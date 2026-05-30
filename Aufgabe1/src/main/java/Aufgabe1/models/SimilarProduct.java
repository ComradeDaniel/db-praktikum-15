package Aufgabe1.models;

import java.util.Objects;

/** Verknuepfung product_id -> similar_product_id (aus dem &lt;similars&gt;-Element). */
public class SimilarProduct {
    private final String productId;
    private final String similarProductId;

    public SimilarProduct(String productId, String similarProductId) {
        this.productId = productId;
        this.similarProductId = similarProductId;
    }

    public String getProductId() {
        return productId;
    }

    public String getSimilarProductId() {
        return similarProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimilarProduct that)) {
            return false;
        }
        return Objects.equals(productId, that.productId)
                && Objects.equals(similarProductId, that.similarProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, similarProductId);
    }
}
