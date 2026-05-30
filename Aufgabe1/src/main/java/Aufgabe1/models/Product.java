package Aufgabe1.models;

import java.util.Objects;

public abstract class Product {
    protected String asin;
    protected String title;
    protected Integer salesRank;
    protected String imageURL;
    protected String ean;
    protected String detailURL;
    protected Float avgRating;
    protected Integer numReviews;

    public Product() {}

    public Product(Product product) {
        this.asin = product.asin;
        this.title = product.title;
        this.salesRank = product.salesRank;
        this.imageURL = product.imageURL;
        this.ean = product.ean;
        this.detailURL = product.detailURL;
        this.avgRating = product.avgRating;
        this.numReviews = product.numReviews;
    }

    public Product(
            String asin,
            String title,
            Integer salesRank,
            String imageURL,
            String ean,
            String detailURL,
            Float avgRating,
            Integer numReviews
    ) {
        this.asin = asin;
        this.title = title;
        this.salesRank = salesRank;
        this.imageURL = imageURL;
        this.ean = ean;
        this.detailURL = detailURL;
        this.avgRating = avgRating;
        this.numReviews = numReviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSalesRank() {
        return salesRank;
    }

    public void setSalesRank(Integer salesRank) {
        this.salesRank = salesRank;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getDetailURL() {
        return detailURL;
    }

    public void setDetailURL(String detailURL) {
        this.detailURL = detailURL;
    }

    public Float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(asin, product.asin) && Objects.equals(title, product.title) && Objects.equals(salesRank, product.salesRank) && Objects.equals(imageURL, product.imageURL) && Objects.equals(ean, product.ean) && Objects.equals(detailURL, product.detailURL) && Objects.equals(avgRating, product.avgRating) && Objects.equals(numReviews, product.numReviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asin, title, salesRank, imageURL, ean, detailURL, avgRating, numReviews);
    }
}
