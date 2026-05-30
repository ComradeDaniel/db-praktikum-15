package Aufgabe1;

import Aufgabe1.models.*;
import Aufgabe1.models.MusicCD.Track;
import Aufgabe1.utility.HydrationUtils;
import Aufgabe1.utility.HydrationErrorHolder;
import dresden.*;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Mapping der dresden.xml (ShopType/ItemType) auf die Domain-Modelle, analog LeipzigHydrator
// Dresden-Eigenheiten: Listen sind List<String>, Bild/URL in DetailsType, Sprachen als LanguageType
public class DresdenHydrator {

    public static HashSet<Studio> hydrateToStudios(
            ShopType shop,
            HashSet<Studio> out,
            HashMap<Studio, HashSet<String>> studioProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (ItemType product : shop.getItem()) {
            if (!"DVD".equals(product.getPgroup())) {
                continue;
            }
            StudiosType studios = product.getStudios();
            if (studios == null || studios.getStudio() == null || studios.getStudio().isEmpty()) {
                continue;
            }
            String asin = product.getAsin();
            for (String studioName : studios.getStudio()) {
                if (studioName == null || studioName.isBlank()) {
                    hydrationErrors.add(asin, "Empty studio name");
                    continue;
                }
                Studio domainStudio = new Studio(studioName);
                indexAdd(studioProductIndex, domainStudio, asin);
                out.add(domainStudio);
            }
        }

        return out;
    }

    public static HashSet<Person> hydrateToPersons(
            ShopType shop,
            HashSet<Person> out,
            HashMap<Person, HashSet<String>> personProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (ItemType product : shop.getItem()) {
            String asin = product.getAsin();
            switch (product.getPgroup()) {
                case "DVD": {
                    addPersons(product.getActors() == null ? null : product.getActors().getActor(),
                            asin, "Actor", true, out, personProductIndex, hydrationErrors);
                    addPersons(product.getCreators() == null ? null : product.getCreators().getCreator(),
                            asin, "Creator", true, out, personProductIndex, hydrationErrors);
                    addPersons(product.getDirectors() == null ? null : product.getDirectors().getDirector(),
                            asin, "Director", true, out, personProductIndex, hydrationErrors);
                    break;
                }
                case "Music": {
                    boolean before = personProductIndexContains(personProductIndex, asin);
                    addPersons(product.getArtists() == null ? null : product.getArtists().getArtist(),
                            asin, null, false, out, personProductIndex, hydrationErrors);
                    addPersons(product.getCreators() == null ? null : product.getCreators().getCreator(),
                            asin, null, false, out, personProductIndex, hydrationErrors);
                    if (!before && !personProductIndexContains(personProductIndex, asin)) {
                        hydrationErrors.add(asin, "No artist or creator specification found");
                    }
                    break;
                }
                case "Book": {
                    List<String> authors = product.getAuthors() == null ? null : product.getAuthors().getAuthor();
                    if (authors == null || authors.isEmpty()) {
                        hydrationErrors.add(asin, "No author specification found");
                        break;
                    }
                    addPersons(authors, asin, null, false, out, personProductIndex, hydrationErrors);
                    break;
                }
                default:
            }
        }
        return out;
    }

    public static HashSet<Publisher> hydrateToPublishers(
            ShopType shop,
            HashSet<Publisher> out,
            HashMap<Publisher, HashSet<String>> publisherProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (ItemType product : shop.getItem()) {
            if (!"Book".equals(product.getPgroup())) {
                continue;
            }
            PublishersType publishers = product.getPublishers();
            if (publishers == null || publishers.getPublisher() == null || publishers.getPublisher().isEmpty()) {
                hydrationErrors.add(product.getAsin(), "No publisher specification found");
                continue;
            }
            String asin = product.getAsin();
            for (String name : publishers.getPublisher()) {
                if (name == null || name.isBlank()) {
                    hydrationErrors.add(asin, "Empty publisher name");
                    continue;
                }
                Publisher domainPublisher = new Publisher(name);
                indexAdd(publisherProductIndex, domainPublisher, asin);
                out.add(domainPublisher);
            }
        }
        return out;
    }

    public static HashSet<Label> hydrateToLabels(
            ShopType shop,
            HashSet<Label> out,
            HashMap<Label, HashSet<String>> labelProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (ItemType product : shop.getItem()) {
            if (!"Music".equals(product.getPgroup())) {
                continue;
            }
            LabelsType labels = product.getLabels();
            if (labels == null || labels.getLabel() == null || labels.getLabel().isEmpty()) {
                hydrationErrors.add(product.getAsin(), "No label specification found");
                continue;
            }
            String asin = product.getAsin();
            for (String name : labels.getLabel()) {
                if (name == null || name.isBlank()) {
                    hydrationErrors.add(asin, "Empty label name");
                    continue;
                }
                Label domainLabel = new Label(name);
                indexAdd(labelProductIndex, domainLabel, asin);
                out.add(domainLabel);
            }
        }
        return out;
    }

