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
 * <p>Java-Klasse für DvdspecType complex type.</p>
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
 * 
 * <pre>{@code
 * <complexType name="DvdspecType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="aspectratio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="regioncode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="releasedate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="runningtime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="theatr_release" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="upc" type="{}ValAttributeType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DvdspecType", propOrder = {
    "aspectratio",
    "format",
    "regioncode",
    "releasedate",
    "runningtime",
    "theatrRelease",
    "upc"
})
public class DvdspecType {

    protected String aspectratio;
    protected String format;
    protected String regioncode;
    protected String releasedate;
    protected String runningtime;
    @XmlElement(name = "theatr_release")
    protected String theatrRelease;
    protected ValAttributeType upc;

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
     * Ruft den Wert der upc-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ValAttributeType }
     *     
     */
    public ValAttributeType getUpc() {
        return upc;
    }

    /**
     * Legt den Wert der upc-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ValAttributeType }
     *     
     */
    public void setUpc(ValAttributeType value) {
        this.upc = value;
    }

}
