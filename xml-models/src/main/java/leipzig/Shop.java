//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v4.0.5 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
//


package leipzig;

import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java-Klasse für anonymous complex type.</p>
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="item" maxOccurs="unbounded">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="price">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <attribute name="mult" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           <attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           <attribute name="currency" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   <element name="bookspec" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="binding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="edition" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="val" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                             <element name="isbn" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="val" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                             <element name="package" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     <attribute name="height" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     <attribute name="length" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                             <element name="pages" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="publication" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="date" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="dvdspec" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="upc" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                             <element name="audio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                             <element name="aspectratio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="regioncode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="releasedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="runningtime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="theatr_release" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="musicspec" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="binding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="format" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                             <element name="num_discs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="releasedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="upc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="labels" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="label" maxOccurs="unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="publishers" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="publisher" maxOccurs="unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="studios" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="studio" maxOccurs="unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="similars" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="sim_product" maxOccurs="unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <sequence>
 *                                       <element name="asin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       <element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     </sequence>
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="tracks" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="title" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="audiotext" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <any processContents='skip' maxOccurs="unbounded" minOccurs="0"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="listmania" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="list" maxOccurs="unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="actors" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="actor" maxOccurs="unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="artists" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="artist" maxOccurs="unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="authors" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="author" maxOccurs="unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="creators" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="creator" maxOccurs="unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                   <element name="directors" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence minOccurs="0">
 *                             <element name="director" maxOccurs="unbounded" minOccurs="0">
 *                               <complexType>
 *                                 <complexContent>
 *                                   <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   </restriction>
 *                                 </complexContent>
 *                               </complexType>
 *                             </element>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *                 <attribute name="pgroup" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="asin" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="salesrank" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="picture" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="detailpage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 <attribute name="ean" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </sequence>
 *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="street" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="zip" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "item"
})
@XmlRootElement(name = "shop")
public class Shop {

