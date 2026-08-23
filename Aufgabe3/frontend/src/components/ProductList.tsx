import {
  dash,
  formatDate,
  formatLanguages,
  formatList,
  formatPrice,
  formatRating,
  productTypeClass,
  productTypeLabel,
} from '../lib/format'
import type { ProductDetails, ProductSummary, TrackInfo } from '../lib/types'
import { DetailGrid, ExpandableRow } from './ExpandableRow'

function TypeBadge({ type }: { type: string }) {
  return (
    <span className={`inline-flex rounded-full px-2 py-0.5 text-xs font-semibold ${productTypeClass(type)}`}>
      {productTypeLabel(type)}
    </span>
  )
}

function ProductSummaryLine({
  title,
  productType,
  avgRating,
  minPriceCents,
  numReviews,
}: {
  title: string
  productType: string
  avgRating: number | string | null
  minPriceCents?: number | null
  numReviews?: number
}) {
  return (
    <div className="flex flex-wrap items-center gap-x-3 gap-y-1">
      <span className="font-medium">{title}</span>
      <TypeBadge type={productType} />
      <span className="text-sm text-ink-muted">★ {formatRating(avgRating)}</span>
      {minPriceCents != null && (
        <span className="text-sm tabular-nums text-ink-muted">{formatPrice(minPriceCents, null)}</span>
      )}
      {numReviews != null && <span className="text-sm text-ink-muted">{numReviews} Reviews</span>}
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
        <ExpandableRow
          key={product.productId}
          summary={
            <ProductSummaryLine
              title={product.title}
              productType={product.productType}
              avgRating={product.avgRating}
              minPriceCents={product.minPriceCents}
              numReviews={product.numReviews}
            />
          }
        >
          <DetailGrid
            items={[
              ['Produkt-ID', <code key="id">{product.productId}</code>],
              ['Typ', productTypeLabel(product.productType)],
              ['Titel', product.title],
              ['Durchschnittsrating', formatRating(product.avgRating)],
              ['Anzahl Reviews', String(product.numReviews)],
              ['Sales Rank', dash(product.salesRank)],
              ['Mindestpreis', formatPrice(product.minPriceCents, null)],
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
      <ExpandableRow
        defaultOpen
        summary={
          <ProductSummaryLine
            title={product.title}
            productType={product.productType}
            avgRating={product.avgRating}
            numReviews={product.numReviews}
          />
        }
      >
        <div className="flex flex-col gap-4 sm:flex-row">
          {product.imageUrl && (
            <img
              src={product.imageUrl}
              alt=""
              className="h-36 w-28 shrink-0 rounded-md border border-line bg-paper-2 object-cover"
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
                ['Kategorien', formatList(product.categories)],
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
                    ['Autor:innen', formatList(product.book.authors)],
                    ['Verlag', formatList(product.book.publishers)],
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
                    ['Kinostart', dash(product.dvd.theatricalRelease)],
                    ['Bildformat', dash(product.dvd.aspectRatio)],
                    ['Audioformat', dash(product.dvd.audioFormat)],
                    ['UPC', dash(product.dvd.upc)],
                    ['Studios', formatList(product.dvd.studios)],
                    ['Regie', formatList(product.dvd.directors)],
                    ['Schauspiel', formatList(product.dvd.actors)],
                    ['Creator', formatList(product.dvd.creators)],
                    ['Sprachen', formatLanguages(product.dvd.languages)],
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
                    ['Künstler:innen', formatList(product.musicCd.artists)],
                    ['Label', formatList(product.musicCd.labels)],
                  ]}
                />
                <TrackList tracks={product.musicCd.tracks} />
              </section>
            )}
          </div>
        </div>
      </ExpandableRow>
    </ul>
  )
}

function TrackList({ tracks }: { tracks: TrackInfo[] | null | undefined }) {
  if (!tracks || tracks.length === 0) return null
  const ordered = [...tracks].sort((a, b) => a.trackNo - b.trackNo)
  return (
    <div className="mt-3">
      <h4 className="mb-1.5 text-xs font-semibold tracking-wide text-ink-muted uppercase">Tracks</h4>
      <ol className="space-y-0.5 text-sm">
        {ordered.map((track) => (
          <li key={`${track.trackNo}-${track.name}`} className="flex gap-2">
            <span className="w-6 shrink-0 tabular-nums text-ink-muted">{track.trackNo}.</span>
            <span>{track.name}</span>
          </li>
        ))}
      </ol>
    </div>
  )
}

export function Empty({ children }: { children: string }) {
  return <p className="px-4 py-8 text-center text-ink-muted">{children}</p>
}
