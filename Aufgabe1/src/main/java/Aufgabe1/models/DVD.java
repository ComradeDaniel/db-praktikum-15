package Aufgabe1.models;

import java.util.Date;
import java.util.List;

public class DVD extends Product {
    public String format;
    public int runtime;
    public int regionCode;
    public Date releaseDate;
    public String theatricalRelease;
    public String aspectRation;
    public String audioFormat;
    public String upc;
    public List<DVDPerson> persons;
    public List<Studio> studios;
    public List<DVDLanguage> languages;

    public DVD(
            String title,
            int salesRank,
            String imageURL,
            String ean,
            String detailURL,
            float avgRating,
            int numReviews,
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
        super(title, salesRank, imageURL, ean, detailURL, avgRating, numReviews);
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
        public String role;

        public DVDPerson(String name, String role) {
            super(name);
            this.role = role;
        }
    }

    public static class DVDLanguage {
        public String language;
        public String languageType;

        public DVDLanguage(String language, String languageType) {
            this.language = language;
            this.languageType = languageType;
        }
    }
}
