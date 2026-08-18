package aufgabe3.web

import aufgabe3.api.MediaStoreApi
import aufgabe3.api.NewReview
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MediaStoreController(
    private val mediaStore: MediaStoreApi,
) {
    @GetMapping("/health")
    fun health(): Map<String, String> = mapOf("status" to "ready")

    @GetMapping("/products/{productId}")
    fun getProduct(@PathVariable productId: String) = mediaStore.getProduct(productId)

    @GetMapping("/products")
    fun getProducts(@RequestParam(required = false) pattern: String?) =
        mediaStore.getProducts(pattern)

    @GetMapping("/categories/tree")
    fun getCategoryTree() = mediaStore.getCategoryTree()

    @GetMapping("/categories/products")
    fun getProductsByCategoryPath(@RequestParam path: String) =
        mediaStore.getProductsByCategoryPath(parsePath(path))

    @GetMapping("/top-products")
    fun getTopProducts(@RequestParam(defaultValue = "5") k: Int) =
        mediaStore.getTopProducts(k)

    @GetMapping("/products/{productId}/similar-cheaper")
    fun getSimilarCheaperProduct(@PathVariable productId: String) =
        mediaStore.getSimilarCheaperProduct(productId)

    @PostMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    fun addNewReview(@RequestBody review: NewReview) = mediaStore.addNewReview(review)

    @GetMapping("/trolls")
    fun getTrolls(@RequestParam maxAverageRating: Double) =
        mediaStore.getTrolls(maxAverageRating)

    @GetMapping("/products/{productId}/offers")
    fun getOffers(@PathVariable productId: String) = mediaStore.getOffers(productId)

    private fun parsePath(path: String): List<String> =
        path.split('/').map { it.trim() }.filter { it.isNotEmpty() }
}
