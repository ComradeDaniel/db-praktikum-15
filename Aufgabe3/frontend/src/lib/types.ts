export type ProductSummary = {
  productId: string
  title: string
  productType: string
  avgRating: number | string | null
  salesRank: number | null
  minPriceCents: number | null
  numReviews: number
}

export type TrackInfo = {
  trackNo: number
  name: string
}

export type LanguageInfo = {
  language: string
  type: string
}

export type BookDetails = {
  isbn: string | null
  pageCount: number | null
  releaseDate: string | null
  binding: string | null
  edition: string | null
  authors: string[]
  publishers: string[]
}

export type DvdDetails = {
  format: string | null
  runtime: number | null
  regionCode: number | null
  releaseDate: string | null
  aspectRatio: string | null
  upc: string | null
  audioFormat: string | null
  theatricalRelease: number | null
  studios: string[]
  directors: string[]
  actors: string[]
  creators: string[]
  languages: LanguageInfo[]
}

export type MusicCdDetails = {
  releaseDate: string | null
  binding: string | null
  format: string | null
  numDiscs: number | null
  upc: string | null
  artists: string[]
  labels: string[]
  tracks: TrackInfo[]
}

export type ReviewInfo = {
  reviewId: number
  username: string | null
  score: number
  reviewDate: string | null
  summary: string | null
  content: string | null
  helpful: number | null
}

export type ProductDetails = {
  productId: string
  title: string
  productType: string
  salesRank: number | null
  imageUrl: string | null
  ean: string | null
  detailUrl: string | null
  avgRating: number | string | null
  numReviews: number
  categories: string[]
  book?: BookDetails | null
  dvd?: DvdDetails | null
  musicCd?: MusicCdDetails | null
  reviews?: ReviewInfo[] | null
}

export type CategoryNode = {
  categoryId: number
  name: string
  children: CategoryNode[]
}

export type NewReview = {
  productId: string
  username: string | null
  score: number
  reviewDate: string | null
  summary: string | null
  content: string | null
}

export type TrollUser = {
  username: string
  averageScore: number
  reviewCount: number
}

export type OfferInfo = {
  offerId: number
  storeId: number
  storeName: string | null
  productId: string
  priceCents: number | null
  available: boolean | null
  currency: string | null
  condition: string | null
}

export type QueryResult =
  | { kind: 'product'; value: ProductDetails | null }
  | { kind: 'products'; value: ProductSummary[] }
  | { kind: 'tree'; value: CategoryNode }
  | { kind: 'trolls'; value: TrollUser[] }
  | { kind: 'offers'; value: OfferInfo[] }
  | { kind: 'review' }

export type MethodId =
  | 'getProduct'
  | 'getProducts'
  | 'getCategoryTree'
  | 'getProductsByCategoryPath'
  | 'getTopProducts'
  | 'getSimilarCheaperProduct'
  | 'addNewReview'
  | 'getTrolls'
  | 'getOffers'

export type FormState = {
  productId: string
  pattern: string
  path: string
  k: string
  maxAverageRating: string
  username: string
  score: string
  reviewDate: string
  summary: string
  content: string
}

export const initialForm: FormState = {
  productId: '',
  pattern: '',
  path: '',
  k: '5',
  maxAverageRating: '2',
  username: '',
  score: '5',
  reviewDate: '',
  summary: '',
  content: '',
}
