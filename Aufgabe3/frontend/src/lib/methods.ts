import type { FormState, MethodId } from './types'

export const METHODS: { id: MethodId; label: string; hint: string }[] = [
  { id: 'getProduct', label: 'Produkt über ID', hint: 'GET /api/products/{id}' },
  { id: 'getProducts', label: 'Produkte über Pattern', hint: 'GET /api/products?pattern=' },
  { id: 'getCategoryTree', label: 'Kategorienbaum', hint: 'GET /api/categories/tree' },
  { id: 'getProductsByCategoryPath', label: 'Produkte über Kategoriepfad', hint: 'GET /api/categories/products?path=' },
  { id: 'getTopProducts', label: 'Top-Produkte', hint: 'GET /api/top-products?k=' },
  { id: 'getSimilarCheaperProduct', label: 'Ähnliche günstigere Produkte', hint: 'GET /api/products/{id}/similar-cheaper' },
  { id: 'addNewReview', label: 'Review hinzufügen', hint: 'POST /api/reviews' },
  { id: 'getTrolls', label: 'Trolle', hint: 'GET /api/trolls?maxAverageRating=' },
  { id: 'getOffers', label: 'Angebote', hint: 'GET /api/products/{id}/offers' },
]

export function submitLabel(form: FormState, id: MethodId): string {
  switch (id) {
    case 'addNewReview':
      return 'Review speichern'
    case 'getCategoryTree':
      return 'Baum laden'
    case 'getProducts':
      return form.pattern.trim() ? 'Suchen' : 'Alle Produkte laden'
    default:
      return 'Abfragen'
  }
}
