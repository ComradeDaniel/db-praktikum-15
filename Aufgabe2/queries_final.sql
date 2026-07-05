-- Media Store — Teil 2a: SQL-Anfragen
-- PostgreSQL
-- Gruppe 18


-- Q1: Wieviele Produkte jeden Typs (Buch, Musik-CD, DVD) sind in der Datenbank erfasst? Hinweis: Geben Sie das Ergebnis in einer 3-spaltigen Relation aus.
SELECT COUNT(*) FILTER (WHERE product_type = 'Book')    AS buecher,
       COUNT(*) FILTER (WHERE product_type = 'MusicCD') AS cds,
       COUNT(*) FILTER (WHERE product_type = 'DVD')     AS dvds
FROM Product;


-- Q2: Nennen Sie die 5 besten Produkte jedes Typs (Buch, Musik-CD, DVD) sortiert nach dem durchschnittlichem Rating. Hinweis: Geben Sie das Ergebnis in einer einzigen Relation mit den Attributen Typ, ProduktNr, Rating aus.
(SELECT product_type, product_id, avg_rating 
FROM Product
WHERE avg_rating IS NOT NULL AND product_type = 'Book'
ORDER BY avg_rating DESC
LIMIT 5)

UNION

(SELECT product_type, product_id, avg_rating 
FROM Product
WHERE avg_rating IS NOT NULL AND product_type = 'DVD'
ORDER BY avg_rating DESC
LIMIT 5)

UNION

(SELECT product_type, product_id, avg_rating 
FROM Product
WHERE avg_rating IS NOT NULL AND product_type = 'MusicCD'
ORDER BY avg_rating DESC
LIMIT 5)

ORDER BY product_type ASC, avg_rating DESC;


-- Q3: Für welche Produkte gibt es im Moment kein Angebot?
SELECT p.product_id
FROM Product p
WHERE NOT EXISTS (
    SELECT 1
    FROM Offer o
    WHERE o.product_id = p.product_id
);


-- Q4: Für welche Produkte ist das teuerste Angebot mehr als doppelt so teuer wie das preiswerteste? 
SELECT oa.product_id, oa.price_cents AS price_left, ob.price_cents AS price_right 
FROM Offer AS oa JOIN offer AS ob ON oa.product_id = ob.product_id
WHERE
  oa.price_cents IS NOT NULL AND
  ob.price_cents IS NOT NULL AND
  oa.price_cents > 0 AND
  ob.price_cents > 0 AND
  oa.price_cents > 2 * ob.price_cents;


-- Q5: Produkte mit mindestens einer 1er- UND mindestens einer 5er-Bewertung.
SELECT product_id 
FROM Review  
GROUP BY product_id
HAVING COUNT(*) FILTER (WHERE score = 1) >= 1 AND 
       COUNT(*) FILTER (WHERE score = 5) >= 1;


-- Q6: Für wieviele Produkte gibt es gar keine Rezension?
SELECT COUNT(p.product_id) AS produkte_ohne_rezension
FROM Product AS p
WHERE NOT EXISTS (
    SELECT product_id
    FROM review
    WHERE p.product_id = product_id
);


-- Q7: Rezensenten mit mindestens 10 Rezensionen.
SELECT r.username
FROM Review r
WHERE r.username IS NOT NULL
GROUP BY r.username
HAVING COUNT(*) >= 10;


-- Q8: Geben Sie eine duplikatfreie und alphabetisch sortierte Liste der Namen aller Buchautoren an, die auch an DVDs oder Musik-CDs beteiligt sind.
SELECT DISTINCT person 
FROM Bookauthor
WHERE EXISTS (
  SELECT cdartist.person 
  FROM Cdartist 
  WHERE cdartist.person = bookauthor.person
  UNION
  SELECT dvdperson.person 
  FROM Dvdperson 
  WHERE dvdperson.person = bookauthor.person
);


