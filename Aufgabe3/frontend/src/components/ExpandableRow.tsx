import type { ReactNode } from 'react'
import { useId, useState } from 'react'

type ExpandableRowProps = {
  summary: ReactNode
  children: ReactNode
  defaultOpen?: boolean
}

export function Chevron({ open }: { open: boolean }) {
  return (
    <svg
      className={`h-4 w-4 shrink-0 text-ink-muted transition-transform ${open ? 'rotate-90' : ''}`}
      viewBox="0 0 20 20"
      fill="currentColor"
      aria-hidden
    >
      <path
        fillRule="evenodd"
        d="M7.21 14.77a.75.75 0 01.02-1.06L11.168 10 7.23 6.29a.75.75 0 111.04-1.08l4.5 4.25a.75.75 0 010 1.08l-4.5 4.25a.75.75 0 01-1.06-.02z"
        clipRule="evenodd"
      />
    </svg>
  )
}

export function ExpandableRow({ summary, children, defaultOpen = false }: ExpandableRowProps) {
  const [open, setOpen] = useState(defaultOpen)
  const panelId = useId()

  return (
    <li className="border-b border-line last:border-b-0">
      <button
        type="button"
        aria-expanded={open}
        aria-controls={panelId}
        onClick={() => setOpen((v) => !v)}
        className="flex w-full cursor-pointer items-center gap-3 px-4 py-3 text-left hover:bg-paper/80"
      >
        <Chevron open={open} />
        <div className="min-w-0 flex-1">{summary}</div>
      </button>
      {open && (
        <div id={panelId} className="border-t border-line bg-paper/50 px-4 py-3 pl-11">
          {children}
        </div>
      )}
    </li>
  )
}

export function DetailGrid({ items }: { items: [string, ReactNode][] }) {
  const present = items.filter(([, value]) => value !== null && value !== undefined && value !== '')
  if (present.length === 0) {
    return <p className="text-sm text-ink-muted">Keine weiteren Angaben.</p>
  }
  return (
    <dl className="grid grid-cols-[9.5rem_1fr] gap-x-4 gap-y-1.5 text-sm">
      {present.map(([label, value]) => (
        <div key={label} className="contents">
          <dt className="text-ink-muted">{label}</dt>
          <dd className="min-w-0 wrap-break-word">{value}</dd>
        </div>
      ))}
    </dl>
  )
}
