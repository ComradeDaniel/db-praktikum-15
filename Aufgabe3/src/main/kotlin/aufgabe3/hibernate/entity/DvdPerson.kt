package aufgabe3.hibernate.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Embeddable
class DvdPerson {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person", referencedColumnName = "name", nullable = false)
    var person: Person? = null

    @Column(name = "role", nullable = false)
    var role: String = ""

    override fun equals(other: Any?): Boolean =
        this === other || (other is DvdPerson && person == other.person && role == other.role)

    override fun hashCode(): Int = 31 * (person?.hashCode() ?: 0) + role.hashCode()

    override fun toString(): String = "${person?.name} ($role)"
}
