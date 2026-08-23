import { useEffect, useState, type FormEvent } from 'react'
import { execute, fetchHealth } from './lib/api'
import { METHODS } from './lib/methods'
import { initialForm, type FormState, type MethodId, type QueryResult } from './lib/types'
import { QueryForm } from './components/QueryForm'
import { Results, resultCount } from './components/Results'

export default function App() {
  const [method, setMethod] = useState<MethodId>('getProduct')
  const [form, setForm] = useState<FormState>(initialForm)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)
  const [result, setResult] = useState<QueryResult | null>(null)
  const [health, setHealth] = useState<'unknown' | 'ok' | 'down'>('unknown')

  useEffect(() => {
    void fetchHealth().then((ok) => setHealth(ok ? 'ok' : 'down'))
  }, [])

  function selectMethod(next: MethodId) {
    setMethod(next)
    setError(null)
    setResult(null)
  }

  async function onSubmit(event: FormEvent) {
    event.preventDefault()
    setLoading(true)
    setError(null)
    try {
      setResult(await execute(method, form))
    } catch (err) {
      setResult(null)
      setError(err instanceof Error ? err.message : 'Unbekannter Fehler')
    } finally {
      setLoading(false)
    }
  }

  const active = METHODS.find((m) => m.id === method)
  const count = result && !error ? resultCount(result) : null

  return (
    <div className="min-h-screen">
      <header className="bg-ink text-paper">
        <div className="mx-auto flex max-w-6xl flex-wrap items-end justify-between gap-4 px-4 py-5">
          <div>
            <p className="text-xs tracking-[0.2em] text-paper/55 uppercase">DB-Praktikum · Aufgabe 3</p>
            <h1 className="font-display text-3xl font-semibold tracking-tight">Media Store</h1>
          </div>
          <HealthBadge status={health} />
        </div>
        <nav aria-label="Methoden" className="border-t border-white/10">
          <div className="mx-auto flex max-w-6xl flex-wrap gap-1 px-3 py-2">
            {METHODS.map((item) => {
              const selected = item.id === method
              return (
                <button
                  key={item.id}
                  type="button"
                  aria-current={selected ? 'page' : undefined}
                  onClick={() => selectMethod(item.id)}
                  className={`cursor-pointer rounded-md px-3 py-1.5 text-sm transition ${
                    selected ? 'bg-card font-semibold text-ink' : 'text-paper/75 hover:bg-white/10 hover:text-paper'
                  }`}
                >
                  {item.label}
                </button>
              )
            })}
          </div>
        </nav>
      </header>

      <main className="mx-auto grid max-w-6xl gap-6 px-4 py-6 lg:grid-cols-[22rem_1fr] lg:items-start">
        <section className="rounded-xl border border-line bg-card p-5 shadow-[0_1px_0_rgba(26,23,20,0.04)]">
          <h2 className="font-display text-xl">{active?.label}</h2>
          <p className="mt-1 mb-4 font-mono text-xs text-ink-muted">{active?.hint}</p>
          <QueryForm
            method={method}
            form={form}
            loading={loading}
            onChange={(patch) => setForm((prev) => ({ ...prev, ...patch }))}
            onSubmit={(e) => void onSubmit(e)}
          />
        </section>

        <section className="overflow-hidden rounded-xl border border-line bg-card shadow-[0_1px_0_rgba(26,23,20,0.04)]">
          <div className="flex items-center justify-between border-b border-line px-4 py-3">
            <h2 className="font-display text-xl">Ergebnisse</h2>
            {count && <span className="text-xs text-ink-muted">{count}</span>}
          </div>
          <div className="max-h-[min(70vh,48rem)] overflow-auto">
            <Results result={result} error={error} loading={loading} />
          </div>
        </section>
      </main>
    </div>
  )
}

function HealthBadge({ status }: { status: 'unknown' | 'ok' | 'down' }) {
  const label = status === 'ok' ? 'API bereit' : status === 'down' ? 'API nicht erreichbar' : 'API…'
  const dot = status === 'ok' ? 'bg-ok' : status === 'down' ? 'bg-down' : 'bg-paper/40'
  return (
    <span className="inline-flex items-center gap-2 rounded-full border border-white/15 px-3 py-1 text-xs text-paper/80">
      <span className={`h-2 w-2 rounded-full ${dot}`} />
      {label}
    </span>
  )
}
