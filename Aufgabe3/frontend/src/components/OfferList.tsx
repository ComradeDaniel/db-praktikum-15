import { dash, formatPrice } from '../lib/format'
import type { OfferInfo } from '../lib/types'
import { Empty } from './ProductList'
import { DetailGrid, ExpandableRow } from './ExpandableRow'

export function OfferList({ offers }: { offers: OfferInfo[] }) {
  if (offers.length === 0) {
    return <Empty>Keine Angebote gefunden.</Empty>
  }
  return (
    <ul>
      {offers.map((offer) => (
        <ExpandableRow
          key={offer.offerId}
          summary={
            <div className="flex flex-wrap items-center gap-x-3 gap-y-1">
              <span className="font-medium">{offer.storeName ?? `Store ${offer.storeId}`}</span>
              <span className="tabular-nums">{formatPrice(offer.priceCents, offer.currency)}</span>
              <span
                className={`rounded-full px-2 py-0.5 text-xs font-semibold ${
                  offer.available ? 'bg-ok/10 text-ok' : 'bg-paper-2 text-ink-muted'
                }`}
              >
                {offer.available ? 'verfügbar' : offer.available === false ? 'nicht verfügbar' : 'unbekannt'}
              </span>
            </div>
          }
        >
          <DetailGrid
            items={[
              ['Angebots-ID', String(offer.offerId)],
              ['Store-ID', String(offer.storeId)],
              ['Store', dash(offer.storeName)],
              ['Produkt-ID', <code key="pid">{offer.productId}</code>],
              ['Preis', formatPrice(offer.priceCents, offer.currency)],
              ['Währung', dash(offer.currency)],
              ['Verfügbar', dash(offer.available)],
              ['Zustand', dash(offer.condition)],
            ]}
          />
        </ExpandableRow>
      ))}
    </ul>
  )
}
