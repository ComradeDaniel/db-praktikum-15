import type { QueryResult } from '../lib/types'
import { CategoryTree } from './CategoryTree'
import { OfferList } from './OfferList'
import { Empty, ProductDetailsList, ProductSummaryList } from './ProductList'
import { TrollList } from './TrollList'

type ResultsProps = {
  result: QueryResult | null
  error: string | null
  loading: boolean
}

export function Results({ result, error, loading }: ResultsProps) {
  if (loading) {
    return <p className="px-4 py-10 text-center text-ink-muted">Abfrage läuft…</p>
  }
  if (error) {
    return (
      <div className="m-4 rounded-md border border-down/30 bg-down/5 px-4 py-3 text-sm text-down" role="alert">
        {error}
      </div>
    )
  }
  if (!result) {
    return <Empty>Noch keine Abfrage ausgeführt.</Empty>
  }

  switch (result.kind) {
    case 'product':
      return <ProductDetailsList product={result.value} />
    case 'products':
      return <ProductSummaryList products={result.value} />
    case 'tree':
      return <CategoryTree node={result.value} />
    case 'trolls':
      return <TrollList trolls={result.value} />
    case 'offers':
      return <OfferList offers={result.value} />
    case 'review':
      return (
        <p className="m-4 rounded-md border border-ok/30 bg-ok/5 px-4 py-3 text-sm text-ok">
          Review wurde gespeichert.
        </p>
      )
  }
}

export function resultCount(result: QueryResult | null): string | null {
  if (!result) return null
  switch (result.kind) {
    case 'product':
      return result.value ? '1 Ergebnis' : '0 Ergebnisse'
    case 'products':
      return countLabel(result.value.length)
    case 'trolls':
      return countLabel(result.value.length)
    case 'offers':
      return countLabel(result.value.length)
    case 'tree':
      return 'Kategorienbaum'
    case 'review':
      return 'gespeichert'
  }
}

function countLabel(n: number): string {
  return n === 1 ? '1 Ergebnis' : `${n.toLocaleString('de-DE')} Ergebnisse`
}
