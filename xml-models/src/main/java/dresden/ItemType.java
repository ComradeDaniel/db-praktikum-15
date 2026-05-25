//
// Diese Datei wurde mit der Eclipse Implementation of JAXB, v4.0.5 generiert 
// Siehe https://eclipse-ee4j.github.io/jaxb-ri 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
//


package dresden;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ItemType complex type.</p>
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
 * 
 * <pre>{@code
 * <complexType name="ItemType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="price" type="{}PriceType"/>
 *         <element name="ean" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="details" type="{}DetailsType"/>
 *         <element name="listmania" type="{}ListmaniaType"/>
 *         <element name="actors" type="{}ActorsType"/>
 *         <element name="artists" type="{}ArtistsType"/>
 *         <element name="authors" type="{}AuthorsType"/>
 *         <element name="creators" type="{}CreatorsType"/>
 *         <element name="directors" type="{}DirectorsType"/>
 *         <element name="labels" type="{}LabelsType"/>
 *         <element name="publishers" type="{}PublishersType"/>
 *         <element name="studios" type="{}StudiosType"/>
 *         <element name="similars" type="{}SimilarsType"/>
 *         <element name="tracks" type="{}TracksType"/>
 *         <element name="audiotext" type="{}AudiotextType"/>
 *         <element name="bookspec" type="{}BookspecType"/>
 *         <element name="dvdspec" type="{}DvdspecType"/>
 *         <element name="musicspec" type="{}MusicspecType"/>
 *       </sequence>
 *       <attribute name="pgroup" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="asin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="salesrank" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemType", propOrder = {
    "title",
    "price",
    "ean",
    "details",
    "listmania",
    "actors",
    "artists",
    "authors",
    "creators",
    "directors",
    "labels",
    "publishers",
    "studios",
    "similars",
    "tracks",
    "audiotext",
    "bookspec",
    "dvdspec",
    "musicspec"
})
public class ItemType {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected PriceType price;
    protected String ean;
    @XmlElement(required = true)
    protected DetailsType details;
    @XmlElement(required = true)
    protected ListmaniaType listmania;
    @XmlElement(required = true)
    protected ActorsType actors;
    @XmlElement(required = true)
    protected ArtistsType artists;
    @XmlElement(required = true)
    protected AuthorsType authors;
    @XmlElement(required = true)
    protected CreatorsType creators;
    @XmlElement(required = true)
    protected DirectorsType directors;
    @XmlElement(required = true)
    protected LabelsType labels;
    @XmlElement(required = true)
    protected PublishersType publishers;
    @XmlElement(required = true)
    protected StudiosType studios;
    @XmlElement(required = true)
    protected SimilarsType similars;
    @XmlElement(required = true)
    protected TracksType tracks;
    @XmlElement(required = true)
    protected AudiotextType audiotext;
    @XmlElement(required = true)
    protected BookspecType bookspec;
    @XmlElement(required = true)
    protected DvdspecType dvdspec;
    @XmlElement(required = true)
    protected MusicspecType musicspec;
    @XmlAttribute(name = "pgroup", required = true)
    protected String pgroup;
    @XmlAttribute(name = "asin", required = true)
    protected String asin;
    @XmlAttribute(name = "salesrank")
    protected String salesrank;

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
     * Ruft den Wert der price-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PriceType }
     *     
     */
    public PriceType getPrice() {
        return price;
    }

    /**
     * Legt den Wert der price-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceType }
     *     
     */
    public void setPrice(PriceType value) {
        this.price = value;
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
     * Ruft den Wert der details-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DetailsType }
     *     
     */
    public DetailsType getDetails() {
        return details;
    }

    /**
     * Legt den Wert der details-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DetailsType }
     *     
     */
    public void setDetails(DetailsType value) {
        this.details = value;
    }

    /**
     * Ruft den Wert der listmania-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ListmaniaType }
     *     
     */
    public ListmaniaType getListmania() {
        return listmania;
    }

    /**
     * Legt den Wert der listmania-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ListmaniaType }
     *     
     */
    public void setListmania(ListmaniaType value) {
        this.listmania = value;
    }

    /**
     * Ruft den Wert der actors-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ActorsType }
     *     
     */
    public ActorsType getActors() {
        return actors;
    }

    /**
     * Legt den Wert der actors-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ActorsType }
     *     
     */
    public void setActors(ActorsType value) {
        this.actors = value;
    }

    /**
     * Ruft den Wert der artists-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link ArtistsType }
     *     
     */
    public ArtistsType getArtists() {
        return artists;
    }

    /**
     * Legt den Wert der artists-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link ArtistsType }
     *     
     */
    public void setArtists(ArtistsType value) {
        this.artists = value;
    }

    /**
     * Ruft den Wert der authors-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AuthorsType }
     *     
     */
    public AuthorsType getAuthors() {
        return authors;
    }

    /**
     * Legt den Wert der authors-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorsType }
     *     
     */
    public void setAuthors(AuthorsType value) {
        this.authors = value;
    }

    /**
     * Ruft den Wert der creators-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link CreatorsType }
     *     
     */
    public CreatorsType getCreators() {
        return creators;
    }

    /**
     * Legt den Wert der creators-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatorsType }
     *     
     */
    public void setCreators(CreatorsType value) {
        this.creators = value;
    }

    /**
     * Ruft den Wert der directors-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DirectorsType }
     *     
     */
    public DirectorsType getDirectors() {
        return directors;
    }

    /**
     * Legt den Wert der directors-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectorsType }
     *     
     */
    public void setDirectors(DirectorsType value) {
        this.directors = value;
    }

    /**
     * Ruft den Wert der labels-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link LabelsType }
     *     
     */
    public LabelsType getLabels() {
        return labels;
    }

    /**
     * Legt den Wert der labels-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelsType }
     *     
     */
    public void setLabels(LabelsType value) {
        this.labels = value;
    }

    /**
     * Ruft den Wert der publishers-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PublishersType }
     *     
     */
    public PublishersType getPublishers() {
        return publishers;
    }

    /**
     * Legt den Wert der publishers-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PublishersType }
     *     
     */
    public void setPublishers(PublishersType value) {
        this.publishers = value;
    }

    /**
     * Ruft den Wert der studios-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link StudiosType }
     *     
     */
    public StudiosType getStudios() {
        return studios;
    }

    /**
     * Legt den Wert der studios-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link StudiosType }
     *     
     */
    public void setStudios(StudiosType value) {
        this.studios = value;
    }

    /**
     * Ruft den Wert der similars-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link SimilarsType }
     *     
     */
    public SimilarsType getSimilars() {
        return similars;
    }

    /**
     * Legt den Wert der similars-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link SimilarsType }
     *     
     */
    public void setSimilars(SimilarsType value) {
        this.similars = value;
    }

    /**
     * Ruft den Wert der tracks-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TracksType }
     *     
     */
    public TracksType getTracks() {
        return tracks;
    }

    /**
     * Legt den Wert der tracks-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TracksType }
     *     
     */
    public void setTracks(TracksType value) {
        this.tracks = value;
    }

    /**
     * Ruft den Wert der audiotext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AudiotextType }
     *     
     */
    public AudiotextType getAudiotext() {
        return audiotext;
    }

    /**
     * Legt den Wert der audiotext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AudiotextType }
     *     
     */
    public void setAudiotext(AudiotextType value) {
        this.audiotext = value;
    }

    /**
     * Ruft den Wert der bookspec-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link BookspecType }
     *     
     */
    public BookspecType getBookspec() {
        return bookspec;
    }

    /**
     * Legt den Wert der bookspec-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link BookspecType }
     *     
     */
    public void setBookspec(BookspecType value) {
        this.bookspec = value;
    }

    /**
     * Ruft den Wert der dvdspec-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link DvdspecType }
     *     
     */
    public DvdspecType getDvdspec() {
        return dvdspec;
    }

    /**
     * Legt den Wert der dvdspec-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link DvdspecType }
     *     
     */
    public void setDvdspec(DvdspecType value) {
        this.dvdspec = value;
    }

    /**
     * Ruft den Wert der musicspec-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MusicspecType }
     *     
     */
    public MusicspecType getMusicspec() {
        return musicspec;
    }

    /**
     * Legt den Wert der musicspec-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MusicspecType }
     *     
     */
    public void setMusicspec(MusicspecType value) {
        this.musicspec = value;
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

}
