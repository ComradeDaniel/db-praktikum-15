package Aufgabe1.models;

import java.util.List;

public abstract class Product {
    public int id;
    public String title;
    public int salesRank;
    public String imageURL;
    public String ean;
    public String detailURL;
    public float avgRating;
    public int numReviews;
    public List<Product> similarProducts;

    public Product(
            String title,
            int salesRank,
            String imageURL,
            String ean,
            String detailURL,
            float avgRating,
            int numReviews,
            List<Product> similarProducts
    ) {
        this.title = title;
        this.salesRank = salesRank;
        this.imageURL = imageURL;
        this.ean = ean;
        this.detailURL = detailURL;
        this.avgRating = avgRating;
        this.numReviews = numReviews;
        this.similarProducts = similarProducts;
    }
}
