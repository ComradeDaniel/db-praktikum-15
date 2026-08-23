import { dash, formatDate, formatRating, productTypeClass, productTypeLabel } from '../lib/format'
import type { ProductDetails, ProductSummary } from '../lib/types'
import { DetailGrid, ExpandableRow } from './ExpandableRow'

function TypeBadge({ type }: { type: string }) {
  return (
    <span className={`inline-flex rounded-full px-2 py-0.5 text-xs font-semibold ${productTypeClass(type)}`}>
      {productTypeLabel(type)}
    </span>
  )
}

function ProductSummaryLine({ product }: { product: ProductSummary }) {
  return (
    <div className="flex flex-wrap items-center gap-x-3 gap-y-1">
      <span className="font-medium">{product.title}</span>
      <TypeBadge type={product.productType} />
      <span className="text-sm text-ink-muted">★ {formatRating(product.avgRating)}</span>
    </div>
  )
}

export function ProductSummaryList({ products }: { products: ProductSummary[] }) {
  if (products.length === 0) {
    return <Empty>Keine Produkte gefunden.</Empty>
  }
  return (
    <ul>
      {products.map((product) => (
        <ExpandableRow key={product.productId} summary={<ProductSummaryLine product={product} />}>
          <DetailGrid
            items={[
              ['Produkt-ID', <code key="id">{product.productId}</code>],
              ['Typ', productTypeLabel(product.productType)],
              ['Titel', product.title],
              ['Durchschnittsrating', formatRating(product.avgRating)],
              ['Sales Rank', dash(product.salesRank)],
            ]}
          />
        </ExpandableRow>
      ))}
    </ul>
  )
}

export function ProductDetailsList({ product }: { product: ProductDetails | null }) {
  if (!product) {
    return <Empty>Kein Produkt mit dieser ID gefunden.</Empty>
  }
  return (
    <ul>
      <ExpandableRow defaultOpen summary={<ProductSummaryLine product={product} />}>
        <div className="flex flex-col gap-4 sm:flex-row">
          {product.imageUrl && (
            <img
              src={product.imageUrl}
              alt=""
              className="h-36 w-28 shrink-0 rounded-md border border-line object-cover bg-paper-2"
            />
          )}
          <div className="min-w-0 flex-1 space-y-4">
            <DetailGrid
              items={[
                ['Produkt-ID', <code key="id">{product.productId}</code>],
                ['Typ', productTypeLabel(product.productType)],
                ['Titel', product.title],
                ['Durchschnittsrating', formatRating(product.avgRating)],
                ['Anzahl Reviews', String(product.numReviews)],
                ['Sales Rank', dash(product.salesRank)],
                ['EAN', dash(product.ean)],
                [
                  'Detailseite',
                  product.detailUrl ? (
                    <a
                      key="url"
                      href={product.detailUrl}
                      target="_blank"
                      rel="noreferrer"
                      className="text-accent underline-offset-2 hover:underline"
                    >
                      {product.detailUrl}
                    </a>
                  ) : (
                    '—'
                  ),
                ],
              ]}
            />
            {product.book && (
              <section>
                <h3 className="mb-2 text-xs font-semibold tracking-wide text-book uppercase">Buch</h3>
                <DetailGrid
                  items={[
                    ['ISBN', dash(product.book.isbn)],
                    ['Seiten', dash(product.book.pageCount)],
                    ['Erscheinungsdatum', formatDate(product.book.releaseDate)],
                    ['Einband', dash(product.book.binding)],
                    ['Ausgabe', dash(product.book.edition)],
                  ]}
                />
              </section>
            )}
            {product.dvd && (
              <section>
                <h3 className="mb-2 text-xs font-semibold tracking-wide text-dvd uppercase">DVD</h3>
                <DetailGrid
                  items={[
                    ['Format', dash(product.dvd.format)],
                    ['Laufzeit (Min.)', dash(product.dvd.runtime)],
                    ['Region', dash(product.dvd.regionCode)],
                    ['Erscheinungsdatum', formatDate(product.dvd.releaseDate)],
                    ['Bildformat', dash(product.dvd.aspectRatio)],
                    ['UPC', dash(product.dvd.upc)],
                  ]}
                />
              </section>
            )}
            {product.musicCd && (
              <section>
                <h3 className="mb-2 text-xs font-semibold tracking-wide text-cd uppercase">Musik-CD</h3>
                <DetailGrid
                  items={[
                    ['Erscheinungsdatum', formatDate(product.musicCd.releaseDate)],
                    ['Einband', dash(product.musicCd.binding)],
                    ['Format', dash(product.musicCd.format)],
                    ['Anzahl Discs', dash(product.musicCd.numDiscs)],
                    ['UPC', dash(product.musicCd.upc)],
                  ]}
                />
              </section>
            )}
          </div>
        </div>
      </ExpandableRow>
    </ul>
  )
}

export function Empty({ children }: { children: string }) {
  return <p className="px-4 py-8 text-center text-ink-muted">{children}</p>
}