    public static HashSet<ListmaniaList> hydrateToListmania(
            ShopType shop,
            HashSet<ListmaniaList> out,
            HashMap<ListmaniaList, HashSet<String>> listmaniaProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (ItemType product : shop.getItem()) {
            ListmaniaType listmania = product.getListmania();
            if (listmania == null || listmania.getList() == null || listmania.getList().isEmpty()) {
                continue;
            }
            String asin = product.getAsin();
            for (String name : listmania.getList()) {
                if (name == null || name.isBlank()) {
                    hydrationErrors.add(asin, "Empty listmania name");
                    continue;
                }
                ListmaniaList domainListmania = new ListmaniaList(name);
                indexAdd(listmaniaProductIndex, domainListmania, asin);
                out.add(domainListmania);
            }
        }
        return out;
    }

    public static HashSet<DVD.DVDLanguage> hydrateToDVDLanguages(
            ShopType shop,
            HashSet<DVD.DVDLanguage> out,
            HashMap<DVD.DVDLanguage, HashSet<String>> dvdlanguageProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (ItemType product : shop.getItem()) {
            if (!"DVD".equals(product.getPgroup())) {
                continue;
            }
            AudiotextType audiotext = product.getAudiotext();
            if (audiotext == null || audiotext.getLanguage() == null || audiotext.getLanguage().isEmpty()) {
                continue;
            }
            String asin = product.getAsin();
            for (LanguageType lang : audiotext.getLanguage()) {
                String type = lang.getType();
                String value = lang.getValue();
                if (type == null || type.isBlank()) {
                    hydrationErrors.add(asin, "Blank language type");
                    continue;
                }
                if (value == null || value.isBlank()) {
                    hydrationErrors.add(asin, "Empty audiotext language");
                    continue;
                }
                // DVDLanguage(language, languageType): value = Sprache, type = Art
                DVD.DVDLanguage newLanguage = new DVD.DVDLanguage(value, type);
                out.add(newLanguage);
                indexAdd(dvdlanguageProductIndex, newLanguage, asin);
            }
        }
        return out;
    }

    public static HashMap<String, Product> hydrateToProducts(
            ShopType shop,
            HashMap<String, Product> out,
            HydrationErrorHolder hydrationErrors
    ) {
        for (ItemType product : shop.getItem()) {
            switch (product.getPgroup()) {
                case "Book": {
                    BookspecType bookspec = product.getBookspec();
                    if (bookspec == null) {
                        hydrationErrors.add(product.getAsin(), "No book specification found");
                        break;
                    }
                    Book book = hydrateBook(bookspec, hydrateProduct(product, new Book(), hydrationErrors), hydrationErrors);
                    putProduct(out, product.getAsin(), book, Book.class, hydrationErrors);
                    break;
                }
                case "Music": {
                    MusicspecType musicspec = product.getMusicspec();
                    if (musicspec == null) {
                        hydrationErrors.add(product.getAsin(), "No Music specification found");
                        break;
                    }
                    MusicCD musicCD = hydrateMusicCD(musicspec, product.getTracks(),
                            hydrateProduct(product, new MusicCD(), hydrationErrors), hydrationErrors);
                    putProduct(out, product.getAsin(), musicCD, MusicCD.class, hydrationErrors);
                    break;
                }
                case "DVD": {
                    DvdspecType dvdspec = product.getDvdspec();
                    if (dvdspec == null) {
                        hydrationErrors.add(product.getAsin(), "No DVD specification found");
                        break;
                    }
                    DVD dvd = hydrateDvd(dvdspec, product.getAudiotext(),
                            hydrateProduct(product, new DVD(), hydrationErrors), hydrationErrors);
                    putProduct(out, product.getAsin(), dvd, DVD.class, hydrationErrors);
                    break;
                }
                default:
                    hydrationErrors.add(product.getAsin(),
                            String.format("Unknown product pgroup: %s", product.getPgroup()));
                    break;
            }
        }
        return out;
    }

