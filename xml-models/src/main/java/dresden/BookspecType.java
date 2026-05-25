//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v4.0.5 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
//


package dresden;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für BookspecType complex type.</p>
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
 * 
 * <pre>{@code
 * <complexType name="BookspecType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="binding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="edition" type="{}ValAttributeType" minOccurs="0"/>
 *         <element name="isbn" type="{}ValAttributeType" minOccurs="0"/>
 *         <element name="package" type="{}PackageType" minOccurs="0"/>
 *         <element name="pages" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="publication" type="{}PublicationType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BookspecType", propOrder = {
    "binding",
    "edition",
    "isbn",
    "_package",
    "pages",
    "publication"
})
public class BookspecType {

    protected String binding;
    protected ValAttributeType edition;
    protected ValAttributeType isbn;
    @XmlElement(name = "package")
    protected PackageType _package;
    protected String pages;
    protected PublicationType publication;

    /**
     * Ruft den Wert der binding-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinding() {
        return binding;
    }

    /**
     * Legt den Wert der binding-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinding(String value) {
        this.binding = value;
    }

    /**
     * Ruft den Wert der edition-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ValAttributeType }
     *     
     */
    public ValAttributeType getEdition() {
        return edition;
    }

    /**
     * Legt den Wert der edition-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ValAttributeType }
     *     
     */
    public void setEdition(ValAttributeType value) {
        this.edition = value;
    }

    /**
     * Ruft den Wert der isbn-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ValAttributeType }
     *     
     */
    public ValAttributeType getIsbn() {
        return isbn;
    }

    /**
     * Legt den Wert der isbn-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ValAttributeType }
     *     
     */
    public void setIsbn(ValAttributeType value) {
        this.isbn = value;
    }

    /**
     * Ruft den Wert der package-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PackageType }
     *     
     */
    public PackageType getPackage() {
        return _package;
    }

    /**
     * Legt den Wert der package-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PackageType }
     *     
     */
    public void setPackage(PackageType value) {
        this._package = value;
    }

    /**
     * Ruft den Wert der pages-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPages() {
        return pages;
    }

    /**
     * Legt den Wert der pages-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPages(String value) {
        this.pages = value;
    }

    /**
     * Ruft den Wert der publication-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PublicationType }
     *     
     */
    public PublicationType getPublication() {
        return publication;
    }

    /**
     * Legt den Wert der publication-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PublicationType }
     *     
     */
    public void setPublication(PublicationType value) {
        this.publication = value;
    }

}
