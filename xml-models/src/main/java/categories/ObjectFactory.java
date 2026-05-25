//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v4.0.5 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
//


package categories;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the categories package. 
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _Item_QNAME = new QName("", "item");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: categories
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Categories }
     * 
     * @return
     *     the new instance of {@link Categories }
     */
    public Categories createCategories() {
        return new Categories();
    }

    /**
     * Create an instance of {@link Category }
     * 
     * @return
     *     the new instance of {@link Category }
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "item")
    public JAXBElement<String> createItem(String value) {
        return new JAXBElement<>(_Item_QNAME, String.class, null, value);
    }

}
