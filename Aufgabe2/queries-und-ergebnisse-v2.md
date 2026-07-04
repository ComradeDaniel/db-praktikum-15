# Media Store — Teil 2a: SQL-Anfragen und Ergebnisse

Ausgeführt auf der lokal geladenen PostgreSQL-Datenbank (`mediastore`, Docker, Port 5432).
Lange Ergebnislisten sind gekürzt (Sample + Gesamtzahl).

---

## Q1 — Wieviele Produkte jeden Typs (Buch, Musik-CD, DVD)?

```sql
select *
from (select count(*) as books from book) as b,
     (select count(*) as dvds  from dvd)  as d,
     (select count(*) as cds   from musiccd) as m;
```

**Ergebnis:**

| books | dvds | cds  |
|-------|------|------|
| 696   | 689  | 1939 |

(1 Zeile)

---

## Q2 — Die 5 besten Produkte jedes Typs nach Durchschnitts-Rating

```sql
(select product_type, product_id, avg_rating from product
 where avg_rating is not null and product_type = 'Book'
 order by avg_rating desc limit 5)
union
(select product_type, product_id, avg_rating from product
 where avg_rating is not null and product_type = 'DVD'
 order by avg_rating desc limit 5)
union
(select product_type, product_id, avg_rating from product
 where avg_rating is not null and product_type = 'MusicCD'
 order by avg_rating desc limit 5)
order by product_type asc, avg_rating desc;
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

> Interpretation: „kein Angebot“ = kein **verfügbares** Angebot (`available`).

```sql
select product_id from product
except
select product_id from offer where available;
```

**Ergebnis (gekürzt):**

| product_id |
|------------|
| B00066VMC8 |
| 3933241553 |
| 3791530119 |
| B00030B9DY |
| B00005OCH6 |
| … |

(2359 Zeilen)

---

## Q4 — Teuerstes Angebot mehr als doppelt so teuer wie das preiswerteste?

```sql
select oa.product_id, oa.price_cents as price_left, ob.price_cents as price_right
from offer as oa join offer as ob on oa.product_id = ob.product_id
where oa.price_cents is not null and ob.price_cents is not null
  and oa.price_cents > 0 and ob.price_cents > 0
  and oa.price_cents > 2 * ob.price_cents;
```

**Ergebnis:**

| product_id | price_left | price_right |
|------------|------------|-------------|
| B00005AT2N | 1712 | 712 |
| B0007Z0Y72 |  719 |  10 |
| B00004CWTY | 1034 | 333 |
| B00004CWTY | 1111 | 333 |

(4 Zeilen — Ausgabe als Paare, daher `B00004CWTY` doppelt; entspricht 3 distinct Produkten)

---

## Q5 — Produkte mit mindestens einer 1er- UND einer 5er-Bewertung

```sql
select a.product_id from review as a
where exists (select product_id from review where score = 1 and a.product_id = product_id)
  and exists (select product_id from review where score = 5 and a.product_id = product_id);
