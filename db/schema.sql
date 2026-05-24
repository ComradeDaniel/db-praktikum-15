-- =====================================================================
-- Media Store — Datenbankschema (Teil 1b)
-- PostgreSQL 16+
--
-- Reihenfolge: Dimensionen → Produkthierarchie → m:n-Beziehungen
--            → Geschäftsprozesse → Indexe
--
-- Re-runnable: löscht alle Tabellen vor der Neuanlage.
-- =====================================================================


-- ---------------------------------------------------------------------
-- DROP-Phase (umgekehrte Abhängigkeitsreihenfolge)
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS LoadError        CASCADE;
DROP TABLE IF EXISTS BasketItem       CASCADE;
DROP TABLE IF EXISTS Basket           CASCADE;
DROP TABLE IF EXISTS Review           CASCADE;
DROP TABLE IF EXISTS Offer            CASCADE;
DROP TABLE IF EXISTS SimilarProduct   CASCADE;
DROP TABLE IF EXISTS ProductCategory  CASCADE;
DROP TABLE IF EXISTS ProductListmania CASCADE;
DROP TABLE IF EXISTS DVDLanguage      CASCADE;
DROP TABLE IF EXISTS DVDStudio        CASCADE;
DROP TABLE IF EXISTS CDArtist         CASCADE;
DROP TABLE IF EXISTS DVDPerson        CASCADE;
DROP TABLE IF EXISTS BookAuthor       CASCADE;
DROP TABLE IF EXISTS Track            CASCADE;
DROP TABLE IF EXISTS MusicCD          CASCADE;
DROP TABLE IF EXISTS DVD              CASCADE;
DROP TABLE IF EXISTS Book             CASCADE;
DROP TABLE IF EXISTS Product          CASCADE;
DROP TABLE IF EXISTS Category         CASCADE;
DROP TABLE IF EXISTS ListmaniaList    CASCADE;
DROP TABLE IF EXISTS Customer         CASCADE;
DROP TABLE IF EXISTS Studio           CASCADE;
DROP TABLE IF EXISTS Label            CASCADE;
DROP TABLE IF EXISTS Publisher        CASCADE;
DROP TABLE IF EXISTS Person           CASCADE;
DROP TABLE IF EXISTS Store            CASCADE;


-- =====================================================================
-- 1. Dimensionstabellen (keine ausgehenden FKs)
-- =====================================================================

CREATE TABLE Store (
    store_id    SERIAL      PRIMARY KEY,
    name        TEXT        NOT NULL,
    street      TEXT,
    zip         TEXT
);

CREATE TABLE Person (
    person_id   SERIAL      PRIMARY KEY,
    name        TEXT        NOT NULL UNIQUE
);

CREATE TABLE Publisher (
    publisher_id SERIAL     PRIMARY KEY,
    name         TEXT       NOT NULL UNIQUE
);

CREATE TABLE Label (
    label_id    SERIAL      PRIMARY KEY,
    name        TEXT        NOT NULL UNIQUE
);

CREATE TABLE Studio (
    studio_id   SERIAL      PRIMARY KEY,
    name        TEXT        NOT NULL UNIQUE
);

CREATE TABLE Customer (
    customer_id      SERIAL PRIMARY KEY,
    username         TEXT   NOT NULL UNIQUE,
    delivery_address TEXT,
    account_number   TEXT
);

CREATE TABLE ListmaniaList ( -- brauchen wir das? szenario.md erwähnt das nicht
    list_id     SERIAL      PRIMARY KEY,
    name        TEXT        NOT NULL UNIQUE
);


-- =====================================================================
-- 2. Category (Self-Reference für Hierarchie)
-- =====================================================================

CREATE TABLE Category (
    category_id SERIAL      PRIMARY KEY,
    name        TEXT        NOT NULL,
    parent_id   INT         REFERENCES Category(category_id) ON DELETE CASCADE,
    CONSTRAINT chk_category_no_self_parent CHECK (parent_id IS NULL OR parent_id <> category_id)
);


-- =====================================================================
-- 3. Product (Supertyp) + Subtypen
-- =====================================================================

CREATE TABLE Product (
    product_id   TEXT       PRIMARY KEY,    -- = ASIN
    title        TEXT       NOT NULL,
    sales_rank   INT,
    image_url    TEXT,
    ean          TEXT, -- könnte unsigned bigint sein (kleiner)
    detail_url   TEXT, -- brauchen wir das? szenario.md erwähnt das nicht (generell die Frage, ob wir alle Infos aus dem Datensatz speichern wollen oder nur die wir brauchen)
    avg_rating   NUMERIC(3,2),
    num_reviews  INT        NOT NULL DEFAULT 0,
    product_type TEXT       NOT NULL,
    CONSTRAINT chk_product_rating_range  CHECK (avg_rating IS NULL OR avg_rating BETWEEN 1 AND 5), -- wie streng wollen wir unsere constraints eigentlich machen
    CONSTRAINT chk_product_num_reviews   CHECK (num_reviews >= 0),
    CONSTRAINT chk_product_type          CHECK (product_type IN ('Book','DVD','MusicCD'))
);

