export type ProductSummary = {
  productId: string
  title: string
  productType: string
  avgRating: number | string | null
  salesRank: number | null
}

export type BookDetails = {
  isbn: string | null
  pageCount: number | null
  releaseDate: string | null
  binding: string | null
  edition: string | null
}

export type DvdDetails = {
  format: string | null
  runtime: number | null
  regionCode: number | null
  releaseDate: string | null
  aspectRatio: string | null
  upc: string | null
}

export type MusicCdDetails = {
  releaseDate: string | null
  binding: string | null
  format: string | null
  numDiscs: number | null
  upc: string | null
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
  book?: BookDetails | null
  dvd?: DvdDetails | null
  musicCd?: MusicCdDetails | null
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
  helpful: number | null
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
  helpful: string
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
  helpful: '',
  reviewDate: '',
  summary: '',
  content: '',
}
