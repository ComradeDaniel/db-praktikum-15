package aufgabe3.hibernate.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "store")
class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    var storeId: Int = 0

    @Column(nullable = false)
    var name: String = ""

    var street: String? = null
    var zip: String? = null
}
