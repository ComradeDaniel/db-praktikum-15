package Aufgabe1;

import Aufgabe1.models.Customer;
import Aufgabe1.models.Review;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Liest reviews.csv und erzeugt Review- und Customer-Objekte.
 *
 * Cleanup gemäß Offene-Fragen.md:
 *  - 2g: user "guest" wird auf synthetische Usernames (guest-000001, ...) gemappt,
 *        damit nicht alle anonymen Reviews auf einen Customer kollabieren. Reale User
 *        mit gleichem Namen werden zu einem Customer zusammengefasst.
 *  - 2b/2c: Felder werden getrimmt, Leerstrings werden zu null.
 *  - 2f: Reviews mit Datum in der Zukunft werden abgelehnt (Schema-CHECK review_date <= heute).
 *  - 2h: identische Reviews (product, user, datum, content) werden nur einmal übernommen.
 *
 * CSV-Annahme (verifiziert): UTF-8, ein Record pro Zeile, Felder in " gequotet,
 * keine ""-Escapes (Quotes im Text sind HTML-codiert als &quot;).
 */
public class ReviewCsvReader {

    public static class Result {
        public final List<Review> reviews;
        public final Collection<Customer> customers;

        Result(List<Review> reviews, Collection<Customer> customers) {
            this.reviews = reviews;
            this.customers = customers;
        }
    }

    public static Result read(File csvFile, List<String> errors) throws IOException {
        List<Review> reviews = new ArrayList<>();
        Map<String, Customer> customersByName = new LinkedHashMap<>();
        Set<String> seenReviewKeys = new HashSet<>();
        int guestCounter = 0;

        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
        dateFmt.setLenient(false);
        Date now = new Date();

        try (BufferedReader br = Files.newBufferedReader(csvFile.toPath(), StandardCharsets.UTF_8)) {
            br.readLine(); // Header überspringen
            String line;
            int lineNo = 1;
            while ((line = br.readLine()) != null) {
                lineNo++;
                String[] f = parseCsvLine(line);
                if (f.length < 7) {
                    errors.add(error("Review", "*", "erwartet 7 Felder, gefunden " + f.length, lineNo));
                    continue;
                }

                String productId  = trimToNull(f[0]);
                String ratingRaw  = trimToNull(f[1]);
                String helpfulRaw = trimToNull(f[2]);
                String dateRaw    = trimToNull(f[3]);
                String userRaw    = trimToNull(f[4]);
                String summary    = decodeEntities(trimToNull(f[5]));
                String content    = decodeEntities(trimToNull(f[6]));

                // 2h: Duplikat-Erkennung über die ORIGINAL-Werte (vor Guest-Synthese)
                String dupKey = productId + "" + userRaw + "" + dateRaw + "" + content;
                if (!seenReviewKeys.add(dupKey)) {
                    errors.add(error("Review", "*", "Duplikat (product=" + productId + ", user=" + userRaw + ")", lineNo));
                    continue;
                }

                if (productId == null) {
                    errors.add(error("Review", "product", "fehlende Produkt-ID (ASIN)", lineNo));
                    continue;
                }

                // score: NOT NULL, 1..5
                int score;
                try {
                    score = Integer.parseInt(ratingRaw);
                } catch (NumberFormatException e) {
                    errors.add(error("Review", "score", "kein gültiger Integer: '" + ratingRaw + "'", lineNo));
                    continue;
                }
                if (score < 1 || score > 5) {
                    errors.add(error("Review", "score", "Wert " + score + " außerhalb 1..5", lineNo));
                    continue;
                }

                // helpful: Domain-Typ int -> bei fehlendem/ungültigem Wert Default 0
                int helpful = 0;
                if (helpfulRaw != null) {
                    try {
                        helpful = Integer.parseInt(helpfulRaw);
                    } catch (NumberFormatException e) {
                        errors.add(error("Review", "helpful", "kein gültiger Integer: '" + helpfulRaw + "', auf 0 gesetzt", lineNo));
                    }
                }

                // reviewDate: nullable; Zukunft -> ablehnen (2f); Parse-Fehler -> null + Log
                Date reviewDate = null;
                if (dateRaw != null) {
                    try {
                        reviewDate = dateFmt.parse(dateRaw);
                        if (reviewDate.after(now)) {
                            errors.add(error("Review", "reviewDate", "Datum in der Zukunft: " + dateRaw, lineNo));
                            continue;
                        }
                    } catch (ParseException e) {
                        errors.add(error("Review", "reviewDate", "unlesbares Datum: '" + dateRaw + "', auf null gesetzt", lineNo));
                        reviewDate = null;
                    }
                }

                // Customer auflösen: guest -> eigener synthetischer Eintrag; sonst nach Username dedupliziert
                Customer customer;
                if (userRaw == null || userRaw.equalsIgnoreCase("guest")) {
                    guestCounter++;
                    String synth = String.format("guest-%06d", guestCounter);
                    customer = new Customer(synth, null, null);
                    customersByName.put(synth, customer);
                } else {
                    customer = customersByName.get(userRaw);
                    if (customer == null) {
                        customer = new Customer(userRaw, null, null);
                        customersByName.put(userRaw, customer);
                    }
                }

                reviews.add(new Review(productId, customer, score, helpful, reviewDate, summary, content));
            }
        }

        return new Result(reviews, customersByName.values());
    }

    /**
     * Dekodiert die in den Daten vorkommenden HTML-Entities. &amp; wird zuletzt
     * ersetzt, damit z.B. "&amp;lt;" korrekt zu "&lt;" (Literal) und nicht zu "<" wird.
     */
    private static String decodeEntities(String s) {
        if (s == null) return null;
        return s.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "\"")
                .replace("&amp;", "&");
    }

    private static String trimToNull(String s) {
        if (s == null) return null;
        s = s.trim();
        return s.isEmpty() ? null : s;
    }

    private static String error(String entity, String attribute, String message, int lineNo) {
        return String.format("ERROR: %s, %s, %s (Zeile %d)", entity, attribute, message, lineNo);
    }

    /**
     * Quote-bewusster Parser für eine CSV-Zeile. Felder optional in " eingeschlossen,
     * Komma trennt nur außerhalb von Quotes. Keine ""-Escapes (siehe Klassen-Doku).
     */
    private static String[] parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean inQuotes = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(c);
            }
        }
        fields.add(cur.toString());
        return fields.toArray(new String[0]);
    }
}
