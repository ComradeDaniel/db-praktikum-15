package Aufgabe1.models;

import java.util.Date;
import java.util.List;

public class Book extends Product {
    public String isbn;
    public int pageCount;
    public Date releaseDate;
    public String binding;
    public String edition;
    public int packageWeight;
    public int packageHeight;
    public int packageLength;
    public int publisherID;
    public List<Person> authors;

    public Book(
            String title,
            int salesRank,
            String imageURL,
            String ean,
            String detailURL,
            float avgRating,
            int numReviews,
            String isbn,
            int pageCount,
            Date releaseDate,
            String binding,
            String edition,
            int packageWeight,
            int packageHeight,
            int packageLength,
            int publisherID,
            List<Person> authors
    ) {
        super(title, salesRank, imageURL, ean, detailURL, avgRating, numReviews);
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.releaseDate = releaseDate;
        this.binding = binding;
        this.edition = edition;
        this.packageWeight = packageWeight;
        this.packageHeight = packageHeight;
        this.packageLength = packageLength;
        this.publisherID = publisherID;
        this.authors = authors;
    }
}
