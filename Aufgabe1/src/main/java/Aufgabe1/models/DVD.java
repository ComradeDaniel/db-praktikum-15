package Aufgabe1.models;

import java.util.Date;
import java.util.List;

public class DVD extends Product {
    private String format;
    private int runtime;
    private int regionCode;
    private Date releaseDate;
    private String theatricalRelease;
    private String aspectRation;
    private String audioFormat;
    private String upc;
    private List<DVDPerson> persons;
    private List<Studio> studios;
    private List<DVDLanguage> languages;

    public DVD(
            String title,
            int salesRank,
            String imageURL,
            String ean,
            String detailURL,
            float avgRating,
            int numReviews,
            List<Product> similarpProducts,
            String format,
            int runtime,
            int regionCode,
            Date releaseDate,
            String theatricalRelease,
            String aspectRation,
            String audioFormat,
            String upc,
            List<DVDPerson> persons,
            List<Studio> studios,
            List<DVDLanguage> languages
    ) {
        super(title, salesRank, imageURL, ean, detailURL, avgRating, numReviews, similarpProducts);
        this.format = format;
        this.runtime = runtime;
        this.regionCode = regionCode;
        this.releaseDate = releaseDate;
        this.theatricalRelease = theatricalRelease;
        this.aspectRation = aspectRation;
        this.audioFormat = audioFormat;
        this.upc = upc;
        this.persons = persons;
        this.studios = studios;
        this.languages = languages;
    }

    public static class DVDPerson extends Person {
        private String role;

        public DVDPerson(String name, String role) {
            super(name);
            this.role = role;
        }
    }

    public static class DVDLanguage {
        private String language;
        private String languageType;

        public DVDLanguage(String language, String languageType) {
            this.language = language;
            this.languageType = languageType;
        }
    }
}
