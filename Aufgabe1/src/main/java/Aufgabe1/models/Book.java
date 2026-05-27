package Aufgabe1.models;

import java.util.Date;
import java.util.List;

public class Book extends Product {
    private String isbn;
    private int pageCount;
    private Date releaseDate;
    private String binding;
    private String edition;
    private int packageWeight;
    private int packageHeight;
    private int packageLength;
    private int publisherID;
    private List<Person> authors;

    public Book(
            String title,
            int salesRank,
            String imageURL,
            String ean,
            String detailURL,
            float avgRating,
            int numReviews,
            List<Product> similarProducts,
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
        super(title, salesRank, imageURL, ean, detailURL, avgRating, numReviews, similarProducts);
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
