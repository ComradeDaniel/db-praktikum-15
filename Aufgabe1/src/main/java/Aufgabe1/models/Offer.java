package Aufgabe1.models;

import java.util.Objects;

// Angebot eines Produkts in einem Store (aus dem <price>-Element)
public class Offer {
    private int id;
    private String productId;   // ASIN -> Product
    private int storeID;
    private Integer priceCents;
    private boolean available;
    private String currency;
    private String condition;

    public Offer(
            String productId,
            int storeID,
            Integer priceCents,
            boolean available,
            String currency,
            String condition
    ) {
        this.productId = productId;
        this.storeID = storeID;
        this.priceCents = priceCents;
        this.available = available;
        this.currency = currency;
        this.condition = condition;
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

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public Integer getPriceCents() {
        return priceCents;
    }

    public void setPriceCents(Integer priceCents) {
        this.priceCents = priceCents;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Offer offer)) return false;
        return getStoreID() == offer.getStoreID() && isAvailable() == offer.isAvailable() && Objects.equals(getProductId(), offer.getProductId()) && Objects.equals(getPriceCents(), offer.getPriceCents()) && Objects.equals(getCurrency(), offer.getCurrency()) && Objects.equals(getCondition(), offer.getCondition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getStoreID(), getPriceCents(), isAvailable(), getCurrency(), getCondition());
    }
}
