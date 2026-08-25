package aufgabe3.hibernate

import aufgabe3.api.CategoryNode
import aufgabe3.api.BookDetails
import aufgabe3.api.DvdDetails
import aufgabe3.api.LanguageInfo
import aufgabe3.api.MusicCdDetails
import aufgabe3.api.TrackInfo
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
import java.time.LocalDate
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

    override fun getProduct(productId: String): ProductDetails? = withSession { session ->
        val product = session.find(Product::class.java, productId)
        if (product == null) {
            throw NotFoundException("Produkt nicht gefunden: $productId")
        }

        product.toProductDetails()
    }

    override fun getProducts(pattern: String?): List<ProductSummary> = withSession { session ->
        val filter = if (pattern == null) "" else "where p.title like :pattern"

        val query = session.createSelectionQuery(
            """
            select p,
                   (select min(o.priceCents)
                    from Offer o
                    where o.product = p and o.priceCents is not null)
            from Product p
            $filter
            order by p.title
            """.trimIndent(),
            Tuple::class.java,
        )

        if (pattern != null) {
            query.setParameter("pattern", pattern)
        }

        query.resultList.map { row -> row.toProductSummary() }
    }

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

    override fun getTopProducts(k: Int): List<ProductSummary> {
        require(k > 0) { "k muss größer als 0 sein" }

        return withSession { session ->
            session
                .createSelectionQuery(
                    """
                    select p,
                           (select min(o.priceCents)
                            from Offer o
                            where o.product = p and o.priceCents is not null)
                    from Product p
                    where p.avgRating is not null
                    order by p.avgRating desc, p.numReviews desc, p.productId asc
                    """.trimIndent(),
                    Tuple::class.java,
                )
                .setMaxResults(k)
                .resultList
                .map { row -> row.toProductSummary() }
        }
    }

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

    override fun addNewReview(review: NewReview) {
        require(review.productId.isNotBlank()) { "productId darf nicht leer sein" }
        require(review.score in 1..5) { "score muss zwischen 1 und 5 liegen" }
        require(review.helpful == null || review.helpful >= 0) { "helpful darf nicht negativ sein" }

        val reviewDate = if (review.reviewDate == null) LocalDate.now() else review.reviewDate
        require(!reviewDate.isAfter(LocalDate.now())) { "reviewDate darf nicht in der Zukunft liegen" }

        withTransaction { session ->
            val product = session.find(Product::class.java, review.productId)
            if (product == null) {
                throw NotFoundException("Produkt nicht gefunden: ${review.productId}")
            }

            var customer: Customer? = null
            if (review.username != null) {
                customer = session.find(Customer::class.java, review.username)
                if (customer == null) {
                    throw NotFoundException("Kunde nicht gefunden: ${review.username}")
                }
            }

            val entity = Review()
            entity.product = product
            entity.customer = customer
            entity.score = review.score.toShort()
            entity.helpful = review.helpful
            entity.reviewDate = reviewDate
            entity.summary = review.summary
            entity.content = review.content

            session.persist(entity)
        }
    }

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

    override fun getOffers(productId: String): List<OfferInfo> = withSession { session ->
        session
            .createSelectionQuery(
                """
                select o from Offer o
                join fetch o.store
                where o.product.productId = :productId
                order by o.priceCents asc nulls last
                """.trimIndent(),
                Offer::class.java,
            )
            .setParameter("productId", productId)
            .resultList
            .map { offer -> offer.toOfferInfo() }
    }

    private fun <T> withSession(block: (Session) -> T): T {
        val factory = sessionFactory ?: error("init() wurde nicht aufgerufen")
        return factory.openSession().use(block)
    }

    private fun <T> withTransaction(block: (Session) -> T): T = withSession { session ->
        val transaction = session.beginTransaction()
        try {
            val result = block(session)
            transaction.commit()
            result
        } catch (e: Exception) {
            if (transaction.isActive) {
                transaction.rollback()
            }
            throw e
        }
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

    private fun Offer.toOfferInfo(): OfferInfo {
        val s = checkNotNull(store)
        val p = checkNotNull(product)
        return OfferInfo(
            offerId = offerId,
            storeId = s.storeId,
            storeName = s.name,
            productId = p.productId,
            priceCents = priceCents,
            available = available,
            currency = currency,
            condition = condition,
        )
    }

    private fun Product.toProductDetails(): ProductDetails {
        val book = if (this is Book) this.toBookDetails() else null
        val dvd = if (this is Dvd) this.toDvdDetails() else null
        val musicCd = if (this is MusicCd) this.toMusicCdDetails() else null

        return ProductDetails(
            productId = productId,
            title = title,
            productType = productTypeOf(this),
            salesRank = salesRank,
            imageUrl = imageUrl,
            ean = ean,
            detailUrl = detailUrl,
            avgRating = avgRating,
            numReviews = numReviews,
            categories = categories.map { category -> category.name }.sorted(),
            book = book,
            dvd = dvd,
            musicCd = musicCd,
        )
    }

    private fun Book.toBookDetails(): BookDetails = BookDetails(
        isbn = isbn,
        pageCount = pageCount,
        releaseDate = releaseDate,
        binding = binding,
        edition = edition,
        authors = authors.map { person -> person.name }.sorted(),
        publishers = publishers.map { publisher -> publisher.name }.sorted(),
    )

    private fun Dvd.toDvdDetails(): DvdDetails = DvdDetails(
        format = format,
        runtime = runtime,
        regionCode = regionCode,
        releaseDate = releaseDate,
        aspectRatio = aspectRatio,
        upc = upc,
        audioFormat = audioFormat,
        theatricalRelease = theatricalRelease,
        studios = studios.map { studio -> studio.name }.sorted(),
        directors = personsWithRole("Director"),
        actors = personsWithRole("Actor"),
        creators = personsWithRole("Creator"),
        languages = languages.map { entry -> LanguageInfo(entry.language, entry.languageType) },
    )

    private fun Dvd.personsWithRole(role: String): List<String> = persons
        .filter { entry -> entry.role == role }
        .map { entry -> checkNotNull(entry.person).name }
        .sorted()

    private fun MusicCd.toMusicCdDetails(): MusicCdDetails = MusicCdDetails(
        releaseDate = releaseDate,
        binding = binding,
        format = format,
        numDiscs = numDiscs,
        upc = upc,
        artists = artists.map { person -> person.name }.sorted(),
        labels = labels.map { label -> label.name }.sorted(),
        tracks = tracks.map { track -> TrackInfo(track.trackNo, track.name) },
    )

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
