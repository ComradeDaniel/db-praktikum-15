package Aufgabe1.models;

import java.util.List;
import java.util.Objects;

public abstract class Product {
    protected int id;
    protected String title;
    protected int salesRank;
    protected String imageURL;
    protected String ean;
    protected String detailURL;
    protected float avgRating;
    protected int numReviews;
    protected List<Product> similarProducts;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalesRank() {
        return salesRank;
    }

    public void setSalesRank(int salesRank) {
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

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public List<Product> getSimilarProducts() {
        return similarProducts;
    }

    public void setSimilarProducts(List<Product> similarProducts) {
        this.similarProducts = similarProducts;
    }

    public void addSimilarProduct(Product product) {
        this.similarProducts.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return salesRank == product.salesRank &&
                Float.compare(avgRating, product.avgRating) == 0 &&
                numReviews == product.numReviews &&
                Objects.equals(title, product.title) &&
                Objects.equals(imageURL, product.imageURL) &&
                Objects.equals(ean, product.ean) &&
                Objects.equals(detailURL, product.detailURL) &&
                Objects.equals(similarProducts, product.similarProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, salesRank, imageURL, ean, detailURL, avgRating, numReviews, similarProducts);
    }
}
