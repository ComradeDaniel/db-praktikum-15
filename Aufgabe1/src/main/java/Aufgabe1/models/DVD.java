package Aufgabe1.models;

import java.time.LocalDate;
import java.util.Objects;

public class DVD extends Product {
    private String format;
    private Integer runtime;
    private Integer regionCode;
    private LocalDate releaseDate;
    private Integer theatricalRelease;
    private String aspectRatio;
    private String audioFormat;
    private String upc;

    public DVD() {
        super();
    }

    public DVD(
            String asin,
            String title,
            Integer salesRank,
            String imageURL,
            String ean,
            String detailURL,
            Float avgRating,
            Integer numReviews,
            String format,
            Integer runtime,
            Integer regionCode,
            LocalDate releaseDate,
            Integer theatricalRelease,
            String aspectRatio,
            String audioFormat,
            String upc
    ) {
        super(asin, title, salesRank, imageURL, ean, detailURL, avgRating, numReviews);
        this.format = format;
        this.runtime = runtime;
        this.regionCode = regionCode;
        this.releaseDate = releaseDate;
        this.theatricalRelease = theatricalRelease;
        this.aspectRatio = aspectRatio;
        this.audioFormat = audioFormat;
        this.upc = upc;
    }

    public DVD(
            Product product,
            String format,
            Integer runtime,
            Integer regionCode,
            LocalDate releaseDate,
            Integer theatricalRelease,
            String aspectRatio,
            String audioFormat,
            String upc
    ) {
        super(product);
        this.format = format;
        this.runtime = runtime;
        this.regionCode = regionCode;
        this.releaseDate = releaseDate;
        this.theatricalRelease = theatricalRelease;
        this.aspectRatio = aspectRatio;
        this.audioFormat = audioFormat;
        this.upc = upc;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DVD dvd)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(format, dvd.format) && Objects.equals(runtime, dvd.runtime) && Objects.equals(regionCode, dvd.regionCode) && Objects.equals(releaseDate, dvd.releaseDate) && Objects.equals(theatricalRelease, dvd.theatricalRelease) && Objects.equals(aspectRatio, dvd.aspectRatio) && Objects.equals(audioFormat, dvd.audioFormat) && Objects.equals(upc, dvd.upc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), format, runtime, regionCode, releaseDate, theatricalRelease, aspectRatio, audioFormat, upc);
    }

    public static class DVDPerson extends Person {
        private String role;

        public DVDPerson(String name, String role) {
            super(name);
            this.role = role;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof DVDPerson dvdPerson)) return false;
            if (!super.equals(o)) return false;
            return Objects.equals(role, dvdPerson.role);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), role);
        }
    }

    public static class DVDLanguage {
        private String language;
        private String languageType;

        public DVDLanguage(String language, String languageType) {
            this.language = language;
            this.languageType = languageType;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getLanguageType() {
            return languageType;
        }

        public void setLanguageType(String languageType) {
            this.languageType = languageType;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof DVDLanguage that)) return false;
            return Objects.equals(language, that.language) && Objects.equals(languageType, that.languageType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(language, languageType);
        }
    }
}
