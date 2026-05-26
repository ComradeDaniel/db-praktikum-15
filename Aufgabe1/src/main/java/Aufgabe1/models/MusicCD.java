package Aufgabe1.models;

import java.util.Date;
import java.util.List;

public class MusicCD extends Product {
    public Date releaseDate;
    public String binding;
    public String format;
    public int numDiscs;
    public String upc;
    public Label label;
    public List<Track> tracks;
    public List<Person> artists;

    public MusicCD(
            String title,
            int salesRank,
            String imageURL,
            String ean,
            String detailURL,
            float avgRating,
            int numReviews,
            Date releaseDate,
            String binding,
            String format,
            int numDiscs,
            String upc,
            Label label,
            List<Track> tracks,
            List<Person> artists
    ) {
        super(title, salesRank, imageURL, ean, detailURL, avgRating, numReviews);
        this.releaseDate = releaseDate;
        this.binding = binding;
        this.format = format;
        this.numDiscs = numDiscs;
        this.upc = upc;
        this.label = label;
        this.tracks = tracks;
        this.artists = artists;
    }

    public static class Track {
        public int trackNumber;
        public String trackName;

        public Track(int trackNumber, String trackName) {
            this.trackNumber = trackNumber;
            this.trackName = trackName;
        }
    }
}
