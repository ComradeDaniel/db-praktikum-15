package aufgabe3.hibernate.entity

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorColumn
import jakarta.persistence.DiscriminatorType
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.Inheritance
import jakarta.persistence.InheritanceType
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
abstract class Product {
    @Id
    @Column(name = "product_id")
    var productId: String = ""

    @Column(nullable = false)
    var title: String = ""

    @Column(name = "sales_rank")
    var salesRank: Int? = null

    @Column(name = "image_url")
    var imageUrl: String? = null

    var ean: String? = null

    @Column(name = "detail_url")
    var detailUrl: String? = null

    @Column(name = "avg_rating")
    var avgRating: BigDecimal? = null

    @Column(name = "num_reviews", nullable = false)
    var numReviews: Int = 0

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "productcategory",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")],
    )
    var categories: MutableSet<Category> = mutableSetOf()

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "similarproduct",
        joinColumns = [JoinColumn(name = "product_id")],
        inverseJoinColumns = [JoinColumn(name = "similar_product_id")],
    )
    var similarProducts: MutableSet<Product> = mutableSetOf()
}

@Entity
@Table(name = "book")
@DiscriminatorValue("Book")
class Book : Product() {
    var isbn: String? = null

    @Column(name = "page_count")
    var pageCount: Int? = null

    @Column(name = "release_date")
    var releaseDate: LocalDate? = null

    var binding: String? = null
    var edition: String? = null

    @Column(name = "package_weight")
    var packageWeight: Int? = null

    @Column(name = "package_height")
    var packageHeight: Int? = null

    @Column(name = "package_length")
    var packageLength: Int? = null
}

@Entity
@Table(name = "dvd")
@DiscriminatorValue("DVD")
class Dvd : Product() {
    var format: String? = null
    var runtime: Int? = null

    @Column(name = "region_code")
    var regionCode: Int? = null

    @Column(name = "release_date")
    var releaseDate: LocalDate? = null

    @Column(name = "theatrical_release")
    var theatricalRelease: Short? = null

    @Column(name = "aspect_ratio")
    var aspectRatio: String? = null

    @Column(name = "audio_format")
    var audioFormat: String? = null

    var upc: String? = null
}

@Entity
@Table(name = "musiccd")
@DiscriminatorValue("MusicCD")
class MusicCd : Product() {
    @Column(name = "release_date")
    var releaseDate: LocalDate? = null

    var binding: String? = null
    var format: String? = null

    @Column(name = "num_discs")
    var numDiscs: Int? = null

    var upc: String? = null
}
