-- 1
select * from (select count(*) as books from book) as b, (select count(*) as dvds from dvd) as d, (select count(*) as cds from musiccd) as m;

-- 2
(select
product_type, product_id, avg_rating 
from product
where avg_rating is not null and product_type = 'Book'
order by avg_rating desc
limit 5)

UNION

(select
product_type, product_id, avg_rating 
from product
where avg_rating is not null and product_type = 'DVD'
order by avg_rating desc
limit 5)

union

(select
product_type, product_id, avg_rating 
from product
where avg_rating is not null and product_type = 'MusicCD'
order by avg_rating desc
limit 5)

order by product_type asc, avg_rating desc;

-- 3
select product_id from product
except
select product_id from offer where available;
-- prüfung
select count (*) from (select product_id from product
except
select product_id from offer where available);

-- 4
select oa.product_id, oa.price_cents as price_left, ob.price_cents as price_right from offer as oa join offer as ob on oa.product_id = ob.product_id
where 
  oa.price_cents is not null and
  ob.price_cents is not null and
  oa.price_cents > 0 and
  ob.price_cents > 0 and
  oa.price_cents > 2 * ob.price_cents;

-- 5
select a.product_id from review as a where
exists (select product_id from review where score = 1 and a.product_id = product_id) and
exists (select product_id from review where score = 5 and a.product_id = product_id);
-- prüfung
select count(distinct product_id) from review as a where
exists (select product_id from review where score = 1 and a.product_id = product_id) and
exists (select product_id from review where score = 5 and a.product_id = product_id);
-- 140 rows

-- 6
select p.product_id from product as p where not exists
(select product_id from review where p.product_id = product_id);
-- prüfung
select count(distinct p.product_id) from product as p where not exists
(select product_id from review where p.product_id = product_id);
-- 1220 rows

-- 7
select username, count(*) from review where username is not null group by username having count(username) > 9;
-- 6 rows

-- 8
select distinct person from bookauthor
where exists (
  select cdartist.person from cdartist where cdartist.person = bookauthor.person
  union
  select dvdperson.person from dvdperson where dvdperson.person = bookauthor.person
);
-- 18 rows

-- 9
-- trackount.orZero()
select avg(coalesce(trackcount, 0)) from
(select musiccd.product_id, trackcount from musiccd left join (select product_id, count(*) as trackcount from track group by product_id) as bla
on bla.product_id = musiccd.product_id);
-- 21.542

-- 10
-- https://www.postgresql.org/docs/current/queries-with.html#QUERIES-WITH-RECURSIVE

-- dass es AUCH in der selben Hauptkategorie sein darf, ist nicht ausgeschlosen?
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

-- 11
-- Annahme: angeboten heißt nicht, dass es verfügbar sein muss sondern dass es ein offer gibt
select o.product_id, store_id from offer o
where (
    select count(distinct store_id) from offer where product_id = o.product_id
  ) = (
    select count(distinct store_id) from offer
  )
  order by product_id;

-- 12
select (select count(distinct o.product_id) from offer o join store s on o.store_id = s.store_id
where s.name = 'Leipzig' and -- in leipzig
  -- wird in allen stores verkauft?
  (
    select count(distinct store_id) from offer where product_id = o.product_id
  ) = (
    select count(distinct store_id) from offer
  )
  -- günstigste store_ids enthalten store_id von leipzig?
  and o.store_id = any
  (
    -- store ids der günstigsten offers für ein produkt
    select store_id from offer sub
    where sub.product_id = o.product_id and sub.price_cents is not null and
    sub.price_cents <= all (
      select price_cents from offer where product_id = sub.product_id and
      price_cents is not null
    )
  )) * 100.0 / (
    -- durch die anzahl aus aufgabe 11
    select count(distinct o.product_id) from offer o
    where (
        select count(distinct store_id) from offer where product_id = o.product_id
      ) = (
        select count(distinct store_id) from offer
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
