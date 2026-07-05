# Media Store — Teil 2a: SQL-Anfragen und Ergebnisse

Ausgeführt auf der lokal geladenen PostgreSQL-Datenbank (`mediastore`, Docker, Port 5432).
Bestand: Product = 3324, Offer = 3609, Review = 6276, Store = 2.
Lange Ergebnislisten sind gekürzt (Sample + Gesamtzahl); vollständige Listen in `queries-und-ergebnisse-final.txt`.

---

## Q1 — Wieviele Produkte jeden Typs (Buch, Musik-CD, DVD)?

```sql
SELECT COUNT(*) FILTER (WHERE product_type = 'Book')    AS buecher,
       COUNT(*) FILTER (WHERE product_type = 'MusicCD') AS cds,
       COUNT(*) FILTER (WHERE product_type = 'DVD')     AS dvds
FROM Product;
```

**Ergebnis:**

| buecher | cds  | dvds |
|---------|------|------|
| 696     | 1939 | 689  |

(1 Zeile)

---

## Q2 — Die 5 besten Produkte jedes Typs nach Durchschnitts-Rating

```sql
(SELECT product_type, product_id, avg_rating FROM Product
 WHERE avg_rating IS NOT NULL AND product_type = 'Book'
 ORDER BY avg_rating DESC LIMIT 5)
UNION
(SELECT product_type, product_id, avg_rating FROM Product
 WHERE avg_rating IS NOT NULL AND product_type = 'DVD'
 ORDER BY avg_rating DESC LIMIT 5)
UNION
(SELECT product_type, product_id, avg_rating FROM Product
 WHERE avg_rating IS NOT NULL AND product_type = 'MusicCD'
 ORDER BY avg_rating DESC LIMIT 5)
ORDER BY product_type ASC, avg_rating DESC;
```

**Ergebnis:**

| product_type | product_id | avg_rating |
|--------------|------------|------------|
| Book    | 3257008945 | 5.00 |
| Book    | 3401081853 | 5.00 |
| Book    | 3785555474 | 5.00 |
| Book    | 3920524527 | 5.00 |
| Book    | 3937825266 | 5.00 |
| DVD     | B00005AR5L | 5.00 |
| DVD     | B000068PFH | 5.00 |
| DVD     | B0002A4J3W | 5.00 |
| DVD     | B000BSNPRG | 5.00 |
| DVD     | B000BW9BZW | 5.00 |
| MusicCD | B000001GX5 | 5.00 |
| MusicCD | B000001GYC | 5.00 |
| MusicCD | B000006NXP | 5.00 |
| MusicCD | B00002R2TN | 5.00 |
| MusicCD | B000ALCG06 | 5.00 |

(15 Zeilen — bei Rating-Gleichstand an Platz 5 ist die Auswahl nicht deterministisch)

---

## Q3 — Für welche Produkte gibt es im Moment kein Angebot?

> Interpretation: „kein Angebot" = kein **Offer-Eintrag** (`NOT EXISTS` in `Offer`).

```sql
SELECT p.product_id
FROM Product p
WHERE NOT EXISTS (
    SELECT 1 FROM Offer o WHERE o.product_id = p.product_id
);
```

**Ergebnis:**

| product_id |
|------------|
| B00005Q673 |
| B000068RQ5 |
| B00006OA4U |
| B00006RJSG |
| B000075AIV |
| B00008IDM1 |
| B00008IDM9 |
| B00008VDUA |
| B00008Y4J9 |
| B0000A0I94 |
| B00013T782 |
| B000620NB8 |
| B0007DDPWU |
| B0007SMEQ8 |
| B0009VJVJG |
| B000A1IE5C |
| B000ANRYEM |
| B000ATJYZ8 |
| B000B5KEE6 |
| B000BITGHE |
| B000BRBH90 |
| B000BW9BZW |

(22 Zeilen)

---

## Q4 — Teuerstes Angebot mehr als doppelt so teuer wie das preiswerteste?

```sql
SELECT oa.product_id, oa.price_cents AS price_left, ob.price_cents AS price_right
FROM Offer AS oa JOIN Offer AS ob ON oa.product_id = ob.product_id
WHERE oa.price_cents IS NOT NULL AND ob.price_cents IS NOT NULL
  AND oa.price_cents > 0 AND ob.price_cents > 0
  AND oa.price_cents > 2 * ob.price_cents;
```

