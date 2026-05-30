package Aufgabe1;

import Aufgabe1.models.*;
import Aufgabe1.utility.HydrationErrorHolder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Schreibt die hydrierten Domain-Objekte per JDBC in die DB: eine Transaktion, FK-Reihenfolge
public class DataLoader {

    // Reihenfolge fuer TRUNCATE: umgekehrt zur Abhaengigkeit (Kinder vor Eltern)
    private static final String[] MANAGED_TABLES = {
            "Review", "Offer", "ProductCategory", "ProductListmania", "DVDLanguage",
            "DVDStudio", "CDLabel", "CDArtist", "DVDPerson", "BookAuthor", "Track",
            "MusicCD", "DVD", "Book", "Product", "Category", "ListmaniaList",
            "Customer", "Studio", "Label", "Publisher", "Person"
    };

    public static void load(
            Connection conn,
            Set<Person> persons,
            Set<Publisher> publishers,
            Set<Label> labels,
            Set<Studio> studios,
            Set<ListmaniaList> listmaniaLists,
            Collection<Customer> customers,
            List<Category> categories,
            Map<String, Product> products,
            Map<Person, HashSet<String>> personProductIndex,
            Map<Publisher, HashSet<String>> publisherProductIndex,
            Map<Label, HashSet<String>> labelProductIndex,
            Map<Studio, HashSet<String>> studioProductIndex,
            Map<ListmaniaList, HashSet<String>> listmaniaProductIndex,
            Map<DVD.DVDLanguage, HashSet<String>> dvdLanguageProductIndex,
            List<Review> reviews
    ) throws SQLException {
        boolean previousAutoCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);
        try {
            truncateAll(conn); // erst leeren -> Re-Run ist idempotent

            insertNamedDimension(conn, "Person", persons.stream().map(Person::getName).toList());
            insertNamedDimension(conn, "Publisher", publishers.stream().map(Publisher::getName).toList());
            insertNamedDimension(conn, "Label", labels.stream().map(Label::getName).toList());
            insertNamedDimension(conn, "Studio", studios.stream().map(Studio::getName).toList());
            insertNamedDimension(conn, "ListmaniaList", listmaniaLists.stream().map(ListmaniaList::getName).toList());

            insertCustomers(conn, customers);
            insertCategories(conn, categories);

            Map<String, String> asinToPublisher = buildAsinToPublisher(publisherProductIndex);
            insertProducts(conn, products, asinToPublisher);

            // m:n-Tabellen; product_ids werden gegen geladene Produkte gefiltert (FK)
            insertPersonLinks(conn, personProductIndex, products);
            insertDvdStudio(conn, studioProductIndex, products);
            insertCdLabel(conn, labelProductIndex, products);
            insertDvdLanguage(conn, dvdLanguageProductIndex, products);
            insertProductListmania(conn, listmaniaProductIndex, products);
            insertProductCategory(conn, categories, products);

            insertReviews(conn, reviews, products);
            updateProductRatings(conn); // avg_rating/num_reviews aus geladenen Reviews nachziehen

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(previousAutoCommit);
        }
    }

    private static void truncateAll(Connection conn) throws SQLException {
        String sql = "TRUNCATE TABLE " + String.join(", ", MANAGED_TABLES) + " RESTART IDENTITY CASCADE";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    // name ist PK; nach Namen dedupliziert, da DVDPerson denselben Namen mehrfach (versch. Rolle) liefert
    private static void insertNamedDimension(Connection conn, String table, List<String> names) throws SQLException {
        String sql = "INSERT INTO " + table + " (name) VALUES (?)";
        Set<String> seen = new HashSet<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (String name : names) {
                if (!seen.add(name)) {
                    continue;
                }
                ps.setString(1, name);
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void insertCustomers(Connection conn, Collection<Customer> customers) throws SQLException {
        String sql = "INSERT INTO Customer (username, delivery_address, account_number) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Customer c : customers) {
                ps.setString(1, c.getUsername());
                ps.setString(2, c.getDeliveryAddress());
                ps.setString(3, c.getAccount_number());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    // nach id sortiert einfuegen -> Eltern stehen vor Kindern (FK parent_id)
    private static void insertCategories(Connection conn, List<Category> categories) throws SQLException {
        String sql = "INSERT INTO Category (category_id, name, parent_id) VALUES (?, ?, ?)";
        List<Category> sorted = categories.stream()
                .sorted((a, b) -> Integer.compare(a.getId(), b.getId()))
                .toList();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Category c : sorted) {
                ps.setInt(1, c.getId());
                ps.setString(2, c.getName());
                if (c.getParentId() == 0) {
                    ps.setNull(3, Types.INTEGER);
                } else {
                    ps.setInt(3, c.getParentId());
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    // Schema erlaubt nur einen Verlag je Buch; bei mehreren deterministisch den alphabetisch ersten
    private static Map<String, String> buildAsinToPublisher(Map<Publisher, HashSet<String>> publisherProductIndex) {
        Map<String, String> asinToPublisher = new HashMap<>();
        publisherProductIndex.entrySet().stream()
                .sorted((a, b) -> a.getKey().getName().compareTo(b.getKey().getName()))
                .forEach(e -> {
                    for (String asin : e.getValue()) {
                        asinToPublisher.putIfAbsent(asin, e.getKey().getName());
                    }
                });
        return asinToPublisher;
    }

    private static void insertProducts(Connection conn, Map<String, Product> products,
                                       Map<String, String> asinToPublisher) throws SQLException {
        String productSql = "INSERT INTO Product " +
                "(product_id, title, sales_rank, image_url, ean, detail_url, avg_rating, num_reviews, product_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(productSql)) {
            for (Product p : products.values()) {
                ps.setString(1, p.getAsin());
                ps.setString(2, p.getTitle());
                setNullableInt(ps, 3, p.getSalesRank());
                ps.setString(4, p.getImageURL());
                ps.setString(5, p.getEan());
                ps.setString(6, p.getDetailURL());
                setNullableFloat(ps, 7, p.getAvgRating());
                ps.setInt(8, p.getNumReviews() == null ? 0 : p.getNumReviews());
                ps.setString(9, productType(p));
                ps.addBatch();
            }
            ps.executeBatch();
        }

        insertBooks(conn, products, asinToPublisher);
        insertDvds(conn, products);
        insertMusicCds(conn, products);
        insertTracks(conn, products);
    }

    private static void insertBooks(Connection conn, Map<String, Product> products,
                                    Map<String, String> asinToPublisher) throws SQLException {
        String sql = "INSERT INTO Book " +
                "(product_id, isbn, page_count, release_date, binding, edition, " +
                " package_weight, package_height, package_length, publisher) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Product p : products.values()) {
                if (!(p instanceof Book b)) continue;
                ps.setString(1, b.getAsin());
                ps.setString(2, b.getIsbn());
                setNullableInt(ps, 3, b.getPageCount());
                setNullableDate(ps, 4, b.getReleaseDate());
                ps.setString(5, b.getBinding());
                ps.setString(6, b.getEdition());
                setNullableInt(ps, 7, b.getPackageWeight());
                setNullableInt(ps, 8, b.getPackageHeight());
                setNullableInt(ps, 9, b.getPackageLength());
                ps.setString(10, asinToPublisher.get(b.getAsin()));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void insertDvds(Connection conn, Map<String, Product> products) throws SQLException {
        String sql = "INSERT INTO DVD " +
                "(product_id, format, runtime, region_code, release_date, " +
                " theatrical_release, aspect_ratio, audio_format, upc) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Product p : products.values()) {
                if (!(p instanceof DVD d)) continue;
                ps.setString(1, d.getAsin());
                ps.setString(2, d.getFormat());
                setNullableInt(ps, 3, d.getRuntime());
                setNullableInt(ps, 4, d.getRegionCode());
                setNullableDate(ps, 5, d.getReleaseDate());
                setNullableInt(ps, 6, d.getTheatricalRelease());
                ps.setString(7, d.getAspectRatio());
                ps.setString(8, d.getAudioFormat());
                ps.setString(9, d.getUpc());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void insertMusicCds(Connection conn, Map<String, Product> products) throws SQLException {
        String sql = "INSERT INTO MusicCD " +
                "(product_id, release_date, binding, format, num_discs, upc) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Product p : products.values()) {
                if (!(p instanceof MusicCD m)) continue;
                ps.setString(1, m.getAsin());
                setNullableDate(ps, 2, m.getReleaseDate());
                ps.setString(3, m.getBinding());
                ps.setString(4, m.getFormat());
                setNullableInt(ps, 5, m.getNumDiscs());
                ps.setString(6, m.getUpc());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void insertTracks(Connection conn, Map<String, Product> products) throws SQLException {
        String sql = "INSERT INTO Track (product_id, track_no, name) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Product p : products.values()) {
                if (!(p instanceof MusicCD m) || m.getTracks() == null) continue;
                for (MusicCD.Track t : m.getTracks()) {
                    ps.setString(1, m.getAsin());
                    ps.setInt(2, t.getTrackNumber());
                    ps.setString(3, t.getTrackName());
                    ps.addBatch();
                }
            }
            ps.executeBatch();
        }
    }

    // Routing nach Typ/Rolle: DVDPerson->DVDPerson(role), Person an Buch->BookAuthor, an CD->CDArtist
    private static void insertPersonLinks(Connection conn,
                                          Map<Person, HashSet<String>> personProductIndex,
                                          Map<String, Product> products) throws SQLException {
        String bookSql = "INSERT INTO BookAuthor (product_id, person) VALUES (?, ?)";
        String cdSql = "INSERT INTO CDArtist (product_id, person) VALUES (?, ?)";
        String dvdSql = "INSERT INTO DVDPerson (product_id, person, role) VALUES (?, ?, ?)";
        try (PreparedStatement bookPs = conn.prepareStatement(bookSql);
             PreparedStatement cdPs = conn.prepareStatement(cdSql);
             PreparedStatement dvdPs = conn.prepareStatement(dvdSql)) {
            for (Map.Entry<Person, HashSet<String>> e : personProductIndex.entrySet()) {
                Person person = e.getKey();
                for (String asin : e.getValue()) {
                    Product p = products.get(asin);
                    if (p == null) continue;
                    if (person instanceof DVD.DVDPerson dp) {
                        if (p instanceof DVD) {
                            dvdPs.setString(1, asin);
                            dvdPs.setString(2, dp.getName());
                            dvdPs.setString(3, dp.getRole());
                            dvdPs.addBatch();
                        }
                    } else if (p instanceof Book) {
                        bookPs.setString(1, asin);
                        bookPs.setString(2, person.getName());
                        bookPs.addBatch();
                    } else if (p instanceof MusicCD) {
                        cdPs.setString(1, asin);
                        cdPs.setString(2, person.getName());
                        cdPs.addBatch();
                    }
                }
            }
            bookPs.executeBatch();
            cdPs.executeBatch();
            dvdPs.executeBatch();
        }
    }

    private static void insertDvdStudio(Connection conn, Map<Studio, HashSet<String>> studioProductIndex,
                                        Map<String, Product> products) throws SQLException {
        String sql = "INSERT INTO DVDStudio (product_id, studio) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Map.Entry<Studio, HashSet<String>> e : studioProductIndex.entrySet()) {
                for (String asin : e.getValue()) {
                    if (products.get(asin) instanceof DVD) {
                        ps.setString(1, asin);
                        ps.setString(2, e.getKey().getName());
                        ps.addBatch();
                    }
                }
            }
            ps.executeBatch();
        }
    }

    private static void insertCdLabel(Connection conn, Map<Label, HashSet<String>> labelProductIndex,
                                      Map<String, Product> products) throws SQLException {
        String sql = "INSERT INTO CDLabel (product_id, label) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Map.Entry<Label, HashSet<String>> e : labelProductIndex.entrySet()) {
                for (String asin : e.getValue()) {
                    if (products.get(asin) instanceof MusicCD) {
                        ps.setString(1, asin);
                        ps.setString(2, e.getKey().getName());
                        ps.addBatch();
                    }
                }
            }
            ps.executeBatch();
        }
    }

    private static void insertDvdLanguage(Connection conn, Map<DVD.DVDLanguage, HashSet<String>> index,
                                          Map<String, Product> products) throws SQLException {
        String sql = "INSERT INTO DVDLanguage (product_id, language, language_type) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Map.Entry<DVD.DVDLanguage, HashSet<String>> e : index.entrySet()) {
                DVD.DVDLanguage lang = e.getKey();
                for (String asin : e.getValue()) {
                    if (products.get(asin) instanceof DVD) {
                        ps.setString(1, asin);
                        ps.setString(2, lang.getLanguage());
                        ps.setString(3, lang.getLanguageType());
                        ps.addBatch();
                    }
                }
            }
            ps.executeBatch();
        }
    }

    private static void insertProductListmania(Connection conn, Map<ListmaniaList, HashSet<String>> index,
                                               Map<String, Product> products) throws SQLException {
        String sql = "INSERT INTO ProductListmania (product_id, list) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Map.Entry<ListmaniaList, HashSet<String>> e : index.entrySet()) {
                for (String asin : e.getValue()) {
                    if (products.containsKey(asin)) {
                        ps.setString(1, asin);
                        ps.setString(2, e.getKey().getName());
                        ps.addBatch();
                    }
                }
            }
            ps.executeBatch();
        }
    }

    private static void insertProductCategory(Connection conn, List<Category> categories,
                                              Map<String, Product> products) throws SQLException {
        String sql = "INSERT INTO ProductCategory (product_id, category_id) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Category c : categories) {
                if (c.getProductIDs() == null) continue;
                Set<String> seen = new HashSet<>();
                for (String asin : c.getProductIDs()) {
                    if (products.containsKey(asin) && seen.add(asin)) {
                        ps.setString(1, asin);
                        ps.setInt(2, c.getId());
                        ps.addBatch();
                    }
                }
            }
            ps.executeBatch();
        }
    }

    private static void insertReviews(Connection conn, List<Review> reviews,
                                      Map<String, Product> products) throws SQLException {
        String sql = "INSERT INTO Review (product_id, username, score, helpful, review_date, summary, content) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Review r : reviews) {
                if (!products.containsKey(r.getProductId())) continue;
                ps.setString(1, r.getProductId());
                ps.setString(2, r.getCustomer() == null ? null : r.getCustomer().getUsername());
                ps.setInt(3, r.getScore());
                ps.setInt(4, r.getHelpful());
                if (r.getReviewDate() == null) {
                    ps.setNull(5, Types.DATE);
                } else {
                    ps.setDate(5, new Date(r.getReviewDate().getTime()));
                }
                ps.setString(6, r.getSummary());
                ps.setString(7, r.getContent());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    // Schreibt die gesammelten Fehler (Hydration + CSV) in die LoadError-Tabelle
    public static void writeLoadErrors(Connection conn, HydrationErrorHolder hydrationErrors,
                                       List<String> reviewErrors) throws SQLException {
        boolean previousAutoCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);
        try {
            try (Statement st = conn.createStatement()) {
                st.execute("TRUNCATE TABLE LoadError RESTART IDENTITY");
            }
            String sql = "INSERT INTO LoadError (entity, attribute, value, reason, source_file, source_line) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Map.Entry<String, List<String>> e : hydrationErrors.entrySet()) {
                    String key = e.getKey();
                    String entity = (key != null && key.startsWith("Category#")) ? "Category" : "Product";
                    for (String msg : e.getValue()) {
                        ps.setString(1, entity);
                        ps.setNull(2, Types.VARCHAR);
                        ps.setString(3, key);
                        ps.setString(4, msg);
                        ps.setNull(5, Types.VARCHAR);
                        ps.setNull(6, Types.INTEGER);
                        ps.addBatch();
                    }
                }
                // vorformatierte CSV-Fehler "ERROR: entity, attribut, grund (Zeile N)" zerlegen
                for (String err : reviewErrors) {
                    String entity = "Review";
                    String attribute = null;
                    String reason = err;
                    Integer line = null;
                    String s = err.startsWith("ERROR: ") ? err.substring(7) : err;
                    int idx = s.lastIndexOf("(Zeile ");
                    if (idx >= 0 && s.endsWith(")")) {
                        String num = s.substring(idx + 7, s.length() - 1).trim();
                        try {
                            line = Integer.parseInt(num);
                        } catch (NumberFormatException ignore) {
                        }
                        s = s.substring(0, idx).trim();
                    }
                    String[] parts = s.split(", ", 3);
                    if (parts.length == 3) {
                        entity = parts[0];
                        attribute = parts[1];
                        reason = parts[2];
                    }
                    ps.setString(1, entity);
                    if (attribute == null) {
                        ps.setNull(2, Types.VARCHAR);
                    } else {
                        ps.setString(2, attribute);
                    }
                    ps.setNull(3, Types.VARCHAR);
                    ps.setString(4, reason);
                    ps.setString(5, "reviews.csv");
                    if (line == null) {
                        ps.setNull(6, Types.INTEGER);
                    } else {
                        ps.setInt(6, line);
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(previousAutoCommit);
        }
    }

    private static void updateProductRatings(Connection conn) throws SQLException {
        String sql = "UPDATE Product p SET num_reviews = r.cnt, avg_rating = r.avg FROM ("
                + "SELECT product_id, COUNT(*) AS cnt, ROUND(AVG(score)::numeric, 2) AS avg "
                + "FROM Review GROUP BY product_id) r WHERE p.product_id = r.product_id";
        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
        }
    }

    private static String productType(Product p) {
        if (p instanceof Book) return "Book";
        if (p instanceof DVD) return "DVD";
        if (p instanceof MusicCD) return "MusicCD";
        throw new IllegalArgumentException("Unbekannter Produkttyp: " + p.getClass().getSimpleName());
    }

    private static void setNullableInt(PreparedStatement ps, int idx, Integer value) throws SQLException {
        if (value == null) {
            ps.setNull(idx, Types.INTEGER);
        } else {
            ps.setInt(idx, value);
        }
    }

    private static void setNullableFloat(PreparedStatement ps, int idx, Float value) throws SQLException {
        if (value == null) {
            ps.setNull(idx, Types.NUMERIC);
        } else {
            ps.setBigDecimal(idx, new java.math.BigDecimal(value.toString()));
        }
    }

    private static void setNullableDate(PreparedStatement ps, int idx, LocalDate value) throws SQLException {
        if (value == null) {
            ps.setNull(idx, Types.DATE);
        } else {
            ps.setDate(idx, Date.valueOf(value));
        }
    }
}