CREATE TABLE Book (
    product_id     TEXT     PRIMARY KEY REFERENCES Product(product_id) ON DELETE CASCADE,
    isbn           TEXT     UNIQUE,
    page_count     INT,
    release_date   DATE,
    binding        TEXT, -- brauchen wir das? szenario.md erwähnt das nicht
    edition        TEXT, -- brauchen wir das? szenario.md erwähnt das nicht
    package_weight INT, -- brauchen wir das? szenario.md erwähnt das nicht
    package_height INT, -- brauchen wir das? szenario.md erwähnt das nicht
    package_length INT, -- brauchen wir das? szenario.md erwähnt das nicht
    publisher_id   INT      REFERENCES Publisher(publisher_id),
    CONSTRAINT chk_book_pages         CHECK (page_count     IS NULL OR page_count     > 0),
    CONSTRAINT chk_book_release_date  CHECK (release_date   IS NULL OR release_date  <= CURRENT_DATE), -- evtl. zu streng
    CONSTRAINT chk_book_pkg_weight    CHECK (package_weight IS NULL OR package_weight >= 0), -- würde ich weglassen
    CONSTRAINT chk_book_pkg_height    CHECK (package_height IS NULL OR package_height >= 0), -- würde ich weglassen
    CONSTRAINT chk_book_pkg_length    CHECK (package_length IS NULL OR package_length >= 0) -- würde ich weglassen
);

CREATE TABLE DVD (
    product_id         TEXT  PRIMARY KEY REFERENCES Product(product_id) ON DELETE CASCADE,
    format             TEXT,
    runtime            INT,
    region_code        INT,
    release_date       DATE,
    theatrical_release TEXT,    -- Freitext, kann Jahreszahl oder leer sein -- warum dann nicht smallint?
    aspect_ratio       TEXT,
    audio_format       TEXT,
    upc                TEXT, -- brauchen wir das? szenario.md erwähnt das nicht
    CONSTRAINT chk_dvd_runtime       CHECK (runtime      IS NULL OR runtime > 0),
    CONSTRAINT chk_dvd_region        CHECK (region_code  IS NULL OR region_code BETWEEN 0 AND 8),
    CONSTRAINT chk_dvd_release_date  CHECK (release_date IS NULL OR release_date <= CURRENT_DATE) -- evtl. zu streng
);

CREATE TABLE MusicCD (
    product_id   TEXT       PRIMARY KEY REFERENCES Product(product_id) ON DELETE CASCADE,
    release_date DATE,
    binding      TEXT, -- brauchen wir das? szenario.md erwähnt das nicht
    format       TEXT, -- brauchen wir das? szenario.md erwähnt das nicht
    num_discs    INT, -- brauchen wir das? szenario.md erwähnt das nicht
    upc          TEXT, -- brauchen wir das? szenario.md erwähnt das nicht
    label_id     INT        REFERENCES Label(label_id),
    CONSTRAINT chk_cd_release_date CHECK (release_date IS NULL OR release_date <= CURRENT_DATE), -- evtl. zu streng
    CONSTRAINT chk_cd_num_discs    CHECK (num_discs    IS NULL OR num_discs > 0)
);


-- =====================================================================
-- 4. Track (schwache Entity an MusicCD)
-- =====================================================================

CREATE TABLE Track (
    product_id  TEXT        NOT NULL REFERENCES MusicCD(product_id) ON DELETE CASCADE,
    track_no    INT         NOT NULL,
    name        TEXT        NOT NULL,
    PRIMARY KEY (product_id, track_no),
    CONSTRAINT chk_track_no CHECK (track_no > 0)
);


-- =====================================================================
-- 5. m:n-Verknüpfungstabellen
-- =====================================================================

CREATE TABLE BookAuthor (
    product_id  TEXT        NOT NULL REFERENCES Book(product_id)    ON DELETE CASCADE,
    person_id   INT         NOT NULL REFERENCES Person(person_id)   ON DELETE CASCADE,
    PRIMARY KEY (product_id, person_id)
);

CREATE TABLE DVDPerson (
    product_id  TEXT        NOT NULL REFERENCES DVD(product_id)     ON DELETE CASCADE,
    person_id   INT         NOT NULL REFERENCES Person(person_id)   ON DELETE CASCADE,
    role        TEXT        NOT NULL,
    PRIMARY KEY (product_id, person_id, role),
    CONSTRAINT chk_dvdperson_role CHECK (role IN ('Actor','Creator','Director'))
);

CREATE TABLE CDArtist (
    product_id  TEXT        NOT NULL REFERENCES MusicCD(product_id) ON DELETE CASCADE,
    person_id   INT         NOT NULL REFERENCES Person(person_id)   ON DELETE CASCADE,
    PRIMARY KEY (product_id, person_id)
);

CREATE TABLE DVDStudio (
    product_id  TEXT        NOT NULL REFERENCES DVD(product_id)   ON DELETE CASCADE,
    studio_id   INT         NOT NULL REFERENCES Studio(studio_id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, studio_id)
);

