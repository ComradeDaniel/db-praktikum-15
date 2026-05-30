package Aufgabe1.models;

import java.time.LocalDate;
import java.util.*;

public class MusicCD extends Product {
    private LocalDate releaseDate;
    private String binding;
    private String format;
    private Integer numDiscs;
    private String upc;
    private List<Track> tracks;

    public MusicCD() {
        super();
    }

    public MusicCD(
            String asin,
            String title,
            Integer salesRank,
            String imageURL,
            String ean,
            String detailURL,
            Float avgRating,
            Integer numReviews,
            LocalDate releaseDate,
            String binding,
            String format,
            Integer numDiscs,
            String upc,
            List<Track> tracks
    ) {
        super(asin, title, salesRank, imageURL, ean, detailURL, avgRating, numReviews);
        this.releaseDate = releaseDate;
        this.binding = binding;
        this.format = format;
        this.numDiscs = numDiscs;
        this.upc = upc;
        this.tracks = tracks;
    }

    public MusicCD(
            Product product,
            LocalDate releaseDate,
            String binding,
            String format,
            Integer numDiscs,
            String upc,
            List<Track> tracks
    ) {
        super(product);
        this.releaseDate = releaseDate;
        this.binding = binding;
        this.format = format;
        this.numDiscs = numDiscs;
        this.upc = upc;
        this.tracks = tracks;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getNumDiscs() {
        return numDiscs;
    }

    public void setNumDiscs(Integer numDiscs) {
        this.numDiscs = numDiscs;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MusicCD musicCD)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(releaseDate, musicCD.releaseDate) && Objects.equals(binding, musicCD.binding) && Objects.equals(format, musicCD.format) && Objects.equals(numDiscs, musicCD.numDiscs) && Objects.equals(upc, musicCD.upc) && Objects.equals(tracks, musicCD.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), releaseDate, binding, format, numDiscs, upc, tracks);
    }

    public static class Track {
        private int trackNumber;
        private String trackName;

        public Track(int trackNumber, String trackName) {
            this.trackNumber = trackNumber;
            this.trackName = trackName;
        }

        public int getTrackNumber() {
            return trackNumber;
        }

        public void setTrackNumber(int trackNumber) {
            this.trackNumber = trackNumber;
        }

        public String getTrackName() {
            return trackName;
        }

        public void setTrackName(String trackName) {
            this.trackName = trackName;
        }
    }
}
