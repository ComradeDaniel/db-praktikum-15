import type { FormEvent, ReactNode } from 'react'
import { submitLabel } from '../lib/methods'
import type { FormState, MethodId } from '../lib/types'

type QueryFormProps = {
  method: MethodId
  form: FormState
  loading: boolean
  onChange: (patch: Partial<FormState>) => void
  onSubmit: (event: FormEvent) => void
}

const inputClass =
  'w-full rounded-md border border-line bg-card px-3 py-2 text-sm text-ink outline-none focus:border-accent focus:ring-2 focus:ring-accent/20'

export function QueryForm({ method, form, loading, onChange, onSubmit }: QueryFormProps) {
  return (
    <form onSubmit={onSubmit} className="space-y-4">
      {method === 'getProduct' && (
        <Field label="Produkt-ID" hint="z. B. 3257008945 oder B00005AR5L">
          <input
            className={inputClass}
            value={form.productId}
            onChange={(e) => onChange({ productId: e.target.value })}
            placeholder="Produkt-ID"
            autoComplete="off"
            required
          />
        </Field>
      )}

      {method === 'getProducts' && (
        <Field
          label="Titel-Pattern"
          hint="SQL-Wildcards: % (beliebig) und _ (ein Zeichen). Leer lassen, um alle Produkte zu laden."
        >
          <input
            className={inputClass}
            value={form.pattern}
            onChange={(e) => onChange({ pattern: e.target.value })}
            placeholder="%Harry Potter%"
            autoComplete="off"
          />
        </Field>
      )}

      {method === 'getCategoryTree' && (
        <p className="text-sm text-ink-muted">Diese Abfrage benötigt keine Parameter.</p>
      )}

      {method === 'getProductsByCategoryPath' && (
        <Field label="Kategoriepfad" hint="Pfad von der Wurzel, getrennt durch /. Namen allein sind nicht eindeutig.">
          <input
            className={inputClass}
            value={form.path}
            onChange={(e) => onChange({ path: e.target.value })}
            placeholder="Formate/Box-Sets"
            autoComplete="off"
            required
          />
        </Field>
      )}

      {method === 'getTopProducts' && (
        <Field label="Anzahl k" hint="Die k bestbewerteten Produkte.">
          <input
            className={`${inputClass} max-w-32`}
            type="number"
            min={1}
            step={1}
            value={form.k}
            onChange={(e) => onChange({ k: e.target.value })}
            required
          />
        </Field>
      )}

      {method === 'getSimilarCheaperProduct' && (
        <Field label="Produkt-ID" hint="Ähnliche Produkte, die günstiger sind als dieses.">
          <input
            className={inputClass}
            value={form.productId}
            onChange={(e) => onChange({ productId: e.target.value })}
            placeholder="Produkt-ID"
            autoComplete="off"
            required
          />
        </Field>
      )}

      {method === 'addNewReview' && <ReviewFields form={form} onChange={onChange} />}

      {method === 'getTrolls' && (
        <Field
          label="Maximales Durchschnittsrating"
          hint="Nutzer, deren durchschnittliche Bewertung unter diesem Wert liegt."
        >
          <input
            className={`${inputClass} max-w-32`}
            type="number"
            min={0}
            max={5}
            step="0.1"
            value={form.maxAverageRating}
            onChange={(e) => onChange({ maxAverageRating: e.target.value })}
            required
          />
        </Field>
      )}

      {method === 'getOffers' && (
        <Field label="Produkt-ID" hint="Alle Angebote zu diesem Produkt.">
          <input
            className={inputClass}
            value={form.productId}
            onChange={(e) => onChange({ productId: e.target.value })}
            placeholder="Produkt-ID"
            autoComplete="off"
            required
          />
        </Field>
      )}

      <button
        type="submit"
        disabled={loading}
        className="cursor-pointer rounded-md bg-accent px-4 py-2 text-sm font-semibold text-white hover:bg-accent-hover disabled:cursor-wait disabled:opacity-60"
      >
        {loading ? 'Wird ausgeführt…' : submitLabel(form, method)}
      </button>
    </form>
  )
}

function ReviewFields({ form, onChange }: { form: FormState; onChange: (patch: Partial<FormState>) => void }) {
  return (
    <div className="grid gap-4 sm:grid-cols-2">
      <Field label="Produkt-ID">
        <input
          className={inputClass}
          value={form.productId}
          onChange={(e) => onChange({ productId: e.target.value })}
          placeholder="Produkt-ID"
          autoComplete="off"
          required
        />
      </Field>
      <Field label="Benutzername" hint="Optional">
        <input
          className={inputClass}
          value={form.username}
          onChange={(e) => onChange({ username: e.target.value })}
          autoComplete="username"
        />
      </Field>
      <Field label="Bewertung (1–5)">
        <select className={inputClass} value={form.score} onChange={(e) => onChange({ score: e.target.value })}>
          {[1, 2, 3, 4, 5].map((n) => (
            <option key={n} value={n}>
              {n}
            </option>
          ))}
        </select>
      </Field>
      <Field label="Hilfreich" hint="Optional, ganze Zahl">
        <input
          className={inputClass}
          type="number"
          min={0}
          step={1}
          value={form.helpful}
          onChange={(e) => onChange({ helpful: e.target.value })}
        />
      </Field>
      <Field label="Datum" hint="Optional">
        <input
          className={inputClass}
          type="date"
          value={form.reviewDate}
          onChange={(e) => onChange({ reviewDate: e.target.value })}
        />
      </Field>
      <Field label="Zusammenfassung" hint="Optional">
        <input
          className={inputClass}
          value={form.summary}
          onChange={(e) => onChange({ summary: e.target.value })}
        />
      </Field>
      <div className="sm:col-span-2">
        <Field label="Inhalt" hint="Optional">
          <textarea
            className={`${inputClass} min-h-24`}
            value={form.content}
            onChange={(e) => onChange({ content: e.target.value })}
          />
        </Field>
      </div>
    </div>
  )
}

function Field({ label, hint, children }: { label: string; hint?: string; children: ReactNode }) {
  return (
    <label className="block">
      <span className="mb-1 block text-sm font-medium">{label}</span>
      {children}
      {hint && <span className="mt-1 block text-xs text-ink-muted">{hint}</span>}
    </label>
  )
}
