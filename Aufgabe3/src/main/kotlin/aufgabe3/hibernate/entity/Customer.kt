package aufgabe3.hibernate.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customer")
class Customer {
    @Id
    var username: String = ""

    @Column(name = "delivery_address")
    var deliveryAddress: String? = null

    @Column(name = "account_number")
    var accountNumber: String? = null
}