**Ergebnis:**

| product_id | price_left | price_right |
|------------|------------|-------------|
| B00004CWTY | 1034 | 333 |
| B00004CWTY | 1111 | 333 |
| B00005AT2N | 1712 | 712 |
| B0007Z0Y72 | 719  | 10  |

(4 Zeilen für 3 distinct Produkte — Self-Join ohne `DISTINCT` liefert Duplikate)

---

## Q5 — Produkte mit mindestens einer 1er- UND einer 5er-Bewertung

```sql
SELECT product_id
FROM Review
GROUP BY product_id
HAVING COUNT(*) FILTER (WHERE score = 1) >= 1
   AND COUNT(*) FILTER (WHERE score = 5) >= 1;
```

**Ergebnis (Sample):**

| product_id |
|------------|
| 3120101702 |
| 3190028516 |
| 3401023845 |
| 3401058371 |
| 3423055960 |
| … |
| B000BKHF9I |
| B000BKSHIG |
| B000BSNPR6 |
| B000C4JTT2 |

(140 Zeilen — vollständige Liste in der `.txt`)

---

## Q6 — Für wieviele Produkte gibt es gar keine Rezension?

```sql
SELECT p.product_id
FROM Product AS p
WHERE NOT EXISTS (
    SELECT product_id FROM review WHERE p.product_id = product_id
);
```

**Ergebnis:** 1220 Produkte ohne Rezension.

(1220 Zeilen product_id)

---

## Q7 — Rezensenten mit mindestens 10 Rezensionen

```sql
SELECT r.username
FROM Review r
WHERE r.username IS NOT NULL
GROUP BY r.username
HAVING COUNT(*) >= 10;
```

**Ergebnis:**

| username              |
|-----------------------|
| media-maniade         |
| petethemusicfan       |
| m_oehri_stadtmagazine |
| vspillner             |
| katja-lesemaus        |
| marccoll11            |

(6 Zeilen)

---

## Q8 — Buchautoren, die auch an DVDs oder Musik-CDs beteiligt sind

```sql
SELECT DISTINCT person
FROM Bookauthor
WHERE EXISTS (
  SELECT cdartist.person FROM Cdartist WHERE cdartist.person = bookauthor.person
  UNION
  SELECT dvdperson.person FROM Dvdperson WHERE dvdperson.person = bookauthor.person
);
```

**Ergebnis (alphabetisch; queries.sql hat kein `ORDER BY`):**

| name |
|------|
| Ac |
| Al |
| Alexandre |
| Daniel Defoe |
| Dav |
| Dietmar Mues |
| Heino |
| Jrgen |
| Jürgen |
| Korn |
| Leonard Bernstein |
| Nas |
| Nicole |
| Peter |
| Robin |
| Sabrina |
| Sandra |
| Va |

(18 Zeilen)

---

## Q9 — Durchschnittliche Anzahl Lieder einer Musik-CD

```sql
SELECT
    (SELECT COUNT(track_no) FROM MusicCD m JOIN Track t ON m.product_id = t.product_id)::numeric
    /
    (SELECT COUNT(product_id) FROM MusicCD) AS avg_tracks;
```

**Ergebnis:**

| avg_tracks |
|------------|
| 21.54 (21.5420…) |

(1 Zeile)

---

## Q10 — Produkte mit ähnlichen Produkten in einer anderen Hauptkategorie (rekursiv)
> Die Hauptkategorie (Wurzel ohne Oberkategorie) wird per rekursiver CTE einmalig je
> Kategorie gemappt (`category_main_map`). Ein Produkt zählt, wenn sein ähnliches Produkt
> in einer Hauptkategorie liegt, die das Produkt selbst nicht hat.

