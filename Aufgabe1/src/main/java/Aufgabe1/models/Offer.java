package Aufgabe1.models;

public class Offer {
    private int id;
    private String productId;
    private int storeID;
    private Integer priceCents; // null = nicht verfuegbar
    private String currency;
    private String condition;

    public Offer(
            String productId,
            int storeID,
            Integer priceCents,
            String currency,
            String condition
    ) {
        this.productId = productId;
        this.storeID = storeID;
        this.priceCents = priceCents;
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
}
