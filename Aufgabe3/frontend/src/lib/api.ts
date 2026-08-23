import { emptyToInt, emptyToNull, parseNumber, parsePositiveInt, required } from './format'
import type {
  CategoryNode,
  FormState,
  MethodId,
  NewReview,
  OfferInfo,
  ProductDetails,
  ProductSummary,
  QueryResult,
  TrollUser,
} from './types'

class ApiError extends Error {
  status: number
  constructor(status: number, message: string) {
    super(message)
    this.status = status
  }
}

async function request<T>(path: string, init?: RequestInit): Promise<T> {
  const headers = new Headers(init?.headers)
  headers.set('Accept', 'application/json')
  if (init?.body && !headers.has('Content-Type')) {
    headers.set('Content-Type', 'application/json')
  }

  let response: Response
  try {
    response = await fetch(path, { ...init, headers })
  } catch {
    throw new Error('Keine Verbindung zum Server. Läuft die Spring-Boot-Anwendung?')
  }

  const text = await response.text()
  const body = parseBody(text)

  if (!response.ok) {
    throw new ApiError(response.status, errorMessage(response.status, body))
  }

  return body as T
}

function parseBody(text: string): unknown {
  const trimmed = text.trim()
  if (!trimmed || trimmed === 'null') return null
  try {
    return JSON.parse(trimmed)
  } catch {
    return text
  }
}

function errorMessage(status: number, body: unknown): string {
  const fromBody =
    body && typeof body === 'object' && 'error' in body && typeof body.error === 'string'
      ? body.error
      : null
  if (status === 501) {
    return fromBody
      ? `Noch nicht implementiert (${fromBody}).`
      : 'Diese Methode ist in der Mittelschicht noch nicht implementiert.'
  }
  if (fromBody) return fromBody
  if (status === 400) return 'Ungültige Anfrage.'
  if (status === 404) return 'Nicht gefunden.'
  return `Fehler ${status}`
}

function asArray<T>(value: unknown): T[] {
  if (value == null) return []
  if (Array.isArray(value)) return value as T[]
  throw new Error('Unerwartetes Antwortformat (Liste erwartet).')
}

export async function execute(method: MethodId, form: FormState): Promise<QueryResult> {
  switch (method) {
    case 'getProduct': {
      const id = encodeURIComponent(required(form.productId, 'Produkt-ID'))
      const value = await request<ProductDetails | null>(`/api/products/${id}`)
      return { kind: 'product', value }
    }
    case 'getProducts': {
      const params = new URLSearchParams()
      if (form.pattern.trim()) params.set('pattern', form.pattern.trim())
      const qs = params.toString()
      const value = await request<ProductSummary[]>(`/api/products${qs ? `?${qs}` : ''}`)
      return { kind: 'products', value: asArray(value) }
    }
    case 'getCategoryTree': {
      const value = await request<CategoryNode>('/api/categories/tree')
      if (!value || typeof value !== 'object') throw new Error('Ungültige Antwort für den Kategorienbaum.')
      return { kind: 'tree', value }
    }
    case 'getProductsByCategoryPath': {
      const path = required(form.path, 'Kategoriepfad')
      const value = await request<ProductSummary[]>(
        `/api/categories/products?${new URLSearchParams({ path })}`,
      )
      return { kind: 'products', value: asArray(value) }
    }
    case 'getTopProducts': {
      const k = parsePositiveInt(form.k, 'k')
      const value = await request<ProductSummary[]>(
        `/api/top-products?${new URLSearchParams({ k: String(k) })}`,
      )
      return { kind: 'products', value: asArray(value) }
    }
    case 'getSimilarCheaperProduct': {
      const id = encodeURIComponent(required(form.productId, 'Produkt-ID'))
      const value = await request<ProductSummary[]>(`/api/products/${id}/similar-cheaper`)
      return { kind: 'products', value: asArray(value) }
    }
    case 'addNewReview': {
      const score = Number(form.score)
      if (!Number.isInteger(score) || score < 1 || score > 5) {
        throw new Error('Bewertung muss eine ganze Zahl zwischen 1 und 5 sein.')
      }
      const review: NewReview = {
        productId: required(form.productId, 'Produkt-ID'),
        username: emptyToNull(form.username),
        score,
        helpful: emptyToInt(form.helpful, 'Hilfreich'),
        reviewDate: emptyToNull(form.reviewDate),
        summary: emptyToNull(form.summary),
        content: emptyToNull(form.content),
      }
      await request('/api/reviews', { method: 'POST', body: JSON.stringify(review) })
      return { kind: 'review' }
    }
    case 'getTrolls': {
      const maxAverageRating = parseNumber(form.maxAverageRating, 'Maximales Durchschnittsrating')
      const value = await request<TrollUser[]>(
        `/api/trolls?${new URLSearchParams({ maxAverageRating: String(maxAverageRating) })}`,
      )
      return { kind: 'trolls', value: asArray(value) }
    }
    case 'getOffers': {
      const id = encodeURIComponent(required(form.productId, 'Produkt-ID'))
      const value = await request<OfferInfo[]>(`/api/products/${id}/offers`)
      return { kind: 'offers', value: asArray(value) }
    }
  }
}

export async function fetchHealth(): Promise<boolean> {
  try {
    const body = await request<{ status?: string }>('/api/health')
    return body?.status === 'ready'
  } catch {
    return false
  }
}
