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
import aufgabe3.hibernate.entity.Label
import aufgabe3.hibernate.entity.MusicCd
import aufgabe3.hibernate.entity.NotFoundException
import aufgabe3.hibernate.entity.Offer
import aufgabe3.hibernate.entity.Person
import aufgabe3.hibernate.entity.Product
import aufgabe3.hibernate.entity.Publisher
import aufgabe3.hibernate.entity.Review
import aufgabe3.hibernate.entity.Store
import aufgabe3.hibernate.entity.Studio
import jakarta.persistence.Tuple
import org.hibernate.Session
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

    override fun getCategoryTree(): CategoryNode = withSession { session ->
        val all = session
            .createSelectionQuery("from Category", Category::class.java)
            .resultList

        val nodes = all.associate { category ->
            category.categoryId to CategoryNode(category.categoryId, category.name, mutableListOf())
        }

        val roots = mutableListOf<CategoryNode>()
        for (category in all) {
            val node = nodes.getValue(category.categoryId)
            val parentId = category.parent?.categoryId
            if (parentId == null) {
                roots += node
            } else {
                (nodes.getValue(parentId).children as MutableList) += node
            }
        }

        CategoryNode(0, "root", roots)
    }

    override fun getProductsByCategoryPath(path: List<String>): List<ProductSummary> {
        if (path.isEmpty()) {
            throw IllegalArgumentException("Kategoriepfad ist leer")
        }

        return withSession { session ->
            var current = session
                .createSelectionQuery(
                    "from Category c where c.name = :name and c.parent is null",
                    Category::class.java,
                )
                .setParameter("name", path.first())
                .resultList
                .firstOrNull()
                ?: throw NotFoundException("Kategorie nicht gefunden: ${path.first()}")

            for (name in path.drop(1)) {
                current = session
                    .createSelectionQuery(
                        "from Category c where c.name = :name and c.parent.categoryId = :parentId",
                        Category::class.java,
                    )
                    .setParameter("name", name)
                    .setParameter("parentId", current.categoryId)
                    .resultList
                    .firstOrNull()
                    ?: throw NotFoundException("Kategorie nicht gefunden: $name")
            }

            session
                .createSelectionQuery(
                    """
                    select p,
                           (select min(o.priceCents)
                            from Offer o
                            where o.product = p and o.priceCents is not null)
                    from Product p
                    join p.categories c
                    where c.categoryId = :id
                    order by p.title
                    """.trimIndent(),
                    Tuple::class.java,
                )
                .setParameter("id", current.categoryId)
                .resultList
                .map { it.toProductSummary() }
        }
    }

    override fun getTopProducts(k: Int): List<ProductSummary> = notYet("getTopProducts")

    override fun getSimilarCheaperProduct(productId: String): List<ProductSummary> =
        withSession { session ->
            session
                // wenn das gegebene produkt keinen preis hat dann gibt es dafür auch keine offer die günstiger sind
                .createSelectionQuery(
                    """
                    select sim,
                           (select min(oSim.priceCents)
                            from Offer oSim
                            where oSim.product = sim and oSim.priceCents is not null)
                    from Product p
                    join p.similarProducts sim
                    where p.productId = :id
                      and (
                        select min(oSim.priceCents)
                        from Offer oSim
                        where oSim.product = sim and oSim.priceCents is not null
                      ) < (
                        select min(oSrc.priceCents)
                        from Offer oSrc
                        where oSrc.product = p and oSrc.priceCents is not null
                      )
                    order by sim.title
                    """.trimIndent(),
                    Tuple::class.java,
                )
                .setParameter("id", productId)
                .resultList
                .map { it.toProductSummary() }
        }

    override fun addNewReview(review: NewReview): Unit = notYet("addNewReview")

    override fun getTrolls(maxAverageRating: Double): List<TrollUser> = withSession { session ->
        session
            .createSelectionQuery(
                """
                select r.customer.username, avg(r.score), count(r)
                from Review r
                where r.customer is not null
                group by r.customer.username
                having avg(r.score) < :max
                order by avg(r.score)
                """.trimIndent(),
                Tuple::class.java,
            )
            .setParameter("max", maxAverageRating)
            .resultList
            .map { row ->
                TrollUser(
                    username = row.get(0, String::class.java),
                    averageScore = (row.get(1) as Number).toDouble(),
                    reviewCount = (row.get(2) as Number).toLong(),
                )
            }
    }

    override fun getOffers(productId: String): List<OfferInfo> = notYet("getOffers")

    private fun <T> withSession(block: (Session) -> T): T {
        val factory = sessionFactory ?: error("init() wurde nicht aufgerufen")
        return factory.openSession().use(block)
    }

    private fun Tuple.toProductSummary(): ProductSummary {
        val product = get(0, Product::class.java)
        val minPrice = get(1) as Number?
        return ProductSummary(
            productId = product.productId,
            title = product.title,
            productType = productTypeOf(product),
            avgRating = product.avgRating,
            salesRank = product.salesRank,
            minPriceCents = minPrice?.toInt(),
            numReviews = product.numReviews,
        )
    }

    private fun productTypeOf(product: Product): String = when (product) {
        is Book -> "Book"
        is Dvd -> "DVD"
        is MusicCd -> "MusicCD"
        else -> product.javaClass.simpleName
    }

    private fun required(properties: Properties, key: String): String {
        val value = properties.getProperty(key)
        if (value.isNullOrBlank()) {
            throw IllegalArgumentException("Property '$key' fehlt oder ist leer")
        }
        return value
    }

    private fun notYet(method: String): Nothing = throw NotYetImplementedException(method)
}
