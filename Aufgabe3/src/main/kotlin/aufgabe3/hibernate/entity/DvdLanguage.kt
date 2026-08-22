package aufgabe3.hibernate.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class DvdLanguage {
    @Column(name = "language", nullable = false)
    var language: String = ""

    @Column(name = "language_type", nullable = false)
    var languageType: String = ""

    override fun equals(other: Any?): Boolean =
        this === other ||
            (other is DvdLanguage && language == other.language && languageType == other.languageType)

    override fun hashCode(): Int = 31 * language.hashCode() + languageType.hashCode()

    override fun toString(): String = "$language ($languageType)"
}
