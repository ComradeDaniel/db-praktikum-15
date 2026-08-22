package aufgabe3.hibernate.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "studio")
class Studio {
    @Id
    var name: String = ""

    override fun equals(other: Any?): Boolean =
        this === other || (other is Studio && name == other.name)

    override fun hashCode(): Int = name.hashCode()

    override fun toString(): String = name
}
