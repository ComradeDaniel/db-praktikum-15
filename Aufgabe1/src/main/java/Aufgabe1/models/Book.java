package Aufgabe1.models;

import java.time.LocalDate;
import java.util.Objects;

public class Book extends Product {
    private String isbn;
    private Integer pageCount;
    private LocalDate releaseDate;
    private String binding;
    private String edition;
    private Integer packageWeight;
    private Integer packageHeight;
    private Integer packageLength;

    public Book(
            String asin,
            String title,
            int salesRank,
            String imageURL,
            String ean,
            String detailURL,
            float avgRating,
            int numReviews,
            String isbn,
            Integer pageCount,
            LocalDate releaseDate,
            String binding,
            String edition,
            Integer packageWeight,
            Integer packageHeight,
            Integer packageLength
    ) {
        super(asin, title, salesRank, imageURL, ean, detailURL, avgRating, numReviews);
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.releaseDate = releaseDate;
        this.binding = binding;
        this.edition = edition;
        this.packageWeight = packageWeight;
        this.packageHeight = packageHeight;
        this.packageLength = packageLength;
    }

    public Book(
            Product product,
            String isbn,
            Integer pageCount,
            LocalDate releaseDate,
            String binding,
            String edition,
            Integer packageWeight,
            Integer packageHeight,
            Integer packageLength
    ) {
        super(product);
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.releaseDate = releaseDate;
        this.binding = binding;
        this.edition = edition;
        this.packageWeight = packageWeight;
        this.packageHeight = packageHeight;
        this.packageLength = packageLength;
    }

    public Book() {
        super();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(Integer packageWeight) {
        this.packageWeight = packageWeight;
    }

    public Integer getPackageHeight() {
        return packageHeight;
    }

    public void setPackageHeight(Integer packageHeight) {
        this.packageHeight = packageHeight;
    }

    public Integer getPackageLength() {
        return packageLength;
    }

    public void setPackageLength(Integer packageLength) {
        this.packageLength = packageLength;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(isbn, book.isbn) && Objects.equals(pageCount, book.pageCount) && Objects.equals(releaseDate, book.releaseDate) && Objects.equals(binding, book.binding) && Objects.equals(edition, book.edition) && Objects.equals(packageWeight, book.packageWeight) && Objects.equals(packageHeight, book.packageHeight) && Objects.equals(packageLength, book.packageLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isbn, pageCount, releaseDate, binding, edition, packageWeight, packageHeight, packageLength);
    }
}