```sql
WITH RECURSIVE category_main_map(start_id, temp_id, main_id) AS (
  SELECT category_id, parent_id, category_id FROM category
  UNION ALL
  SELECT start_id, c.parent_id, coalesce(c.parent_id, m.temp_id)
  FROM category_main_map m JOIN category c ON m.temp_id = c.category_id
  WHERE c.category_id IS NOT NULL OR c.parent_id IS NOT NULL
)
SELECT count(DISTINCT sim.product_id)
FROM similarproduct sim
JOIN productcategory c ON sim.similar_product_id = c.product_id
JOIN (SELECT start_id, main_id FROM category_main_map WHERE temp_id IS NULL) AS main
  ON main.start_id = c.category_id
WHERE NOT EXISTS (
  SELECT 1 FROM productcategory pcsub
  JOIN (SELECT start_id, main_id FROM category_main_map WHERE temp_id IS NULL) AS simmain
    ON pcsub.category_id = simmain.start_id
  WHERE pcsub.product_id = sim.product_id AND simmain.main_id = main.main_id
);
```

**Ergebnis:**

| anzahl |
|--------|
| 451    |

(1 Zeile — 451 Produkte)

---

## Q11 — Welche Produkte werden in allen Filialen angeboten?

> Interpretation: „angeboten" = es existiert ein Offer.

```sql
SELECT o.product_id
FROM Offer o
GROUP BY o.product_id
HAVING COUNT(DISTINCT o.store_id) = (SELECT COUNT(*) FROM Store);
```

**Ergebnis (Sample):**

| product_id |
|------------|
| B000000UWU |
| B000000WMJ |
| B000000WW4 |
| B00000142W |
| B000001DEW |
| B000001WM9 |
| B000001WVC |
| B000002ONW |
| B000002W2L |
| B000002WVI |
| … |

(303 Zeilen — vollständige Liste in der `.txt`.)

---

## Q12 — In wieviel Prozent der Q11-Fälle hat Leipzig das preiswerteste Angebot?

```sql
SELECT (SELECT COUNT(DISTINCT o.product_id)
        FROM Offer o JOIN Store s ON o.store_id = s.store_id
        WHERE s.name = 'Leipzig'
          AND (SELECT COUNT(DISTINCT store_id) FROM Offer WHERE product_id = o.product_id)
            = (SELECT COUNT(DISTINCT store_id) FROM Offer)
          AND o.store_id = ANY (
            SELECT store_id FROM Offer sub
            WHERE sub.product_id = o.product_id AND sub.price_cents IS NOT NULL
              AND sub.price_cents <= ALL (
                SELECT price_cents FROM Offer
                WHERE product_id = sub.product_id AND price_cents IS NOT NULL)
          )) * 100.0
     / (SELECT COUNT(DISTINCT o.product_id)
        FROM Offer o
        WHERE (SELECT COUNT(DISTINCT store_id) FROM Offer WHERE product_id = o.product_id)
            = (SELECT COUNT(DISTINCT store_id) FROM Offer)) AS prozent;
```

**Ergebnis:**

| prozent |
|---------|
| 18.81   |

(1 Zeile — ca. 18,81%)

---

# Aufgabe 2b:
## Trigger für Änderungen in der Review Tabelle

```sql
-- https://www.postgresql.org/docs/current/sql-createtrigger.html
CREATE OR REPLACE FUNCTION update_product_review_stats()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    IF TG_OP IN ('INSERT', 'UPDATE') THEN
        UPDATE Product p
        SET
            num_reviews = stats.cnt,
            avg_rating  = stats.avg
        FROM (
            SELECT
                COUNT(*)::INT                      AS cnt,
                ROUND(AVG(r.score)::numeric, 2)    AS avg
            FROM Review r
            WHERE r.product_id = NEW.product_id
        ) stats
        WHERE p.product_id = NEW.product_id;
    END IF;

    IF TG_OP = 'DELETE'
       OR (TG_OP = 'UPDATE' AND OLD.product_id IS DISTINCT FROM NEW.product_id) THEN
        UPDATE Product p
        SET
            num_reviews = stats.cnt,
            avg_rating  = stats.avg
        FROM (
            SELECT
                COUNT(*)::INT                      AS cnt,
                ROUND(AVG(r.score)::numeric, 2)    AS avg
            FROM Review r
            WHERE r.product_id = OLD.product_id
        ) stats
        WHERE p.product_id = OLD.product_id;
    END IF;

    RETURN COALESCE(NEW, OLD);
END;
$$;

CREATE TRIGGER trg_update_products
    AFTER INSERT OR UPDATE OF score OR DELETE ON Review
    FOR EACH ROW
    EXECUTE FUNCTION update_product_review_stats();
```
