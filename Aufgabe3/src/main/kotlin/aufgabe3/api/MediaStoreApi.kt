package aufgabe3.api

import java.util.Properties

interface MediaStoreApi {
    fun init(properties: Properties)
    fun finish()

    fun getProduct(productId: String): ProductDetails?
    fun getProducts(pattern: String?): List<ProductSummary>
    fun getCategoryTree(): CategoryNode
    fun getProductsByCategoryPath(path: List<String>): List<ProductSummary>
    fun getTopProducts(k: Int): List<ProductSummary>
    fun getSimilarCheaperProduct(productId: String): List<ProductSummary>
    fun addNewReview(review: NewReview)
    fun getTrolls(maxAverageRating: Double): List<TrollUser>
    fun getOffers(productId: String): List<OfferInfo>
}
