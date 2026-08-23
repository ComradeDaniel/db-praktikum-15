package aufgabe3.api

import java.math.BigDecimal
import java.time.LocalDate

data class ProductSummary(
    val productId: String,
    val title: String,
    val productType: String,
    val avgRating: BigDecimal?,
    val salesRank: Int?,
    val minPriceCents: Int?,  
    val numReviews: Int,
)

data class ProductDetails(
    val productId: String,
    val title: String,
    val productType: String,
    val salesRank: Int?,
    val imageUrl: String?,
    val ean: String?,
    val detailUrl: String?,
    val avgRating: BigDecimal?,
    val numReviews: Int,
    val categories: List<String>,
    val book: BookDetails? = null,
    val dvd: DvdDetails? = null,
    val musicCd: MusicCdDetails? = null,
    
)

data class BookDetails(
    val isbn: String?,
    val pageCount: Int?,
    val releaseDate: LocalDate?,
    val binding: String?,
    val edition: String?,
    val authors: List<String>,
    val publishers: List<String>,
)

data class DvdDetails(
    val format: String?,
    val runtime: Int?,
    val regionCode: Int?,
    val releaseDate: LocalDate?,
    val aspectRatio: String?,
    val upc: String?,
    val audioFormat: String?,
    val theatricalRelease: Short?, 
    val studios: List<String>,
    val directors: List<String>,         
    val actors: List<String>,
    val creators: List<String>,
    val languages: List<LanguageInfo>,
)

data class MusicCdDetails(
    val releaseDate: LocalDate?,
    val binding: String?,
    val format: String?,
    val numDiscs: Int?,
    val upc: String?,
    val artists: List<String>,
    val labels: List<String>,
    val tracks: List<TrackInfo>,
)

data class TrackInfo(
    val trackNo: Int,
    val name:String,
)

data class LanguageInfo(
    val language: String,
    val type: String,
)

data class CategoryNode(
    val categoryId: Int,
    val name: String,
    val children: List<CategoryNode>,
)

data class NewReview(
    val productId: String,
    val username: String?,
    val score: Int,
    val helpful: Int? = null,
    val reviewDate: LocalDate? = null,
    val summary: String? = null,
    val content: String? = null,
)

data class TrollUser(
    val username: String,
    val averageScore: Double,
    val reviewCount: Long,
)

data class OfferInfo(
    val offerId: Int,
    val storeId: Int,
    val storeName: String?,
    val productId: String,
    val priceCents: Int?,
    val available: Boolean?,
    val currency: String?,
    val condition: String?,
)
