package Aufgabe1.models;

import java.util.Date;

public class Review {
    private int id;
    private String productId;
    private Customer customer;
    private int score;
    private int helpful;
    private Date reviewDate;
    private String summary;
    private String content;

    public Review(
            String productId,
            Customer customer,
            int score,
            int helpful,
            Date reviewDate,
            String summary,
            String content
    ) {
        this.productId = productId;
        this.customer = customer;
        this.score = score;
        this.helpful = helpful;
        this.reviewDate = reviewDate;
        this.summary = summary;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHelpful() {
        return helpful;
    }

    public void setHelpful(int helpful) {
        this.helpful = helpful;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
