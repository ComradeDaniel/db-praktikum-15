import { useState } from 'react'
import type { CategoryNode } from '../lib/types'
import { Chevron } from './ExpandableRow'

export function CategoryTree({ node }: { node: CategoryNode }) {
  const count = countNodes(node)
  return (
    <div>
      <p className="border-b border-line px-4 py-2 text-xs text-ink-muted">
        {count} Kategorien (Knoten aufklappen, um Unterkategorien zu sehen)
      </p>
      <ul className="py-1">
        <CategoryItem node={node} depth={0} defaultOpen />
      </ul>
    </div>
  )
}

function CategoryItem({
  node,
  depth,
  defaultOpen = false,
}: {
  node: CategoryNode
  depth: number
  defaultOpen?: boolean
}) {
  const children = node.children ?? []
  const hasChildren = children.length > 0
  const [open, setOpen] = useState(defaultOpen)

  if (!hasChildren) {
    return (
      <li style={{ paddingLeft: 12 + depth * 16 }} className="flex items-center gap-2 px-4 py-1.5 text-sm">
        <span className="inline-block w-4" />
        <span>{node.name || `Kategorie ${node.categoryId}`}</span>
        <span className="text-xs text-ink-muted">#{node.categoryId}</span>
      </li>
    )
  }

  return (
    <li>
      <button
        type="button"
        aria-expanded={open}
        onClick={() => setOpen((v) => !v)}
        style={{ paddingLeft: 16 + depth * 16 }}
        className="flex w-full cursor-pointer items-center gap-2 py-1.5 pr-4 text-left text-sm hover:bg-paper/80"
      >
        <Chevron open={open} />
        <span className="font-medium">{node.name || `Kategorie ${node.categoryId}`}</span>
        <span className="text-xs text-ink-muted">
          #{node.categoryId} · {children.length} Unterkategorien
        </span>
      </button>
      {open && (
        <ul>
          {children.map((child) => (
            <CategoryItem key={child.categoryId} node={child} depth={depth + 1} />
          ))}
        </ul>
      )}
    </li>
  )
}

function countNodes(node: CategoryNode): number {
  return 1 + (node.children ?? []).reduce((sum, child) => sum + countNodes(child), 0)
}