```

**Ergebnis (gekürzt):**

| product_id |
|------------|
| 3120101702 |
| 3190028516 |
| 3401023845 |
| 3401058371 |
| … |

(613 Zeilen — eine Zeile je passender Review, entspricht **140 distinct Produkten**)

---

## Q6 — Für wieviele Produkte gibt es gar keine Rezension?

```sql
select p.product_id from product as p
where not exists (select product_id from review where p.product_id = product_id);
```

**Ergebnis (gekürzt):**

| product_id |
|------------|
| 3275014838 |
| 3401024434 |
| 3401044923 |
| 3401045512 |
| … |

(1220 Zeilen)

---

## Q7 — Rezensenten mit mindestens 10 Rezensionen

```sql
select username, count(*) from review
where username is not null
group by username having count(username) > 9;
```

**Ergebnis:**

| username | count |
|----------|-------|
| katja-lesemaus        | 11 |
| marccoll11            | 10 |
| media-maniade         | 20 |
| m_oehri_stadtmagazine | 13 |
| petethemusicfan       | 14 |
| vspillner             | 13 |

(6 Zeilen)

---

## Q8 — Buchautoren, die auch an DVDs oder Musik-CDs beteiligt sind

```sql
select distinct person from bookauthor
where exists (
  select cdartist.person  from cdartist  where cdartist.person  = bookauthor.person
  union
  select dvdperson.person from dvdperson where dvdperson.person = bookauthor.person
);
```

**Ergebnis:**

| person |
|--------|
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

## Q9 — Durchschnittliche Anzahl von Liedern einer Musik-CD

> CDs ohne Track-Einträge zählen als 0 (`left join` + `coalesce`).

```sql
-- trackount.orZero()
select avg(coalesce(trackcount, 0)) from (
  select musiccd.product_id, trackcount
  from musiccd
  left join (select product_id, count(*) as trackcount from track group by product_id) as bla
    on bla.product_id = musiccd.product_id
);
```

**Ergebnis:**

| avg |
|-----|
| 21.5420319752449716 |

(1 Zeile)

---

## Q10 — Produkte mit ähnlichen Produkten in einer anderen Hauptkategorie

> Die Hauptkategorie (Wurzel ohne Oberkategorie) wird per rekursiver CTE einmalig je
> Kategorie gemappt (`category_main_map`). Ein Produkt zählt, wenn sein ähnliches Produkt
> in einer Hauptkategorie liegt, die das Produkt selbst nicht hat.

```sql
-- https://www.postgresql.org/docs/current/queries-with.html#QUERIES-WITH-RECURSIVE
-- dass es AUCH in der selben Hauptkategorie sein darf, ist nicht ausgeschlosen
with recursive category_main_map(start_id, temp_id, main_id) as (
  select category_id, parent_id, category_id from category
  union all
  select start_id, c.parent_id, coalesce(c.parent_id, m.temp_id)
  from category_main_map m join category c on m.temp_id = c.category_id
  where c.category_id is not null or c.parent_id is not null
)
select count(distinct sim.product_id) from similarproduct sim
join productcategory c on sim.similar_product_id = c.product_id
join (select start_id, main_id from category_main_map where temp_id is null) as main
  on main.start_id = c.category_id
where not exists (
  select 1 from productcategory pcsub
  join (select start_id, main_id from category_main_map where temp_id is null) as simmain
    on pcsub.category_id = simmain.start_id
  where pcsub.product_id = sim.product_id
    and simmain.main_id = main.main_id
);
```

**Ergebnis:**

| count |
|-------|
| 451   |

(1 Zeile — 451 Produkte)

---

## Q11 — Produkte, die in allen Filialen angeboten werden

> „angeboten“ = es existiert ein Offer (unabhängig von Verfügbarkeit).
> Funktioniert für beliebige Filialanzahl; Mehrfachangebote pro Filiale via `distinct`.

```sql
-- Annahme: angeboten heißt nicht, dass es verfügbar sein muss sondern dass es ein offer gibt
select o.product_id, store_id from offer o
where (
    select count(distinct store_id) from offer where product_id = o.product_id
  ) = (
    select count(distinct store_id) from offer
  )
order by product_id;
```

**Ergebnis (gekürzt):**

| product_id | store_id |
|------------|----------|
| B000000UWU | 2 |
| B000000UWU | 1 |
| B000000WMJ | 2 |
| B000000WMJ | 1 |
| B000000WW4 | 1 |
| … |

(607 Zeilen — Ausgabe als (Produkt, Filiale)-Paare, entspricht **303 distinct Produkten**)

Filialen in der DB: `1 = Leipzig`, `2 = Dresden`.

---

## Q12 — In wieviel Prozent der Fälle aus Q11 gibt es in Leipzig das preiswerteste Angebot?

```sql
select (
  select count(distinct o.product_id)
  from offer o join store s on o.store_id = s.store_id
  where s.name = 'Leipzig' and -- in leipzig
    -- wird in allen stores verkauft?
    (select count(distinct store_id) from offer where product_id = o.product_id)
      = (select count(distinct store_id) from offer)
    -- günstigste store_ids enthalten store_id von leipzig?
    and o.store_id = any (
      -- store ids der günstigsten offers für ein produkt
      select store_id from offer sub
      where sub.product_id = o.product_id and sub.price_cents is not null
        and sub.price_cents <= all (
          select price_cents from offer
          where product_id = sub.product_id and price_cents is not null
        )
    )
) * 100.0 / (
  -- durch die anzahl aus aufgabe 11
  select count(distinct o.product_id) from offer o
  where (select count(distinct store_id) from offer where product_id = o.product_id)
      = (select count(distinct store_id) from offer)
) as prozent;
```

**Ergebnis:**

| prozent |
|---------|
| 18.8118811881188119 |

(1 Zeile — ca. 18,81 %)


# Aufgabe 2b:
## Trigger für Änderungen in der Review Tabelle

```sql
-- 2b
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