    public static List<Offer> hydrateToOffers(
        ShopType shop,
        int storeID,
        List<Offer> out,
        HashMap<String, Product> products,
        HydrationErrorHolder hydrationErrors
    ) {
        for (ItemType product : shop.getItem()) {
            String asin = product.getAsin();
            if (!products.containsKey(asin)) {
                continue;
            }

            PriceType price = product.getPrice();
            if (price == null) {
                continue;
            }

            Integer priceCents = null;
            String rawPrice = price.getValue();
            if (rawPrice != null && !rawPrice.isBlank()) {
                Optional<Integer> parsed = HydrationUtils.parseInt(rawPrice.trim(), 10);
                if (parsed.isEmpty()) {
                    hydrationErrors.add(asin, String.format("Invalid price: \"%s\"", rawPrice));
                    continue;
                }
                priceCents = parsed.get();
                if (priceCents <= 0) {
                    hydrationErrors.add(asin, String.format("Price is zero or negative: %d", priceCents));
                    continue;
                }
            }

            out.add(new Offer(
                    asin,
                    storeID,
                    priceCents,
                    blankToNull(price.getCurrency()),
                    blankToNull(price.getState())
            ));
        }

        return out;
    }

    private static Product hydrateProduct(ItemType item, Product product, HydrationErrorHolder hydrationErrors) {
        String asin = item.getAsin();
        if (asin == null || asin.isEmpty()) {
            hydrationErrors.add(item.getAsin(), "No asin found");
            return null;
        }
        product.setAsin(asin);

        String title = item.getTitle();
        if (title == null || title.isEmpty()) {
            hydrationErrors.add(asin, "Title is empty");
            return null;
        }
        product.setTitle(title);

        String salesRank = item.getSalesrank();
        if (salesRank != null && !salesRank.isBlank()) {
            HydrationUtils.parseInt(salesRank, 10).ifPresent(product::setSalesRank);
        }

        product.setEan(blankToNull(item.getEan()));

        DetailsType details = item.getDetails();
        if (details != null) {
            product.setImageURL(blankToNull(details.getImg()));
            product.setDetailURL(blankToNull(details.getValue()));
        }

        return product;
    }

    private static Book hydrateBook(BookspecType bookspec, Product product, HydrationErrorHolder hydrationErrors) {
        if (product == null) {
            return null;
        }
        boolean hasErrors = false;

        if (bookspec.getIsbn() == null || bookspec.getIsbn().getVal() == null || bookspec.getIsbn().getVal().isBlank()) {
            hydrationErrors.add(product.getAsin(), "Empty isbn");
            hasErrors = true;
        }

        Optional<Integer> pages = HydrationUtils.parseInt(bookspec.getPages(), 10);
        if (pages.isEmpty() || pages.get() < 1) {
            hydrationErrors.add(product.getAsin(), String.format("Invalid page number: \"%s\"", bookspec.getPages()));
            hasErrors = true;
        }

        LocalDate releaseDate = null;
        PublicationType publication = bookspec.getPublication();
        if (publication != null && publication.getDate() != null && !publication.getDate().isBlank()) {
            releaseDate = parseDate(publication.getDate(), product.getAsin(), "publication date", hydrationErrors);
        }

        String edition = null;
        if (bookspec.getEdition() != null && bookspec.getEdition().getVal() != null && !bookspec.getEdition().getVal().isBlank()) {
            edition = bookspec.getEdition().getVal();
        }

        Integer weight = null, height = null, length = null;
        PackageType pkg = bookspec.getPackage();
        if (pkg != null) {
            weight = HydrationUtils.parseInt(pkg.getWeight(), 10).orElse(null);
            height = HydrationUtils.parseInt(pkg.getHeight(), 10).orElse(null);
            length = HydrationUtils.parseInt(pkg.getLength(), 10).orElse(null);
        }

        if (hasErrors) {
            return null;
        }
        return new Book(
                product,
                bookspec.getIsbn().getVal(),
                pages.get(),
                releaseDate,
                blankToNull(bookspec.getBinding()),
                edition,
                weight,
                height,
                length
        );
    }

    private static MusicCD hydrateMusicCD(MusicspecType musicspec, TracksType tracks, Product product, HydrationErrorHolder hydrationErrors) {
        if (product == null) {
            return null;
        }

        LocalDate releaseDate = null;
        if (musicspec.getReleasedate() != null && !musicspec.getReleasedate().isBlank()) {
            releaseDate = parseDate(musicspec.getReleasedate(), product.getAsin(), "release date", hydrationErrors);
        }

        Integer numDiscs = HydrationUtils.parseInt(musicspec.getNumDiscs(), 10).orElse(null);
        if (numDiscs != null && numDiscs <= 0) {
            numDiscs = null;
        }

        List<Track> mappedTracks = new ArrayList<>();
        if (tracks != null && tracks.getTitle() != null) {
            for (int i = 0; i < tracks.getTitle().size(); i++) {
                mappedTracks.add(new Track(i + 1, tracks.getTitle().get(i)));
            }
        }

        return new MusicCD(
                product,
                releaseDate,
                blankToNull(musicspec.getBinding()),
                blankToNull(musicspec.getFormat()),
                numDiscs,
                blankToNull(musicspec.getUpc()),
                mappedTracks
        );
    }