-- Q9: Wie hoch ist die durchschnittliche Anzahl von Liedern einer Musik-CD?
SELECT
    (SELECT COUNT(track_no) 
    FROM MusicCD m JOIN Track t ON m.product_id = t.product_id)::numeric
    /
    (SELECT COUNT(product_id) 
    FROM MusicCD) AS avg_tracks;


-- Q10: Für welche Produkte gibt es ähnliche Produkte in einer anderen Hauptkategorie? Hinweis: Eine Hauptkategorie ist eine Produktkategorie ohne Oberkategorie. Erstellen Sie eine rekursive Anfrage, die zu jedem Produkt dessen Hauptkategorie bestimmt.
--      https://www.postgresql.org/docs/current/queries-with.html#QUERIES-WITH-RECURSIVE
--      dass es AUCH in der selben Hauptkategorie sein darf, ist nicht ausgeschlosen?
WITH RECURSIVE category_main_map(start_id, temp_id, main_id) AS (
  SELECT category_id, parent_id, category_id 
  FROM category
  UNION ALL
  SELECT start_id, c.parent_id, coalesce(c.parent_id, m.temp_id)
  FROM category_main_map m JOIN category c ON m.temp_id = c.category_id
  WHERE c.category_id IS NOT NULL OR c.parent_id IS NOT NULL
)
SELECT count(DISTINCT sim.product_id) 
FROM similarproduct sim
JOIN productcategory c ON sim.similar_product_id = c.product_id
JOIN (SELECT start_id, main_id FROM category_main_map WHERE temp_id IS NULL) AS main ON main.start_id = c.category_id
WHERE NOT EXISTS (
  SELECT 1 FROM productcategory pcsub JOIN (SELECT start_id, main_id FROM category_main_map WHERE temp_id IS NULL) AS simmain ON pcsub.category_id = simmain.start_id
  WHERE pcsub.product_id = sim.product_id AND simmain.main_id = main.main_id
);


-- Q11: Welche Produkte werden in allen Filialen angeboten? Hinweis: Ihre Query muss so formuliert werden, dass sie für eine beliebige Anzahl von Filialen funktioniert. Hinweis: Beachten Sie, dass ein Produkt mehrfach von einer Filiale angeboten werden kann (z.B. neu und gebraucht).
SELECT o.product_id
FROM Offer o
GROUP BY o.product_id
HAVING COUNT(DISTINCT o.store_id) = (SELECT COUNT(*) FROM Store);


-- Q12: In wieviel Prozent der Fälle der Frage 11 gibt es in Leipzig das preiswerteste Angebot?
SELECT (SELECT COUNT(DISTINCT o.product_id) 
FROM Offer o JOIN Store s ON o.store_id = s.store_id
WHERE s.name = 'Leipzig' AND -- in leipzig
  -- wird in allen stores verkauft?
  (
    SELECT COUNT(DISTINCT store_id) 
  FROM Offer 
  WHERE product_id = o.product_id
  ) = (
    SELECT COUNT(DISTINCT store_id) 
    FROM Offer
  )
  -- günstigste store_ids enthalten store_id von leipzig?
  AND o.store_id = any
  (
    -- store ids der günstigsten offers für ein produkt
    SELECT store_id 
    FROM Offer sub
    WHERE sub.product_id = o.product_id AND sub.price_cents IS NOT NULL AND
    sub.price_cents <= all (
      SELECT price_cents 
      FROM Offer 
      WHERE product_id = sub.product_id AND price_cents IS NOT NULL
    )
  )) * 100.0 / (
    -- durch die anzahl aus aufgabe 11
    SELECT COUNT(DISTINCT o.product_id) 
    FROM Offer o
    WHERE (
        SELECT COUNT(DISTINCT store_id) FROM Offer WHERE product_id = o.product_id
      ) = (
        SELECT COUNT(DISTINCT store_id) FROM Offer
      )
  );


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
                COUNT(*)::INT                       AS cnt,
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
                COUNT(*)::INT                       AS cnt,
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
