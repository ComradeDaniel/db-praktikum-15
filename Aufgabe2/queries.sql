-- Media Store — Teil 2a: SQL-Anfragen
-- PostgreSQL
-- Gruppe 18


-- Q1: Wieviele Produkte jeden Typs (Buch, Musik-CD, DVD) sind erfasst?
--     Ergebnis als 3-spaltige Relation.
SELECT
    COUNT(*) FILTER (WHERE product_type = 'Book')    AS buecher,
    COUNT(*) FILTER (WHERE product_type = 'MusicCD') AS musik_cds,
    COUNT(*) FILTER (WHERE product_type = 'DVD')     AS dvds
FROM Product;


-- Q2: Die 5 besten Produkte jedes Typs sortiert nach Ø-Rating.
--     Eine Relation mit (Typ, ProduktNr, Rating).
--     Produkte ohne Bewertung (avg_rating IS NULL) bleiben unberücksichtigt.
SELECT typ, produktnr, rating
FROM (
    SELECT product_type AS typ,
           product_id   AS produktnr,
           avg_rating   AS rating,
           ROW_NUMBER() OVER (PARTITION BY product_type
                              ORDER BY avg_rating DESC, product_id) AS rn
    FROM Product
    WHERE avg_rating IS NOT NULL
) t
WHERE rn <= 5
ORDER BY typ, rating DESC, produktnr;


-- Q3: Für welche Produkte gibt es im Moment kein Angebot?
SELECT p.product_id
FROM Product p
WHERE NOT EXISTS (
    SELECT 1 FROM Offer o WHERE o.product_id = p.product_id
)
ORDER BY p.product_id;


-- Q4: Für welche Produkte ist das teuerste Angebot mehr als doppelt
--     so teuer wie das preiswerteste? (nur Angebote mit Preis)
SELECT product_id
FROM Offer
WHERE price_cents IS NOT NULL
GROUP BY product_id
HAVING MIN(price_cents) > 0
   AND MAX(price_cents) > 2 * MIN(price_cents)
ORDER BY product_id;


-- Q5: Produkte mit mindestens einer 1er- UND mindestens einer 5er-Bewertung.
SELECT product_id
FROM Review
WHERE score IN (1, 5)
GROUP BY product_id
HAVING COUNT(*) FILTER (WHERE score = 1) > 0
   AND COUNT(*) FILTER (WHERE score = 5) > 0
ORDER BY product_id;


-- Q6: Für wieviele Produkte gibt es gar keine Rezension?
SELECT COUNT(*) AS produkte_ohne_rezension
FROM Product p
WHERE NOT EXISTS (
    SELECT 1 FROM Review r WHERE r.product_id = p.product_id
);


-- Q7: Rezensenten mit mindestens 10 Rezensionen.
--     Synthetische Gast-Accounts (guest-XXXXXX) sind je Rezension eindeutig
--     und erreichen die Schwelle nicht; NULL-User werden ausgeschlossen.
SELECT username, COUNT(*) AS anzahl_rezensionen
FROM Review
WHERE username IS NOT NULL
GROUP BY username
HAVING COUNT(*) >= 10
ORDER BY anzahl_rezensionen DESC, username;


-- Q8: Duplikatfreie, alphabetisch sortierte Liste aller Buchautoren,
--     die auch an DVDs oder Musik-CDs beteiligt sind.
SELECT DISTINCT ba.person AS name
FROM BookAuthor ba
WHERE ba.person IN (SELECT person FROM DVDPerson)
   OR ba.person IN (SELECT person FROM CDArtist)
ORDER BY name;


-- Q9: Durchschnittliche Anzahl Lieder einer Musik-CD.
--     Gemittelt über alle CDs (CDs ohne Tracks zählen mit 0).
SELECT ROUND(AVG(track_count), 2) AS avg_lieder_pro_cd
FROM (
    SELECT m.product_id, COUNT(t.track_no) AS track_count
    FROM MusicCD m
    LEFT JOIN Track t ON t.product_id = m.product_id
    GROUP BY m.product_id
) s;


