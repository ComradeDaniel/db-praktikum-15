package Aufgabe1.models;

public class Offer {
    private int id;
    private String productId;
    private Store store;
    private Integer priceCents;
    private String currency;
    private String condition;

    public Offer(
            String productId,
            Store store,
            Integer priceCents,
            String currency,
            String condition
    ) {
        this.productId = productId;
        this.store = store;
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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
