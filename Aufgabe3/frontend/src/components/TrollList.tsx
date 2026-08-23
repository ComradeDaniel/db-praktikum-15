import type { TrollUser } from '../lib/types'
import { Empty } from './ProductList'
import { DetailGrid, ExpandableRow } from './ExpandableRow'
import { formatRating } from '../lib/format'

export function TrollList({ trolls }: { trolls: TrollUser[] }) {
  if (trolls.length === 0) {
    return <Empty>Keine Nutzer unter diesem Schwellenwert.</Empty>
  }
  return (
    <ul>
      {trolls.map((troll) => (
        <ExpandableRow
          key={troll.username}
          summary={
            <div className="flex flex-wrap items-center gap-x-3 gap-y-1">
              <span className="font-medium">{troll.username}</span>
              <span className="text-sm text-ink-muted">
                Ø {formatRating(troll.averageScore)} · {troll.reviewCount} Reviews
              </span>
            </div>
          }
        >
          <DetailGrid
            items={[
              ['Benutzername', troll.username],
              ['Durchschnittsbewertung', formatRating(troll.averageScore)],
              ['Anzahl Reviews', String(troll.reviewCount)],
            ]}
          />
        </ExpandableRow>
      ))}
    </ul>
  )
}