-- Q10: Produkte mit ähnlichen Produkten in einer ANDEREN Hauptkategorie.
--      Hauptkategorie = Kategorie ohne Oberkategorie (Wurzel).
--      Rekursive Anfrage ordnet jeder Kategorie ihre Wurzel (Hauptkat.) zu.
WITH RECURSIVE root_of AS (
    -- Basis: Wurzeln zeigen auf sich selbst
    SELECT category_id, category_id AS root_id
    FROM Category
    WHERE parent_id IS NULL
  UNION ALL
    -- Abstieg: Kind erbt die Wurzel des Elternteils
    SELECT c.category_id, r.root_id
    FROM Category c
    JOIN root_of r ON c.parent_id = r.category_id
),
prod_main AS (
    -- Produkt -> Menge seiner Hauptkategorien
    SELECT DISTINCT pc.product_id, ro.root_id
    FROM ProductCategory pc
    JOIN root_of ro ON ro.category_id = pc.category_id
)
SELECT DISTINCT sp.product_id
FROM SimilarProduct sp
WHERE EXISTS (
    SELECT 1
    FROM prod_main pm_s
    WHERE pm_s.product_id = sp.similar_product_id
      AND NOT EXISTS (
          SELECT 1
          FROM prod_main pm_p
          WHERE pm_p.product_id = sp.product_id
            AND pm_p.root_id = pm_s.root_id
      )
)
ORDER BY sp.product_id;


-- Q11: Produkte, die in ALLEN Filialen angeboten werden.
--      "Angeboten" = mit vorliegendem Preis (verfügbar), daher
--      nur Angebote mit price_cents IS NOT NULL.
--      Funktioniert für beliebig viele Filialen (Vergleich mit Store-Anzahl).
--      Ein Produkt kann pro Filiale mehrfach angeboten werden (neu/gebraucht)
--      -> COUNT(DISTINCT store_id).
SELECT product_id
FROM Offer
WHERE price_cents IS NOT NULL
GROUP BY product_id
HAVING COUNT(DISTINCT store_id) = (SELECT COUNT(*) FROM Store)
ORDER BY product_id;


-- Q12: In wieviel Prozent der Q11-Fälle hat Leipzig das preiswerteste Angebot?
--      Preiswertestes = global kleinster price_cents des Produkts;
--      Gleichstand zählt für Leipzig mit.
WITH all_filialen AS (
    -- identisch zu Q11: in allen Filialen mit Preis angeboten
    SELECT product_id
    FROM Offer
    WHERE price_cents IS NOT NULL
    GROUP BY product_id
    HAVING COUNT(DISTINCT store_id) = (SELECT COUNT(*) FROM Store)
),
prices AS (
    SELECT o.product_id, s.name AS store, o.price_cents
    FROM Offer o
    JOIN Store s ON s.store_id = o.store_id
    WHERE o.product_id IN (SELECT product_id FROM all_filialen)
      AND o.price_cents IS NOT NULL
),
cheapest AS (
    SELECT product_id, MIN(price_cents) AS min_price
    FROM prices
    GROUP BY product_id
),
leipzig_cheapest AS (
    SELECT DISTINCT p.product_id
    FROM prices p
    JOIN cheapest c ON c.product_id = p.product_id
                   AND c.min_price  = p.price_cents
    WHERE p.store = 'Leipzig'
)
SELECT
    (SELECT COUNT(*) FROM leipzig_cheapest) AS leipzig_guenstigste,
    (SELECT COUNT(*) FROM all_filialen)     AS q11_gesamt,
    ROUND(
        100.0 * (SELECT COUNT(*) FROM leipzig_cheapest)
              / NULLIF((SELECT COUNT(*) FROM all_filialen), 0),
        2
    ) AS prozent;
