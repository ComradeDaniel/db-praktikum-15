export function dash(value: string | number | boolean | null | undefined): string {
  if (value === null || value === undefined || value === '') return '—'
  if (typeof value === 'boolean') return value ? 'ja' : 'nein'
  return String(value)
}

export function productTypeLabel(type: string): string {
  switch (type) {
    case 'Book':
      return 'Buch'
    case 'DVD':
      return 'DVD'
    case 'MusicCD':
      return 'Musik-CD'
    default:
      return type || 'Produkt'
  }
}

export function productTypeClass(type: string): string {
  switch (type) {
    case 'Book':
      return 'bg-book/10 text-book'
    case 'DVD':
      return 'bg-dvd/10 text-dvd'
    case 'MusicCD':
      return 'bg-cd/10 text-cd'
    default:
      return 'bg-paper-2 text-ink-muted'
  }
}

export function formatRating(value: number | string | null | undefined): string {
  if (value === null || value === undefined || value === '') return 'keine Bewertung'
  const n = typeof value === 'number' ? value : Number(value)
  if (Number.isNaN(n)) return String(value)
  return n.toLocaleString('de-DE', { minimumFractionDigits: 2, maximumFractionDigits: 2 })
}

export function formatPrice(cents: number | null, currency: string | null): string {
  if (cents === null) return '—'
  const code = currency && /^[A-Z]{3}$/i.test(currency) ? currency.toUpperCase() : 'EUR'
  try {
    return new Intl.NumberFormat('de-DE', { style: 'currency', currency: code }).format(cents / 100)
  } catch {
    return `${(cents / 100).toFixed(2)} ${currency ?? ''}`.trim()
  }
}

export function formatList(values: string[] | null | undefined): string {
  if (!values || values.length === 0) return '—'
  return values.join(', ')
}

export function formatLanguages(
  languages: { language: string; type: string }[] | null | undefined,
): string {
  if (!languages || languages.length === 0) return '—'
  return languages.map((item) => (item.type ? `${item.language} (${item.type})` : item.language)).join(', ')
}

export function formatDate(iso: string | null | undefined): string {
  if (!iso) return '—'
  const d = new Date(iso)
  if (Number.isNaN(d.getTime())) return iso
  return d.toLocaleDateString('de-DE')
}

export function required(value: string, label: string): string {
  const trimmed = value.trim()
  if (!trimmed) throw new Error(`${label} ist erforderlich.`)
  return trimmed
}

export function parsePositiveInt(value: string, label: string): number {
  const n = Number(value)
  if (!Number.isInteger(n) || n < 1) throw new Error(`${label} muss eine ganze Zahl ≥ 1 sein.`)
  return n
}

export function parseNumber(value: string, label: string): number {
  const n = Number(value.replace(',', '.'))
  if (Number.isNaN(n)) throw new Error(`${label} muss eine Zahl sein.`)
  return n
}

export function emptyToNull(value: string): string | null {
  const trimmed = value.trim()
  return trimmed === '' ? null : trimmed
}

export function emptyToInt(value: string, label: string): number | null {
  if (value.trim() === '') return null
  const n = Number(value)
  if (!Number.isInteger(n)) throw new Error(`${label} muss eine ganze Zahl sein.`)
  return n
}
