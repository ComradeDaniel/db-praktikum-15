package Aufgabe1;

import Aufgabe1.models.*;
import Aufgabe1.models.MusicCD.Track;
import Aufgabe1.utility.HydrationUtils;
import Aufgabe1.utility.HydrationErrorHolder;
import leipzig.Shop;

import java.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LeipzigHydrator {
    public static HashSet<Studio> hydrateToStudios(
            Shop shop,
            HashSet<Studio> out,
            HashMap<Studio, HashSet<String>> studioProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (int i = 0; i < shop.getItem().size(); i++) {
            Shop.Item product = shop.getItem().get(i);

            if (!product.getPgroup().equals("DVD")) {
                continue;
            }
            Shop.Item.Studios itemStudios = product.getStudios();
            if (itemStudios == null || itemStudios.getStudio() == null || itemStudios.getStudio().isEmpty()) {
                // hydrationErrors.add(product.getAsin(), "No studio specification found");
                continue;
            }

            String asin = product.getAsin();
            itemStudios.getStudio().forEach(studio -> {
                if (studio.getName().isBlank()) {
                    hydrationErrors.add(product.getAsin(), "Empty studio name");
                    return;
                }
                Studio domainStudio = new Studio(studio.getName());
                studioProductIndex.putIfAbsent(domainStudio, new HashSet<>());
                studioProductIndex.computeIfPresent(domainStudio, (s, l) -> {
                    l.add(asin);
                    return l;
                });
                out.add(domainStudio);
            });
        }

        return out;
    }

    public static HashSet<Person> hydrateToPersons(
            Shop shop,
            HashSet<Person> out,
            HashMap<Person, HashSet<String>> personProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (int i = 0; i < shop.getItem().size(); i++) {
            Shop.Item product = shop.getItem().get(i);

            switch (product.getPgroup()) {
                case "DVD": {
                    Shop.Item.Actors actors = product.getActors();
                    if (actors != null && actors.getActor() != null && !actors.getActor().isEmpty()) {
                        String asin = product.getAsin();
                        actors.getActor().forEach(actor -> {
                            if (actor.getName().isBlank()) {
                                hydrationErrors.add(product.getAsin(), "Empty actor name");
                            } else {
                                DVD.DVDPerson person = new DVD.DVDPerson(actor.getName(), "Actor");
                                personProductIndex.putIfAbsent(person, new HashSet<>());
                                personProductIndex.computeIfPresent(person, (s, l) -> {
                                    l.add(asin);
                                    return l;
                                });
                                out.add(person);

                            }
                        });
                    }

                    Shop.Item.Creators creators = product.getCreators();
                    if (creators != null && creators.getCreator() != null && !creators.getCreator().isEmpty()) {
                        String asin = product.getAsin();
                        creators.getCreator().forEach(creator -> {
                            if (creator.getName().isBlank()) {
                                hydrationErrors.add(product.getAsin(), "Empty creator name");
                            } else {
                                DVD.DVDPerson person = new DVD.DVDPerson(creator.getName(), "Creator");
                                personProductIndex.putIfAbsent(person, new HashSet<>());
                                personProductIndex.computeIfPresent(person, (s, l) -> {
                                    l.add(asin);
                                    return l;
                                });
                                out.add(person);
                            }
                        });
                    }

                    Shop.Item.Directors directors = product.getDirectors();
                    if (directors != null && directors.getDirector() != null && !directors.getDirector().isEmpty()) {
                        String asin = product.getAsin();
                        directors.getDirector().forEach(director -> {
                            if (director.getName().isBlank()) {
                                hydrationErrors.add(product.getAsin(), "Empty director name");
                            } else {
                                DVD.DVDPerson person = new DVD.DVDPerson(director.getName(), "Director");
                                personProductIndex.putIfAbsent(person, new HashSet<>());
                                personProductIndex.computeIfPresent(person, (s, l) -> {
                                    l.add(asin);
                                    return l;
                                });
                                out.add(person);
                            }
                        });
                    }
                    break;
                }
                case "Music": {
                    boolean personsFound = false;
                    String asin = product.getAsin();
                    Shop.Item.Artists artistsType = product.getArtists();
                    if (artistsType != null && artistsType.getArtist() != null && !artistsType.getArtist().isEmpty()) {
                        personsFound = true;
                        List<Shop.Item.Artists.Artist> artists = artistsType.getArtist();
                        
                        artists.forEach(artist -> {
                            if (artist.getName().isBlank()) {
                                hydrationErrors.add(product.getAsin(), "Empty artist name");
                                return;
                            }
                            Person person = new Person(artist.getName());
                            personProductIndex.putIfAbsent(person, new HashSet<>());
                            personProductIndex.computeIfPresent(person, (s, l) -> {
                                l.add(asin);
                                return l;
                            });
                            out.add(person);
                        });
                    }

                    // also check creators for Music disks
                    Shop.Item.Creators creators = product.getCreators();
                    if (creators != null && creators.getCreator() != null && !creators.getCreator().isEmpty()) {
                        personsFound = true;
                        creators.getCreator().forEach(creator -> {
                            if (creator.getName().isBlank()) {
                                hydrationErrors.add(product.getAsin(), "Empty creator name");
                            } else {
                                Person person = new Person(creator.getName());
                                personProductIndex.putIfAbsent(person, new HashSet<>());
                                personProductIndex.computeIfPresent(person, (s, l) -> {
                                    l.add(asin);
                                    return l;
                                });
                                out.add(person);
                            }
                        });
                    }
                    if (!personsFound) {
                        hydrationErrors.add(product.getAsin(), "No artist or creator specification found");
                    }
                    break;
                }
                case "Book": {
                    Shop.Item.Authors authorsType = product.getAuthors();
                    if (authorsType == null || authorsType.getAuthor() == null || authorsType.getAuthor().isEmpty()) {
                        hydrationErrors.add(product.getAsin(), "No author specification found");
                        break;
                    }
                    List<Shop.Item.Authors.Author> authors = authorsType.getAuthor();
                    String asin = product.getAsin();
                    authors.forEach(author -> {
                        if (author.getName().isBlank()) {
                            hydrationErrors.add(product.getAsin(), "Empty author name");
                            return;
                        }
                        Person person = new Person(author.getName());
                        personProductIndex.putIfAbsent(person, new HashSet<>());
                        personProductIndex.computeIfPresent(person, (s, l) -> {
                            l.add(asin);
                            return l;
                        });
                        out.add(person);
                    });
                    break;
                }
                default:
            }
        }

        return out;
    }

    public static HashSet<Publisher> hydrateToPublishers(
            Shop shop,
            HashSet<Publisher> out,
            HashMap<Publisher, HashSet<String>> publisherProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (int i = 0; i < shop.getItem().size(); i++) {
            Shop.Item product = shop.getItem().get(i);

            if (!product.getPgroup().equals("Book")) {
                continue;
            }
            Shop.Item.Publishers publishers = product.getPublishers();
            if (publishers == null || publishers.getPublisher() == null || publishers.getPublisher().isEmpty()) {
                hydrationErrors.add(product.getAsin(), "No publisher specification found");
                continue;
            }

            String asin = product.getAsin();
            publishers.getPublisher().forEach(publisher -> {
                if (publisher.getName().isBlank()) {
                    hydrationErrors.add(product.getAsin(), "Empty publisher name");
                    return;
                }
                Publisher domainPublisher = new Publisher(publisher.getName());
                publisherProductIndex.putIfAbsent(domainPublisher, new HashSet<>());
                publisherProductIndex.computeIfPresent(domainPublisher, (s, l) -> {
                    l.add(asin);
                    return l;
                });
                out.add(domainPublisher);
            });
        }

        return out;
    }

    public static HashSet<Label> hydrateToLabels(
            Shop shop,
            HashSet<Label> out,
            HashMap<Label, HashSet<String>> labelProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (int i = 0; i < shop.getItem().size(); i++) {
            Shop.Item product = shop.getItem().get(i);

            if (!product.getPgroup().equals("Music")) {
                continue;
            }
            Shop.Item.Labels labels = product.getLabels();
            if (labels == null || labels.getLabel() == null || labels.getLabel().isEmpty()) {
                hydrationErrors.add(product.getAsin(), "No label specification found");
                continue;
            }

            String asin = product.getAsin();
            labels.getLabel().forEach(label -> {
                if (label.getName().isBlank()) {
                    hydrationErrors.add(product.getAsin(), "Empty label name");
                    return;
                }
                Label domainLabel = new Label(label.getName());
                labelProductIndex.putIfAbsent(domainLabel, new HashSet<>());
                labelProductIndex.computeIfPresent(domainLabel, (s, l) -> {
                    l.add(asin);
                    return l;
                });
                out.add(domainLabel);
            });
        }

        return out;
    }

    public static HashSet<ListmaniaList> hydrateToListmania(
            Shop shop,
            HashSet<ListmaniaList> out,
            HashMap<ListmaniaList, HashSet<String>> listmaniaProductIndex,
            HydrationErrorHolder hydrationErrors
    ) {
        for (int i = 0; i < shop.getItem().size(); i++) {
            Shop.Item product = shop.getItem().get(i);

            Shop.Item.Listmania listmaniaType = product.getListmania();
            if (listmaniaType == null || listmaniaType.getList() == null || listmaniaType.getList().isEmpty()) {
                continue;
            }

            String asin = product.getAsin();
            listmaniaType.getList().forEach(listmania -> {
                if (listmania.getName().isBlank()) {
                    hydrationErrors.add(product.getAsin(), "Empty listmania name");
                    return;
                }
                ListmaniaList domainListmania = new ListmaniaList(listmania.getName());
                listmaniaProductIndex.putIfAbsent(domainListmania, new HashSet<>());
                listmaniaProductIndex.computeIfPresent(domainListmania, (s, l) -> {
                    l.add(asin);
                    return l;
                });
                out.add(domainListmania);
            });
        }

        return out;
    }

    public static HashSet<DVD.DVDLanguage> hydrateToDVDLanguages(
        Shop shop,
        HashSet<DVD.DVDLanguage> out,
        HashMap<DVD.DVDLanguage, HashSet<String>> dvdlanguageProductIndex,
        HydrationErrorHolder hydrationErrors
    ) {
        for (int i = 0; i < shop.getItem().size(); i++) {
            Shop.Item product = shop.getItem().get(i);

            if (!product.getPgroup().equals("DVD")) {
                continue;
            }
            Shop.Item.Audiotext audiotext = product.getAudiotext();
            if (audiotext.getLanguageAndAudioformat() != null && !audiotext.getLanguageAndAudioformat().isEmpty()) {
                // every second object will be audioformat
                for (int j = 0; j < audiotext.getLanguageAndAudioformat().size(); j+=2) {
                    Object obj1 = audiotext.getLanguageAndAudioformat().get(j);
                    if (obj1 instanceof Shop.Item.Audiotext.Language) {
                        String type = null;
                        String language = null;
                        if (((Shop.Item.Audiotext.Language) obj1).getType().isBlank()) {
                            hydrationErrors.add(product.getAsin(), "Blank language type");
                        } else {
                            type = ((Shop.Item.Audiotext.Language) obj1).getType();
                        }
                        if (((Shop.Item.Audiotext.Language) obj1).getValue().isBlank()) {
                            hydrationErrors.add(product.getAsin(), "Empty audiotext language");
                        } else {
                            language = ((Shop.Item.Audiotext.Language) obj1).getValue();
                        }
                        if (type == null || language == null) {
                            continue;
                        }
                        // DVDLanguage(String language, String languageType): language = gesprochene
                        // Sprache (value), languageType = Art (type, z.B. "Original Language").
                        DVD.DVDLanguage newLanguage = new DVD.DVDLanguage(language, type);
                        out.add(newLanguage);
                        dvdlanguageProductIndex.compute(newLanguage, (k, l) -> {
                            if (l == null) {
                                l = new HashSet<>();
                            }
                            l.add(product.getAsin());
                            return l;
                        });
                    } else {
                        hydrationErrors.add(product.getAsin(), "Invalid audio format");
                    }
                }
            }
        }

        return out;
    }

    public static HashMap<String, Product> hydrateToProducts(
            Shop shop,
            HashMap<String, Product> out,
            HydrationErrorHolder hydrationErrors
    ) {
        for (int i = 0; i < shop.getItem().size(); i++) {
            Shop.Item product = shop.getItem().get(i);

            switch (product.getPgroup()) {
                case "Book": {
                    Shop.Item.Bookspec bookspec = product.getBookspec();

                    assertNull(
                        hydrationErrors,
                        product,
                        product.getDvdspec(),
                        product.getMusicspec(),
                        product.getArtists(),
                        product.getAudiotext(),
                        product.getCreators(),
                        product.getDirectors(),
                        product.getLabels(),
                        product.getStudios(),
                        product.getTracks()
                    );
                    if (bookspec == null) {
                        hydrationErrors.add(product.getAsin(), "No book specification found");
                        break;
                    }
                    Book book = hydrateBook(bookspec, hydrateProduct(product, new Book(), hydrationErrors), hydrationErrors);
                    if (book != null) {
                        Product outProduct = out.get(product.getAsin());
                        if (outProduct != null) {
                            if (!(outProduct instanceof Book)) {
                                hydrationErrors.add(product.getAsin(), String.format(
                                    "leipzig: duplicate product of different type %s already present",
                                    outProduct.getClass().getSimpleName()
                                ));
                                break;
                            } else if (!book.equals(outProduct)) {
                                hydrationErrors.add(product.getAsin(), String.format(
                                    "leipzig: duplicate product with different hash %s already present",
                                    ((Book) outProduct).hashCode()
                                ));
                                break;
                            }
                        }
                        out.put(product.getAsin(), book);
                    }
                    break;
                }
                case "Music": {
                    Shop.Item.Musicspec musicspec = product.getMusicspec();

                    assertNull(
                        hydrationErrors,
                        product,
                        product.getBookspec(),
                        product.getDvdspec(),
                        product.getActors(),
                        product.getDirectors(),
                        product.getAuthors(),
                        product.getPublishers(),
                        product.getStudios(),
                        product.getAudiotext()
                    );
                    if (musicspec == null) {
                        hydrationErrors.add(product.getAsin(), "No Music specification found");
                        break;
                    }
                    MusicCD musicCD = hydrateMusicCD(musicspec, product.getTracks(), hydrateProduct(product, new MusicCD(), hydrationErrors), hydrationErrors);
                    if (musicCD != null) {
                        Product outProduct = out.get(product.getAsin());
                        if (outProduct != null) {
                            if (!(outProduct instanceof MusicCD)) {
                                hydrationErrors.add(product.getAsin(), String.format(
                                    "leipzig: duplicate product of different type %s already present",
                                    outProduct.getClass().getSimpleName()
                                ));
                                break;
                            } else if (!musicCD.equals(outProduct)) {
                                hydrationErrors.add(product.getAsin(), String.format(
                                    "leipzig: duplicate product with different hash %s already present",
                                    ((MusicCD) outProduct).hashCode()
                                ));
                                break;
                            }
                        }
                        out.put(product.getAsin(), musicCD);
                    }
                    break;
                }
                case "DVD": {
                    Shop.Item.Dvdspec dvdspec = product.getDvdspec();

                    assertNull(
                        hydrationErrors,
                        product,
                        product.getBookspec(),
                        product.getMusicspec(),
                        product.getArtists(),
                        product.getLabels(),
                        product.getTracks(),
                        product.getAuthors(),
                        product.getPublishers()
                    );
                    if (dvdspec == null) {
                        hydrationErrors.add(product.getAsin(), "No DVD specification found");
                        break;
                    }
                    DVD dvd = hydrateDvd(dvdspec, hydrateProduct(product, new DVD(), hydrationErrors), hydrationErrors);
                    if (dvd != null) {
                        Product outProduct = out.get(product.getAsin());
                        if (outProduct != null) {
                            if (!(outProduct instanceof DVD)) {
                                hydrationErrors.add(product.getAsin(), String.format(
                                    "leipzig: duplicate product of different type %s already present",
                                    outProduct.getClass().getSimpleName()
                                ));
                                break;
                            } else if (!dvd.equals(outProduct)) {
                                hydrationErrors.add(product.getAsin(), String.format(
                                    "leipzig: duplicate product with different hash %s already present",
                                    ((DVD) outProduct).hashCode()
                                ));
                                break;
                            }
                        }
                        out.put(product.getAsin(), dvd);
                    }
                    break;
                }
                default:
                    hydrationErrors.add(product.getAsin(), String.format("Unknown product pgroup: %s", product.getPgroup()));
                    break;
            }
        }

        return out;
    }

    public static List<SimilarProduct> hydrateToSimilarProducts(
            Shop shop,
            List<SimilarProduct> out,
            HashMap<String, Product> products,
            HydrationErrorHolder hydrationErrors
    ) {
        for (int i = 0; i < shop.getItem().size(); i++) {
            Shop.Item product = shop.getItem().get(i);
            String asin = product.getAsin();
            if (!products.containsKey(asin)) {
                continue;
            }

            Shop.Item.Similars similars = product.getSimilars();
            if (similars == null || similars.getSimProduct().isEmpty()) {
                continue;
            }

            for (Shop.Item.Similars.SimProduct simProduct : similars.getSimProduct()) {
                String similarAsin = simProduct.getAsin();
                if (similarAsin == null || similarAsin.isBlank()) {
                    hydrationErrors.add(asin, "Empty similar product asin");
                    continue;
                }
                if (asin.equals(similarAsin)) {
                    continue;
                }
                if (!products.containsKey(similarAsin)) {
                    continue;
                }
                out.add(new SimilarProduct(asin, similarAsin));
            }
        }

        return out;
    }

    public static HashSet<Offer> hydrateToOffers(
        Shop shop,
        int storeID,
        HashSet<Offer> out,
        HashMap<String, Product> products,
        HydrationErrorHolder hydrationErrors
    ) {
        for (int i = 0; i < shop.getItem().size(); i++) {
            Shop.Item product = shop.getItem().get(i);
            String asin = product.getAsin();
            if (!products.containsKey(asin)) {
                continue;
            }

            Shop.Item.Price price = product.getPrice();
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

            String currency = price.getCurrency();
            if (currency != null && currency.isBlank()) {
                currency = null;
            }

            String condition = price.getState();
            if (condition != null && condition.isBlank()) {
                condition = null;
            }

            out.add(new Offer(asin, storeID, priceCents, priceCents != null, currency, condition));
        }

        return out;
    }

    private static Product hydrateProduct(
        Shop.Item item,
        Product product,
        HydrationErrorHolder hydrationErrors
    ) {
        String asin = item.getAsin();
        if (asin == null || asin.isEmpty()) {
            hydrationErrors.add(item.getAsin(), "No asin found");
            return null;
        }
        product.setAsin(asin);

        String title =  item.getTitle();
        if (title == null || title.isEmpty()) {
            hydrationErrors.add(item.getAsin(), "Title is empty");
            return null;
        }
        product.setTitle(title);

        String salesRank = item.getSalesrank();
        if (salesRank != null && !salesRank.isBlank()) {
            product.setSalesRank(HydrationUtils.parseInt(salesRank, 10).orElse(0));
        }

        product.setImageURL(item.getPicture().isBlank() ? null : item.getPicture());

        product.setEan(item.getEan().isBlank() ? null : item.getEan());

        product.setDetailURL(item.getDetailpage().isBlank() ? null : item.getDetailpage());

        return product;
    }

    private static Book hydrateBook(
            Shop.Item.Bookspec bookspec,
            Product product,
            HydrationErrorHolder hydrationErrors
    ) {
        if (product == null) {
            return null;
        }
        Book book = null;
        boolean hasErrors = false;
        if (bookspec.getIsbn() == null || bookspec.getIsbn().getVal().isBlank()) {
            hydrationErrors.add(product.getAsin(), "Empty isbn");
            hasErrors = true;
        }

        Optional<Integer> pages = HydrationUtils.parseInt(bookspec.getPages(), 10);
        if (!bookspec.getPages().isBlank() && (pages.isEmpty() || pages.get() < 1)) {
            hydrationErrors.add(product.getAsin(), String.format("Invalid page number: \"%s\"", bookspec.getPages()));
        }

        Shop.Item.Bookspec.Publication publication = bookspec.getPublication();
        LocalDate releaseDate = null;
        if (publication != null && !publication.getDate().isBlank()) {
            try {
                releaseDate = LocalDate.parse(publication.getDate());
                if (releaseDate.isAfter(LocalDate.now())) {
                    hydrationErrors.add(product.getAsin(), String.format("Publication date %s is in the future", publication.getDate()));
                    releaseDate = null;
                }
            } catch (DateTimeParseException e) {
                hydrationErrors.add(product.getAsin(), String.format("Cannot parse publication date: %s", publication.getDate()));
                releaseDate = null;
            }
        }

        String edition = null;
        if (bookspec.getEdition() != null && !bookspec.getEdition().getVal().isBlank()) {
            edition = bookspec.getEdition().getVal();
        }

        Shop.Item.Bookspec.Package pkg = bookspec.getPackage();
        Integer weight = null;
        Integer height = null;
        Integer length = null;
        if (pkg != null) {
            weight = HydrationUtils.parseInt(pkg.getWeight(), 10).orElse(null);
            height = HydrationUtils.parseInt(pkg.getHeight(), 10).orElse(null);
            length = HydrationUtils.parseInt(pkg.getLength(), 10).orElse(null);
        }

        if (!hasErrors) {
            book = new Book(
                product,
                bookspec.getIsbn().getVal(),
                pages.orElse(null),
                releaseDate,
                bookspec.getBinding().isBlank() ? null : bookspec.getBinding(),
                edition,
                weight,
                height,
                length
            );
        }

        return book;
    }

    private static MusicCD hydrateMusicCD(
        Shop.Item.Musicspec musicspec,
        Shop.Item.Tracks tracks,
        Product product,
        HydrationErrorHolder hydrationErrors
    ) {
        if (product == null) {
            return null;
        }
        MusicCD musicCD = null;

        LocalDate releaseDate = null;
        if (musicspec.getReleasedate() != null && !musicspec.getReleasedate().isBlank()) {
            try {
                releaseDate = LocalDate.parse(musicspec.getReleasedate());
                if (releaseDate.isAfter(LocalDate.now())) {
                    hydrationErrors.add(product.getAsin(), String.format("Release date %s is in the future", musicspec.getReleasedate()));
                    releaseDate = null;
                }
            } catch (DateTimeParseException e) {
                hydrationErrors.add(product.getAsin(), String.format("Cannot parse release date: %s", musicspec.getReleasedate()));
                releaseDate = null;
            }
        }

        String format = null;
        if (musicspec.getFormat() != null && !musicspec.getFormat().getValue().isBlank()) {
            format = musicspec.getFormat().getValue();
        }

        Integer numDiscs = HydrationUtils.parseInt(musicspec.getNumDiscs(), 10).orElse(0);
        if (numDiscs <= 0) {
            numDiscs = null;
        }

        List<Track> mappedTracks = new ArrayList<>();
        if (tracks != null) {
            for (int i = 0; i < tracks.getTitle().size(); i++) {
                mappedTracks.add(new Track(i+1, tracks.getTitle().get(i)));
            }
        }

        musicCD = new MusicCD(
            product,
            releaseDate,
            musicspec.getBinding().isBlank() ? null : musicspec.getBinding(),
            format,
            numDiscs,
            musicspec.getUpc().isBlank() ? null : musicspec.getUpc(),
            mappedTracks
        );

        return musicCD;
    }

    private static DVD hydrateDvd(
        Shop.Item.Dvdspec dvdspec,
        Product product,
        HydrationErrorHolder hydrationErrors
    ) {
        if (product == null) {
            return null;
        }
        DVD dvd = null;

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
            hydrationErrors.add(product.getAsin(), String.format("invalid region code: %s", regionCode.toString()));
        }

        LocalDate releaseDate = null;
        if (dvdspec.getReleasedate() != null && !dvdspec.getReleasedate().isBlank()) {
            try {
                releaseDate = LocalDate.parse(dvdspec.getReleasedate());
                if (releaseDate.isAfter(LocalDate.now())) {
                    hydrationErrors.add(product.getAsin(), String.format("Release date %s is in the future", dvdspec.getReleasedate()));
                    releaseDate = null;
                }
            } catch (DateTimeParseException e) {
                hydrationErrors.add(product.getAsin(), String.format("Cannot parse release date: %s", dvdspec.getReleasedate()));
                releaseDate = null;
            }
        }

        String upc = null;
        if (dvdspec.getUpc() != null && !dvdspec.getUpc().getVal().isBlank()) {
            upc = dvdspec.getUpc().getVal();
        }

        Integer theatricalRelease = HydrationUtils.parseInt(dvdspec.getTheatrRelease(), 10).orElse(null);

        String audioFormat = null;
        if (dvdspec.getAudio() != null && !dvdspec.getAudio().isEmpty()) {
            List<String> fmts = new ArrayList<>();
            for (String f : dvdspec.getAudio()) {
                if (f != null && !f.isBlank()) {
                    fmts.add(f.trim());
                }
            }
            if (!fmts.isEmpty()) {
                audioFormat = String.join(", ", fmts);
            }
        }

        dvd = new DVD(
            product,
            dvdspec.getFormat().isBlank() ? null : dvdspec.getFormat(),
            runtime,
            regionCode,
            releaseDate,
            theatricalRelease,
            dvdspec.getAspectratio().isBlank() ? null : dvdspec.getAspectratio(),
            audioFormat,
            upc
        );

        return dvd;
    }

    private static void assertNull(HydrationErrorHolder hydrationErrors, Shop.Item item, Object... object) {
        StringBuilder fields = new StringBuilder();
        for (Object o : object) {
            if (HydrationUtils.hasNonNullProperties(o)) {
                fields.append(o.getClass().getSimpleName());
                fields.append(", ");
            }
        }
        if (!fields.isEmpty()) {
            hydrationErrors.add(
                    item.getAsin(),
                    String.format(
                            "contains ignored fields for product type %s: %s",
                            item.getPgroup(),
                            fields.substring(0, fields.length() - 2)
                    )
            );
        }

    }
}
