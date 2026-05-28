package Aufgabe1.models;

public class Offer {
    private int id;
    private String productId;   // ASIN = FK auf Product
    private Store store;        // FK auf Store (store_id wird nach Insert gesetzt)
    private int priceCents;
    private String currency;
    private String condition;   // z.B. "new", "used"

    public Offer(
            String productId,
            Store store,
            int priceCents,
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

    public int getPriceCents() {
        return priceCents;
    }

    public void setPriceCents(int priceCents) {
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
