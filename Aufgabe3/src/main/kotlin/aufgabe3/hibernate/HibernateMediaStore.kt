package aufgabe3.hibernate

import aufgabe3.api.CategoryNode
import aufgabe3.api.MediaStoreApi
import aufgabe3.api.NewReview
import aufgabe3.api.NotYetImplementedException
import aufgabe3.api.OfferInfo
import aufgabe3.api.ProductDetails
import aufgabe3.api.ProductSummary
import aufgabe3.api.TrollUser
import aufgabe3.hibernate.entity.Book
import aufgabe3.hibernate.entity.Category
import aufgabe3.hibernate.entity.Customer
import aufgabe3.hibernate.entity.Dvd
import aufgabe3.hibernate.entity.MusicCd
import aufgabe3.hibernate.entity.Offer
import aufgabe3.hibernate.entity.Product
import aufgabe3.hibernate.entity.Review
import aufgabe3.hibernate.entity.Store
import aufgabe3.hibernate.entity.Studio
import aufgabe3.hibernate.entity.Person
import aufgabe3.hibernate.entity.Publisher
import aufgabe3.hibernate.entity.Label
import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistry
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import java.util.Properties

class HibernateMediaStore : MediaStoreApi {
    private var registry: StandardServiceRegistry? = null
    private var sessionFactory: SessionFactory? = null

    override fun init(properties: Properties) {
        finish()

        val driver = required(properties, "db.driver")
        val url = required(properties, "db.url")
        val user = required(properties, "db.user")
        val password = properties.getProperty("db.password") ?: ""

        try {
            Class.forName(driver)
        } catch (e: ClassNotFoundException) {
            throw IllegalStateException("JDBC-Treiber nicht gefunden: $driver", e)
        }

        val settings = mapOf(
            "hibernate.connection.driver_class" to driver,
            "hibernate.connection.url" to url,
            "hibernate.connection.username" to user,
            "hibernate.connection.password" to password,
            "hibernate.hbm2ddl.auto" to "validate",
            "hibernate.jdbc.time_zone" to "UTC",
            "hibernate.connection.pool_size" to "10",
        )

        val builtRegistry = StandardServiceRegistryBuilder()
            .applySettings(settings)
            .build()
        registry = builtRegistry

        try {
            sessionFactory = MetadataSources(builtRegistry)
                .addAnnotatedClasses(
                    Product::class.java,
                    Book::class.java,
                    Dvd::class.java,
                    MusicCd::class.java,
                    Category::class.java,
                    Customer::class.java,
                    Store::class.java,
                    Offer::class.java,
                    Review::class.java,
                    Person::class.java,
                    Publisher::class.java,
                    Label::class.java,
                    Studio::class.java,
                )
                .buildMetadata()
                .buildSessionFactory()
        } catch (e: Exception) {
            StandardServiceRegistryBuilder.destroy(builtRegistry)
            registry = null
            throw e
        }
    }

    override fun finish() {
        sessionFactory?.close()
        sessionFactory = null
        registry?.let { StandardServiceRegistryBuilder.destroy(it) }
        registry = null
    }

    override fun getProduct(productId: String): ProductDetails? = notYet("getProduct")

    override fun getProducts(pattern: String?): List<ProductSummary> = notYet("getProducts")

    override fun getCategoryTree(): CategoryNode = notYet("getCategoryTree")

    override fun getProductsByCategoryPath(path: List<String>): List<ProductSummary> =
        notYet("getProductsByCategoryPath")

    override fun getTopProducts(k: Int): List<ProductSummary> = notYet("getTopProducts")

    override fun getSimilarCheaperProduct(productId: String): List<ProductSummary> =
        notYet("getSimilarCheaperProduct")

    override fun addNewReview(review: NewReview): Unit = notYet("addNewReview")

    override fun getTrolls(maxAverageRating: Double): List<TrollUser> = notYet("getTrolls")

    override fun getOffers(productId: String): List<OfferInfo> = notYet("getOffers")

    private fun required(properties: Properties, key: String): String {
        val value = properties.getProperty(key)
        if (value.isNullOrBlank()) {
            throw IllegalArgumentException("Property '$key' fehlt oder ist leer")
        }
        return value
    }

    private fun notYet(method: String): Nothing = throw NotYetImplementedException(method)
}