    private static DVD hydrateDvd(DvdspecType dvdspec, AudiotextType audiotext, Product product, HydrationErrorHolder hydrationErrors) {
        if (product == null) {
            return null;
        }

        Integer runtime = null;
        Optional<Integer> runtimeOptional = HydrationUtils.parseInt(dvdspec.getRunningtime(), 10);
        if (runtimeOptional.isPresent()) {
            if (runtimeOptional.get() <= 0) {
                hydrationErrors.add(product.getAsin(), String.format("invalid runningtime %d", runtimeOptional.get()));
            } else {
                runtime = runtimeOptional.get();
            }
        }

        Integer regionCode = HydrationUtils.parseInt(dvdspec.getRegioncode(), 10).orElse(null);
        if (regionCode != null && (regionCode < 0 || regionCode > 8)) {
            hydrationErrors.add(product.getAsin(), String.format("invalid region code: %s", regionCode));
            regionCode = null;
        }

        LocalDate releaseDate = null;
        if (dvdspec.getReleasedate() != null && !dvdspec.getReleasedate().isBlank()) {
            releaseDate = parseDate(dvdspec.getReleasedate(), product.getAsin(), "release date", hydrationErrors);
        }

        Integer theatricalRelease = HydrationUtils.parseInt(dvdspec.getTheatrRelease(), 10).orElse(null);

        String upc = null;
        if (dvdspec.getUpc() != null && dvdspec.getUpc().getVal() != null && !dvdspec.getUpc().getVal().isBlank()) {
            upc = dvdspec.getUpc().getVal();
        }

        String audioFormat = null;
        if (audiotext != null && audiotext.getAudioformat() != null && !audiotext.getAudioformat().isEmpty()) {
            List<String> fmts = new ArrayList<>();
            for (String f : audiotext.getAudioformat()) {
                if (f != null && !f.isBlank()) {
                    fmts.add(f.trim());
                }
            }
            if (!fmts.isEmpty()) {
                audioFormat = String.join(", ", fmts);
            }
        }

        return new DVD(
                product,
                blankToNull(dvdspec.getFormat()),
                runtime,
                regionCode,
                releaseDate,
                theatricalRelease,
                blankToNull(dvdspec.getAspectratio()),
                audioFormat,
                upc
        );
    }

    private static <T extends Product> void putProduct(
            HashMap<String, Product> out, String asin, Product candidate,
            Class<T> expectedType, HydrationErrorHolder hydrationErrors
    ) {
        if (candidate == null) {
            return;
        }
        Product existing = out.get(asin);
        if (existing != null) {
            if (!expectedType.isInstance(existing)) {
                hydrationErrors.add(asin, String.format(
                        "dresden: duplicate product of different type %s already present",
                        existing.getClass().getSimpleName()));
                return;
            } else if (!candidate.equals(existing)) {
                hydrationErrors.add(asin, String.format(
                        "dresden: duplicate product with different hash %d already present",
                        existing.hashCode()));
                return;
            }
        }
        out.put(asin, candidate);
    }

    private static void addPersons(
            List<String> names, String asin, String role, boolean asDvdPerson,
            HashSet<Person> out, HashMap<Person, HashSet<String>> personProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        if (names == null || names.isEmpty()) {
            return;
        }
        for (String name : names) {
            if (name == null || name.isBlank()) {
                hydrationErrors.add(asin, "Empty person name");
                continue;
            }
            Person person = asDvdPerson ? new DVD.DVDPerson(name, role) : new Person(name);
            indexAdd(personProductIndex, person, asin);
            out.add(person);
        }
    }

    private static <K> void indexAdd(HashMap<K, HashSet<String>> index, K key, String asin) {
        index.computeIfAbsent(key, k -> new HashSet<>()).add(asin);
    }

    private static boolean personProductIndexContains(HashMap<Person, HashSet<String>> index, String asin) {
        for (HashSet<String> asins : index.values()) {
            if (asins.contains(asin)) {
                return true;
            }
        }
        return false;
    }

    private static LocalDate parseDate(String raw, String asin, String label, HydrationErrorHolder hydrationErrors) {
        try {
            LocalDate d = LocalDate.parse(raw);
            if (d.isAfter(LocalDate.now())) {
                hydrationErrors.add(asin, String.format("%s %s is in the future", label, raw));
                return null;
            }
            return d;
        } catch (DateTimeParseException e) {
            hydrationErrors.add(asin, String.format("Cannot parse %s: %s", label, raw));
            return null;
        }
    }

    private static String blankToNull(String s) {
        return (s == null || s.isBlank()) ? null : s;
    }
}