    @XmlElement(required = true)
    protected java.util.List<Shop.Item> item;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "street")
    protected String street;
    @XmlAttribute(name = "zip")
    protected String zip;

    /**
     * Gets the value of the item property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Shop.Item }
     * </p>
     * 
     * 
     * @return
     *     The value of the item property.
     */
    public java.util.List<Shop.Item> getItem() {
        if (item == null) {
            item = new ArrayList<>();
        }
        return this.item;
    }

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der street-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Legt den Wert der street-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Ruft den Wert der zip-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * Legt den Wert der zip-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.</p>
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="price">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <attribute name="mult" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 <attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 <attribute name="currency" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         <element name="bookspec" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="binding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="edition" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="val" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                   <element name="isbn" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="val" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                   <element name="package" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           <attribute name="height" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           <attribute name="length" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                   <element name="pages" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="publication" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="date" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="dvdspec" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="upc" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                   <element name="audio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *                   <element name="aspectratio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="regioncode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="releasedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="runningtime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="theatr_release" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="musicspec" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="binding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="format" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                   <element name="num_discs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="releasedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="upc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="labels" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="label" maxOccurs="unbounded" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="publishers" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="publisher" maxOccurs="unbounded" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="studios" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="studio" maxOccurs="unbounded" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="similars" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="sim_product" maxOccurs="unbounded" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <sequence>
     *                             <element name="asin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             <element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           </sequence>
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="tracks" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="title" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="audiotext" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <any processContents='skip' maxOccurs="unbounded" minOccurs="0"/>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="listmania" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="list" maxOccurs="unbounded" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="actors" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="actor" maxOccurs="unbounded" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="artists" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="artist" maxOccurs="unbounded" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="authors" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="author" maxOccurs="unbounded" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="creators" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="creator" maxOccurs="unbounded" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *         <element name="directors" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence minOccurs="0">
     *                   <element name="director" maxOccurs="unbounded" minOccurs="0">
     *                     <complexType>
     *                       <complexContent>
     *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         </restriction>
     *                       </complexContent>
     *                     </complexType>
     *                   </element>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *       </sequence>
     *       <attribute name="pgroup" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="asin" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="salesrank" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="picture" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="detailpage" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       <attribute name="ean" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "price",
        "title",
        "bookspec",
        "dvdspec",
        "musicspec",
        "labels",
        "publishers",
        "studios",
        "similars",
        "tracks",
        "audiotext",
        "listmania",
        "actors",
        "artists",
        "authors",
        "creators",
        "directors"
    })
    public static class Item {

        @XmlElement(required = true)
        protected Shop.Item.Price price;
        @XmlElement(required = true)
        protected String title;
        protected Shop.Item.Bookspec bookspec;
        protected Shop.Item.Dvdspec dvdspec;
        protected Shop.Item.Musicspec musicspec;
        protected Shop.Item.Labels labels;
        protected Shop.Item.Publishers publishers;
        protected Shop.Item.Studios studios;
        protected Shop.Item.Similars similars;
        protected Shop.Item.Tracks tracks;
        protected Shop.Item.Audiotext audiotext;
        protected Shop.Item.Listmania listmania;
        protected Shop.Item.Actors actors;
        protected Shop.Item.Artists artists;
        protected Shop.Item.Authors authors;
        protected Shop.Item.Creators creators;
        protected Shop.Item.Directors directors;
        @XmlAttribute(name = "pgroup")
        protected String pgroup;
        @XmlAttribute(name = "asin")
        protected String asin;
        @XmlAttribute(name = "salesrank")
        protected String salesrank;
        @XmlAttribute(name = "picture")
        protected String picture;
        @XmlAttribute(name = "detailpage")
        protected String detailpage;
        @XmlAttribute(name = "ean")
        protected String ean;

        /**
         * Ruft den Wert der price-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Price }
         *     
         */
        public Shop.Item.Price getPrice() {
            return price;
        }

        /**
         * Legt den Wert der price-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Price }
         *     
         */
        public void setPrice(Shop.Item.Price value) {
            this.price = value;
        }

        /**
         * Ruft den Wert der title-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTitle() {
            return title;
        }

        /**
         * Legt den Wert der title-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTitle(String value) {
            this.title = value;
        }

        /**
         * Ruft den Wert der bookspec-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Bookspec }
         *     
         */
        public Shop.Item.Bookspec getBookspec() {
            return bookspec;
        }

        /**
         * Legt den Wert der bookspec-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Bookspec }
         *     
         */
        public void setBookspec(Shop.Item.Bookspec value) {
            this.bookspec = value;
        }

        /**
         * Ruft den Wert der dvdspec-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Dvdspec }
         *     
         */
        public Shop.Item.Dvdspec getDvdspec() {
            return dvdspec;
        }

        /**
         * Legt den Wert der dvdspec-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Dvdspec }
         *     
         */
        public void setDvdspec(Shop.Item.Dvdspec value) {
            this.dvdspec = value;
        }

        /**
         * Ruft den Wert der musicspec-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Musicspec }
         *     
         */
        public Shop.Item.Musicspec getMusicspec() {
            return musicspec;
        }

        /**
         * Legt den Wert der musicspec-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Musicspec }
         *     
         */
        public void setMusicspec(Shop.Item.Musicspec value) {
            this.musicspec = value;
        }

        /**
         * Ruft den Wert der labels-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Labels }
         *     
         */
        public Shop.Item.Labels getLabels() {
            return labels;
        }

        /**
         * Legt den Wert der labels-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Labels }
         *     
         */
        public void setLabels(Shop.Item.Labels value) {
            this.labels = value;
        }

        /**
         * Ruft den Wert der publishers-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Publishers }
         *     
         */
        public Shop.Item.Publishers getPublishers() {
            return publishers;
        }

        /**
         * Legt den Wert der publishers-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Publishers }
         *     
         */
        public void setPublishers(Shop.Item.Publishers value) {
            this.publishers = value;
        }

        /**
         * Ruft den Wert der studios-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Studios }
         *     
         */
        public Shop.Item.Studios getStudios() {
            return studios;
        }

        /**
         * Legt den Wert der studios-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Studios }
         *     
         */
        public void setStudios(Shop.Item.Studios value) {
            this.studios = value;
        }

        /**
         * Ruft den Wert der similars-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Similars }
         *     
         */
        public Shop.Item.Similars getSimilars() {
            return similars;
        }

        /**
         * Legt den Wert der similars-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Similars }
         *     
         */
        public void setSimilars(Shop.Item.Similars value) {
            this.similars = value;
        }

        /**
         * Ruft den Wert der tracks-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Tracks }
         *     
         */
        public Shop.Item.Tracks getTracks() {
            return tracks;
        }

        /**
         * Legt den Wert der tracks-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Tracks }
         *     
         */
        public void setTracks(Shop.Item.Tracks value) {
            this.tracks = value;
        }

        /**
         * Ruft den Wert der audiotext-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Audiotext }
         *     
         */
        public Shop.Item.Audiotext getAudiotext() {
            return audiotext;
        }

        /**
         * Legt den Wert der audiotext-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Audiotext }
         *     
         */
        public void setAudiotext(Shop.Item.Audiotext value) {
            this.audiotext = value;
        }

        /**
         * Ruft den Wert der listmania-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Listmania }
         *     
         */
        public Shop.Item.Listmania getListmania() {
            return listmania;
        }

        /**
         * Legt den Wert der listmania-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Listmania }
         *     
         */
        public void setListmania(Shop.Item.Listmania value) {
            this.listmania = value;
        }

        /**
         * Ruft den Wert der actors-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Actors }
         *     
         */
        public Shop.Item.Actors getActors() {
            return actors;
        }

        /**
         * Legt den Wert der actors-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Actors }
         *     
         */
        public void setActors(Shop.Item.Actors value) {
            this.actors = value;
        }

        /**
         * Ruft den Wert der artists-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Artists }
         *     
         */
        public Shop.Item.Artists getArtists() {
            return artists;
        }

        /**
         * Legt den Wert der artists-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Artists }
         *     
         */
        public void setArtists(Shop.Item.Artists value) {
            this.artists = value;
        }

        /**
         * Ruft den Wert der authors-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Authors }
         *     
         */
        public Shop.Item.Authors getAuthors() {
            return authors;
        }

        /**
         * Legt den Wert der authors-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Authors }
         *     
         */
        public void setAuthors(Shop.Item.Authors value) {
            this.authors = value;
        }

        /**
         * Ruft den Wert der creators-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Creators }
         *     
         */
        public Shop.Item.Creators getCreators() {
            return creators;
        }

        /**
         * Legt den Wert der creators-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Creators }
         *     
         */
        public void setCreators(Shop.Item.Creators value) {
            this.creators = value;
        }

        /**
         * Ruft den Wert der directors-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link Shop.Item.Directors }
         *     
         */
        public Shop.Item.Directors getDirectors() {
            return directors;
        }

        /**
         * Legt den Wert der directors-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link Shop.Item.Directors }
         *     
         */
        public void setDirectors(Shop.Item.Directors value) {
            this.directors = value;
        }

        /**
         * Ruft den Wert der pgroup-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPgroup() {
            return pgroup;
        }

        /**
         * Legt den Wert der pgroup-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPgroup(String value) {
            this.pgroup = value;
        }

        /**
         * Ruft den Wert der asin-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAsin() {
            return asin;
        }

        /**
         * Legt den Wert der asin-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAsin(String value) {
            this.asin = value;
        }

        /**
         * Ruft den Wert der salesrank-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSalesrank() {
            return salesrank;
        }

        /**
         * Legt den Wert der salesrank-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSalesrank(String value) {
            this.salesrank = value;
        }

        /**
         * Ruft den Wert der picture-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPicture() {
            return picture;
        }

        /**
         * Legt den Wert der picture-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPicture(String value) {
            this.picture = value;
        }

        /**
         * Ruft den Wert der detailpage-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDetailpage() {
            return detailpage;
        }

        /**
         * Legt den Wert der detailpage-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDetailpage(String value) {
            this.detailpage = value;
        }

        /**
         * Ruft den Wert der ean-Eigenschaft ab.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEan() {
            return ean;
        }

        /**
         * Legt den Wert der ean-Eigenschaft fest.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEan(String value) {
            this.ean = value;
        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="actor" maxOccurs="unbounded" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "actor"
        })
        public static class Actors {

            protected java.util.List<Shop.Item.Actors.Actor> actor;

            /**
             * Gets the value of the actor property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the actor property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getActor().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Shop.Item.Actors.Actor }
             * </p>
             * 
             * 
             * @return
             *     The value of the actor property.
             */
            public java.util.List<Shop.Item.Actors.Actor> getActor() {
                if (actor == null) {
                    actor = new ArrayList<>();
                }
                return this.actor;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Actor {

                @XmlAttribute(name = "name")
                protected String name;

                /**
                 * Ruft den Wert der name-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Legt den Wert der name-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="artist" maxOccurs="unbounded" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "artist"
        })
        public static class Artists {

            protected java.util.List<Shop.Item.Artists.Artist> artist;

            /**
             * Gets the value of the artist property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the artist property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getArtist().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Shop.Item.Artists.Artist }
             * </p>
             * 
             * 
             * @return
             *     The value of the artist property.
             */
            public java.util.List<Shop.Item.Artists.Artist> getArtist() {
                if (artist == null) {
                    artist = new ArrayList<>();
                }
                return this.artist;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Artist {

                @XmlAttribute(name = "name")
                protected String name;

                /**
                 * Ruft den Wert der name-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Legt den Wert der name-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <any processContents='skip' maxOccurs="unbounded" minOccurs="0"/>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "any"
        })
        public static class Audiotext {

            @XmlAnyElement
            protected java.util.List<Element> any;

            /**
             * Gets the value of the any property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the any property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getAny().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Element }
             * </p>
             * 
             * 
             * @return
             *     The value of the any property.
             */
            public java.util.List<Element> getAny() {
                if (any == null) {
                    any = new ArrayList<>();
                }
                return this.any;
            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="author" maxOccurs="unbounded" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "author"
        })
        public static class Authors {

            protected java.util.List<Shop.Item.Authors.Author> author;

            /**
             * Gets the value of the author property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the author property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getAuthor().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Shop.Item.Authors.Author }
             * </p>
             * 
             * 
             * @return
             *     The value of the author property.
             */
            public java.util.List<Shop.Item.Authors.Author> getAuthor() {
                if (author == null) {
                    author = new ArrayList<>();
                }
                return this.author;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Author {

                @XmlAttribute(name = "name")
                protected String name;

                /**
                 * Ruft den Wert der name-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Legt den Wert der name-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="binding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="edition" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="val" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *         <element name="isbn" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="val" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *         <element name="package" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 <attribute name="height" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 <attribute name="length" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *         <element name="pages" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="publication" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="date" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "binding",
            "edition",
            "isbn",
            "_package",
            "pages",
            "publication"
        })
        public static class Bookspec {

            protected String binding;
            protected Shop.Item.Bookspec.Edition edition;
            protected Shop.Item.Bookspec.Isbn isbn;
            @XmlElement(name = "package")
            protected Shop.Item.Bookspec.Package _package;
            protected String pages;
            protected Shop.Item.Bookspec.Publication publication;

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
             *     {@link Shop.Item.Bookspec.Edition }
             *     
             */
            public Shop.Item.Bookspec.Edition getEdition() {
                return edition;
            }

            /**
             * Legt den Wert der edition-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Shop.Item.Bookspec.Edition }
             *     
             */
            public void setEdition(Shop.Item.Bookspec.Edition value) {
                this.edition = value;
            }

            /**
             * Ruft den Wert der isbn-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Shop.Item.Bookspec.Isbn }
             *     
             */
            public Shop.Item.Bookspec.Isbn getIsbn() {
                return isbn;
            }

            /**
             * Legt den Wert der isbn-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Shop.Item.Bookspec.Isbn }
             *     
             */
            public void setIsbn(Shop.Item.Bookspec.Isbn value) {
                this.isbn = value;
            }

            /**
             * Ruft den Wert der package-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Shop.Item.Bookspec.Package }
             *     
             */
            public Shop.Item.Bookspec.Package getPackage() {
                return _package;
            }

            /**
             * Legt den Wert der package-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Shop.Item.Bookspec.Package }
             *     
             */
            public void setPackage(Shop.Item.Bookspec.Package value) {
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
             *     {@link Shop.Item.Bookspec.Publication }
             *     
             */
            public Shop.Item.Bookspec.Publication getPublication() {
                return publication;
            }

            /**
             * Legt den Wert der publication-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Shop.Item.Bookspec.Publication }
             *     
             */
            public void setPublication(Shop.Item.Bookspec.Publication value) {
                this.publication = value;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="val" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Edition {

                @XmlAttribute(name = "val")
                protected String val;

                /**
                 * Ruft den Wert der val-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getVal() {
                    return val;
                }

                /**
                 * Legt den Wert der val-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setVal(String value) {
                    this.val = value;
                }

            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="val" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Isbn {

                @XmlAttribute(name = "val")
                protected String val;

                /**
                 * Ruft den Wert der val-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getVal() {
                    return val;
                }

                /**
                 * Legt den Wert der val-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setVal(String value) {
                    this.val = value;
                }

            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       <attribute name="height" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       <attribute name="length" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Package {

                @XmlAttribute(name = "weight")
                protected String weight;
                @XmlAttribute(name = "height")
                protected String height;
                @XmlAttribute(name = "length")
                protected String length;

                /**
                 * Ruft den Wert der weight-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getWeight() {
                    return weight;
                }

                /**
                 * Legt den Wert der weight-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setWeight(String value) {
                    this.weight = value;
                }

                /**
                 * Ruft den Wert der height-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getHeight() {
                    return height;
                }

                /**
                 * Legt den Wert der height-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setHeight(String value) {
                    this.height = value;
                }

                /**
                 * Ruft den Wert der length-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getLength() {
                    return length;
                }

                /**
                 * Legt den Wert der length-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setLength(String value) {
                    this.length = value;
                }

            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="date" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Publication {

                @XmlAttribute(name = "date")
                protected String date;

                /**
                 * Ruft den Wert der date-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDate() {
                    return date;
                }

                /**
                 * Legt den Wert der date-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDate(String value) {
                    this.date = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="creator" maxOccurs="unbounded" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "creator"
        })
        public static class Creators {

            protected java.util.List<Shop.Item.Creators.Creator> creator;

            /**
             * Gets the value of the creator property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the creator property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getCreator().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Shop.Item.Creators.Creator }
             * </p>
             * 
             * 
             * @return
             *     The value of the creator property.
             */
            public java.util.List<Shop.Item.Creators.Creator> getCreator() {
                if (creator == null) {
                    creator = new ArrayList<>();
                }
                return this.creator;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Creator {

                @XmlAttribute(name = "name")
                protected String name;

                /**
                 * Ruft den Wert der name-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Legt den Wert der name-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="director" maxOccurs="unbounded" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "director"
        })
        public static class Directors {

            protected java.util.List<Shop.Item.Directors.Director> director;

            /**
             * Gets the value of the director property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the director property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getDirector().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Shop.Item.Directors.Director }
             * </p>
             * 
             * 
             * @return
             *     The value of the director property.
             */
            public java.util.List<Shop.Item.Directors.Director> getDirector() {
                if (director == null) {
                    director = new ArrayList<>();
                }
                return this.director;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Director {

                @XmlAttribute(name = "name")
                protected String name;

                /**
                 * Ruft den Wert der name-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Legt den Wert der name-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="upc" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *         <element name="audio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
         *         <element name="aspectratio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="regioncode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="releasedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="runningtime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="theatr_release" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "format",
            "upc",
            "audio",
            "aspectratio",
            "regioncode",
            "releasedate",
            "runningtime",
            "theatrRelease"
        })
        public static class Dvdspec {

            protected String format;
            protected Shop.Item.Dvdspec.Upc upc;
            protected java.util.List<String> audio;
            protected String aspectratio;
            protected String regioncode;
            protected String releasedate;
            protected String runningtime;
            @XmlElement(name = "theatr_release")
            protected String theatrRelease;

            /**
             * Ruft den Wert der format-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFormat() {
                return format;
            }

            /**
             * Legt den Wert der format-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFormat(String value) {
                this.format = value;
            }

            /**
             * Ruft den Wert der upc-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Shop.Item.Dvdspec.Upc }
             *     
             */
            public Shop.Item.Dvdspec.Upc getUpc() {
                return upc;
            }

            /**
             * Legt den Wert der upc-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Shop.Item.Dvdspec.Upc }
             *     
             */
            public void setUpc(Shop.Item.Dvdspec.Upc value) {
                this.upc = value;
            }

            /**
             * Gets the value of the audio property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the audio property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getAudio().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * </p>
             * 
             * 
             * @return
             *     The value of the audio property.
             */
            public java.util.List<String> getAudio() {
                if (audio == null) {
                    audio = new ArrayList<>();
                }
                return this.audio;
            }

            /**
             * Ruft den Wert der aspectratio-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAspectratio() {
                return aspectratio;
            }

            /**
             * Legt den Wert der aspectratio-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAspectratio(String value) {
                this.aspectratio = value;
            }

            /**
             * Ruft den Wert der regioncode-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRegioncode() {
                return regioncode;
            }

            /**
             * Legt den Wert der regioncode-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRegioncode(String value) {
                this.regioncode = value;
            }

            /**
             * Ruft den Wert der releasedate-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReleasedate() {
                return releasedate;
            }

            /**
             * Legt den Wert der releasedate-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReleasedate(String value) {
                this.releasedate = value;
            }

            /**
             * Ruft den Wert der runningtime-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRunningtime() {
                return runningtime;
            }

            /**
             * Legt den Wert der runningtime-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRunningtime(String value) {
                this.runningtime = value;
            }

            /**
             * Ruft den Wert der theatrRelease-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTheatrRelease() {
                return theatrRelease;
            }

            /**
             * Legt den Wert der theatrRelease-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTheatrRelease(String value) {
                this.theatrRelease = value;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Upc {

                @XmlAttribute(name = "value")
                protected String value;

                /**
                 * Ruft den Wert der value-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Legt den Wert der value-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="label" maxOccurs="unbounded" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "label"
        })
        public static class Labels {

            protected java.util.List<Shop.Item.Labels.Label> label;

            /**
             * Gets the value of the label property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the label property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getLabel().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Shop.Item.Labels.Label }
             * </p>
             * 
             * 
             * @return
             *     The value of the label property.
             */
            public java.util.List<Shop.Item.Labels.Label> getLabel() {
                if (label == null) {
                    label = new ArrayList<>();
                }
                return this.label;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Label {

                @XmlAttribute(name = "name")
                protected String name;

                /**
                 * Ruft den Wert der name-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Legt den Wert der name-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="list" maxOccurs="unbounded" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "list"
        })
        public static class Listmania {

            protected java.util.List<Shop.Item.Listmania.List> list;

            /**
             * Gets the value of the list property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the list property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getList().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Shop.Item.Listmania.List }
             * </p>
             * 
             * 
             * @return
             *     The value of the list property.
             */
            public java.util.List<Shop.Item.Listmania.List> getList() {
                if (list == null) {
                    list = new ArrayList<>();
                }
                return this.list;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class List {

                @XmlAttribute(name = "name")
                protected String name;

                /**
                 * Ruft den Wert der name-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Legt den Wert der name-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="binding" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="format" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *         <element name="num_discs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="releasedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="upc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "binding",
            "format",
            "numDiscs",
            "releasedate",
            "upc"
        })
        public static class Musicspec {

            protected String binding;
            protected Shop.Item.Musicspec.Format format;
            @XmlElement(name = "num_discs")
            protected String numDiscs;
            protected String releasedate;
            protected String upc;

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
             * Ruft den Wert der format-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link Shop.Item.Musicspec.Format }
             *     
             */
            public Shop.Item.Musicspec.Format getFormat() {
                return format;
            }

            /**
             * Legt den Wert der format-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link Shop.Item.Musicspec.Format }
             *     
             */
            public void setFormat(Shop.Item.Musicspec.Format value) {
                this.format = value;
            }

            /**
             * Ruft den Wert der numDiscs-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNumDiscs() {
                return numDiscs;
            }

            /**
             * Legt den Wert der numDiscs-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNumDiscs(String value) {
                this.numDiscs = value;
            }

            /**
             * Ruft den Wert der releasedate-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getReleasedate() {
                return releasedate;
            }

            /**
             * Legt den Wert der releasedate-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setReleasedate(String value) {
                this.releasedate = value;
            }

            /**
             * Ruft den Wert der upc-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUpc() {
                return upc;
            }

            /**
             * Legt den Wert der upc-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUpc(String value) {
                this.upc = value;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="value" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Format {

                @XmlAttribute(name = "value")
                protected String value;

                /**
                 * Ruft den Wert der value-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Legt den Wert der value-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <attribute name="mult" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       <attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       <attribute name="currency" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Price {

            @XmlAttribute(name = "mult")
            protected String mult;
            @XmlAttribute(name = "state")
            protected String state;
            @XmlAttribute(name = "currency")
            protected String currency;

            /**
             * Ruft den Wert der mult-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMult() {
                return mult;
            }

            /**
             * Legt den Wert der mult-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMult(String value) {
                this.mult = value;
            }

            /**
             * Ruft den Wert der state-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getState() {
                return state;
            }

            /**
             * Legt den Wert der state-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setState(String value) {
                this.state = value;
            }

            /**
             * Ruft den Wert der currency-Eigenschaft ab.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCurrency() {
                return currency;
            }

            /**
             * Legt den Wert der currency-Eigenschaft fest.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCurrency(String value) {
                this.currency = value;
            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="publisher" maxOccurs="unbounded" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "publisher"
        })
        public static class Publishers {

            protected java.util.List<Shop.Item.Publishers.Publisher> publisher;

            /**
             * Gets the value of the publisher property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the publisher property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getPublisher().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Shop.Item.Publishers.Publisher }
             * </p>
             * 
             * 
             * @return
             *     The value of the publisher property.
             */
            public java.util.List<Shop.Item.Publishers.Publisher> getPublisher() {
                if (publisher == null) {
                    publisher = new ArrayList<>();
                }
                return this.publisher;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Publisher {

                @XmlAttribute(name = "name")
                protected String name;

                /**
                 * Ruft den Wert der name-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Legt den Wert der name-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="sim_product" maxOccurs="unbounded" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <sequence>
         *                   <element name="asin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   <element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 </sequence>
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "simProduct"
        })
        public static class Similars {

            @XmlElement(name = "sim_product")
            protected java.util.List<Shop.Item.Similars.SimProduct> simProduct;

            /**
             * Gets the value of the simProduct property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the simProduct property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getSimProduct().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Shop.Item.Similars.SimProduct }
             * </p>
             * 
             * 
             * @return
             *     The value of the simProduct property.
             */
            public java.util.List<Shop.Item.Similars.SimProduct> getSimProduct() {
                if (simProduct == null) {
                    simProduct = new ArrayList<>();
                }
                return this.simProduct;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <sequence>
             *         <element name="asin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         <element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *       </sequence>
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "asin",
                "title"
            })
            public static class SimProduct {

                protected String asin;
                protected String title;

                /**
                 * Ruft den Wert der asin-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getAsin() {
                    return asin;
                }

                /**
                 * Legt den Wert der asin-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setAsin(String value) {
                    this.asin = value;
                }

                /**
                 * Ruft den Wert der title-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTitle() {
                    return title;
                }

                /**
                 * Legt den Wert der title-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTitle(String value) {
                    this.title = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="studio" maxOccurs="unbounded" minOccurs="0">
         *           <complexType>
         *             <complexContent>
         *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               </restriction>
         *             </complexContent>
         *           </complexType>
         *         </element>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "studio"
        })
        public static class Studios {

            protected java.util.List<Shop.Item.Studios.Studio> studio;

            /**
             * Gets the value of the studio property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the studio property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getStudio().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Shop.Item.Studios.Studio }
             * </p>
             * 
             * 
             * @return
             *     The value of the studio property.
             */
            public java.util.List<Shop.Item.Studios.Studio> getStudio() {
                if (studio == null) {
                    studio = new ArrayList<>();
                }
                return this.studio;
            }


            /**
             * <p>Java-Klasse für anonymous complex type.</p>
             * 
             * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
             * 
             * <pre>{@code
             * <complexType>
             *   <complexContent>
             *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       <attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     </restriction>
             *   </complexContent>
             * </complexType>
             * }</pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Studio {

                @XmlAttribute(name = "name")
                protected String name;

                /**
                 * Ruft den Wert der name-Eigenschaft ab.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Legt den Wert der name-Eigenschaft fest.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

            }

        }


        /**
         * <p>Java-Klasse für anonymous complex type.</p>
         * 
         * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence minOccurs="0">
         *         <element name="title" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "title"
        })
        public static class Tracks {

            protected java.util.List<String> title;

            /**
             * Gets the value of the title property.
             * 
             * <p>This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the title property.</p>
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * </p>
             * <pre>
             * getTitle().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * </p>
             * 
             * 
             * @return
             *     The value of the title property.
             */
            public java.util.List<String> getTitle() {
                if (title == null) {
                    title = new ArrayList<>();
                }
                return this.title;
            }

        }

    }

}
