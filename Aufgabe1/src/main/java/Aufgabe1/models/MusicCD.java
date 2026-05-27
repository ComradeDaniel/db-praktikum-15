package Aufgabe1.models;

import java.util.*;

public class MusicCD extends Product {
    private Date releaseDate;
    private String binding;
    private String format;
    private int numDiscs;
    private String upc;
    private Label label;
    private List<Track> tracks;
    private List<Person> artists;

    public MusicCD(
            String title,
            int salesRank,
            String imageURL,
            String ean,
            String detailURL,
            float avgRating,
            int numReviews,
            List<Product> similarProducts,
            Date releaseDate,
            String binding,
            String format,
            int numDiscs,
            String upc,
            Label label,
            List<Track> tracks,
            List<Person> artists
    ) {
        super(title, salesRank, imageURL, ean, detailURL, avgRating, numReviews, similarProducts);
        this.releaseDate = releaseDate;
        this.binding = binding;
        this.format = format;
        this.numDiscs = numDiscs;
        this.upc = upc;
        this.label = label;
        this.tracks = tracks;
        this.artists = artists;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
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

    public int getNumDiscs() {
        return numDiscs;
    }

    public void setNumDiscs(int numDiscs) {
        this.numDiscs = numDiscs;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Person> getArtists() {
        return artists;
    }

    public void setArtists(List<Person> artists) {
        this.artists = artists;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MusicCD musicCD)) return false;
        if (!super.equals(o)) return false;
        return numDiscs == musicCD.numDiscs &&
                Objects.equals(releaseDate, musicCD.releaseDate) &&
                Objects.equals(binding, musicCD.binding) &&
                Objects.equals(format, musicCD.format) &&
                Objects.equals(upc, musicCD.upc) &&
                Objects.equals(label, musicCD.label) &&
                Objects.equals(tracks, musicCD.tracks) &&
                Objects.equals(artists, musicCD.artists);
    }

    @Override
    public int hashCode() {
        List<Track> sortedTracks = new ArrayList<>(this.getTracks());
        sortedTracks.sort(Comparator.comparing(Track::getTrackName));

        List<Person> sortedArtists = new ArrayList<>(this.getArtists());
        sortedArtists.sort(Comparator.comparing(Person::getName));

        return Objects.hash(super.hashCode(), releaseDate, binding, format, numDiscs, upc, label, tracks, artists);
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