CREATE TABLE DVDLanguage ( -- brauchen wir das? szenario.md erwähnt das nicht
    product_id    TEXT      NOT NULL REFERENCES DVD(product_id) ON DELETE CASCADE,
    language      TEXT      NOT NULL,
    language_type TEXT      NOT NULL,    -- z.B. "Original Language", "Subtitle"
    PRIMARY KEY (product_id, language, language_type)
);

CREATE TABLE ProductListmania ( -- brauchen wir das? szenario.md erwähnt das nicht
    product_id  TEXT        NOT NULL REFERENCES Product(product_id)    ON DELETE CASCADE,
    list_id     INT         NOT NULL REFERENCES ListmaniaList(list_id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, list_id)
);

CREATE TABLE ProductCategory (
    product_id  TEXT        NOT NULL REFERENCES Product(product_id)  ON DELETE CASCADE,
    category_id INT         NOT NULL REFERENCES Category(category_id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, category_id)
);

CREATE TABLE SimilarProduct (
    product_id         TEXT NOT NULL REFERENCES Product(product_id) ON DELETE CASCADE,
    similar_product_id TEXT NOT NULL REFERENCES Product(product_id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, similar_product_id),
    CONSTRAINT chk_similar_no_self CHECK (product_id <> similar_product_id)
);


-- =====================================================================
-- 6. Geschäftsprozesse: Offer, Review, Basket
-- =====================================================================

CREATE TABLE Offer (
    offer_id    SERIAL      PRIMARY KEY,
    store_id    INT         NOT NULL REFERENCES Store(store_id),
    product_id  TEXT        NOT NULL REFERENCES Product(product_id) ON DELETE CASCADE,
    price_cents INT,                    -- NULL = nicht verfügbar
    currency    TEXT,
    condition   TEXT,                   -- z.B. 'new', 'used'
    CONSTRAINT chk_offer_price CHECK (price_cents IS NULL OR price_cents >= 0)
);

CREATE TABLE Review (
    review_id   SERIAL      PRIMARY KEY,
    product_id  TEXT        NOT NULL REFERENCES Product(product_id) ON DELETE CASCADE,
    customer_id INT         REFERENCES Customer(customer_id) ON DELETE SET NULL,
    score       SMALLINT    NOT NULL,
    helpful     INT,
    review_date DATE,
    summary     TEXT,
    content     TEXT,
    CONSTRAINT chk_review_score   CHECK (score BETWEEN 1 AND 5),
    CONSTRAINT chk_review_helpful CHECK (helpful IS NULL OR helpful >= 0),
    CONSTRAINT chk_review_date    CHECK (review_date IS NULL OR review_date <= CURRENT_DATE)
);

-- Basket / BasketItem: Schema-only — keine Daten im aktuellen Dump.
CREATE TABLE Basket (
    basket_id     SERIAL    PRIMARY KEY,
    customer_id   INT       NOT NULL REFERENCES Customer(customer_id),
    purchase_time TIMESTAMP NOT NULL
);

CREATE TABLE BasketItem (
    basket_id   INT         NOT NULL REFERENCES Basket(basket_id) ON DELETE CASCADE,
    product_id  TEXT        NOT NULL REFERENCES Product(product_id),
    PRIMARY KEY (basket_id, product_id)
);


-- =====================================================================
-- 7. Loader-Hilfstabelle für Fehlerprotokoll
-- =====================================================================

CREATE TABLE LoadError ( -- brauchen wir glaube nicht
    error_id    BIGSERIAL   PRIMARY KEY,
    ts          TIMESTAMP   NOT NULL DEFAULT now(),
    entity      TEXT        NOT NULL,
    attribute   TEXT,
    value       TEXT,
    reason      TEXT        NOT NULL,
    source_file TEXT,
    source_line INT
);


-- =====================================================================
-- 8. Indexe (nur für Spalten, die nicht schon durch PK/UNIQUE abgedeckt sind)
-- =====================================================================

-- Häufig gefiltert/joined in Teil-2-Queries und Teil-3-Methoden
CREATE INDEX idx_offer_store_product_cond ON Offer (store_id, product_id, condition);
CREATE INDEX idx_offer_product            ON Offer (product_id);
CREATE INDEX idx_offer_product_price      ON Offer (price_cents ASC);

CREATE INDEX idx_review_product           ON Review (product_id);
CREATE INDEX idx_review_customer          ON Review (customer_id);

CREATE INDEX idx_productcategory_category ON ProductCategory (category_id);

CREATE INDEX idx_category_parent          ON Category (parent_id);

-- Für getProducts(pattern) in Teil 3 (LIKE-Suche)
CREATE INDEX idx_product_title            ON Product (title);

-- Für Q1 / Q2: oft GROUP BY product_type
CREATE INDEX idx_product_type             ON Product USING HASH (product_type);

-- avg_rating sortiert für getTopProducts und Q2
CREATE INDEX idx_product_avg_rating       ON Product (avg_rating DESC);


-- =====================================================================
-- Schema fertig.
-- =====================================================================
