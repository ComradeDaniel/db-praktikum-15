package aufgabe3.hibernate.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "review")
class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    var reviewId: Int = 0

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    var customer: Customer? = null

    @Column(nullable = false)
    var score: Short = 0

    var helpful: Int? = null

    @Column(name = "review_date")
    var reviewDate: LocalDate? = null

    var summary: String? = null
    var content: String? = null
}
