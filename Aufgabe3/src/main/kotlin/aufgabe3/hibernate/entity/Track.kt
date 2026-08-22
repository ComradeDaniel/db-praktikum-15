package aufgabe3.hibernate.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Track {
    @Column(name = "track_no")
    var trackNo: Int = 0

    var name: String = ""

    override fun equals(other: Any?): Boolean =
        this === other || (other is Track && trackNo == other.trackNo && name == other.name)

    override fun hashCode(): Int = 31 * trackNo + name.hashCode()

    override fun toString(): String = "$trackNo. $name"
}
