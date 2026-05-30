package Aufgabe1;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

import Aufgabe1.models.DVD;
import Aufgabe1.models.Label;
import Aufgabe1.models.ListmaniaList;
import Aufgabe1.models.Person;
import Aufgabe1.models.Product;
import Aufgabe1.models.Publisher;
import Aufgabe1.models.Studio;
import Aufgabe1.utility.HydrationErrorHolder;
import categories.Categories;
import dresden.ShopType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.helpers.DefaultValidationEventHandler;
import leipzig.Shop;

public class Main {
    public static void main(String[] args) {
        try {
            /*
             * ===================================================
             * Unmarhalling XML and CSV
             * ===================================================
            */

            JAXBContext contextLeipzig = JAXBContext.newInstance(leipzig.ObjectFactory.class);
            Unmarshaller unmarshallerLeipzig = contextLeipzig.createUnmarshaller();
            // DefaultValidationEventHandler gibt einen Fehler bei groben Schemaverstößen aus
            unmarshallerLeipzig.setEventHandler(new DefaultValidationEventHandler());
            Shop shopLeipzig = (Shop) unmarshallerLeipzig.unmarshal(new File("data/data/leipzig_transformed.xml"));

            JAXBContext contextDresden = JAXBContext.newInstance(dresden.ObjectFactory.class);
            Unmarshaller unmarshallerDresden = contextDresden.createUnmarshaller();
            unmarshallerDresden.setEventHandler(new DefaultValidationEventHandler());
            JAXBElement<ShopType> shopDresdenElement = (JAXBElement<ShopType>) unmarshallerDresden.unmarshal(new File("data/data/dresden.xml"));
            ShopType shopDresden = shopDresdenElement.getValue();

            JAXBContext contextCategories = JAXBContext.newInstance(categories.ObjectFactory.class);
            Unmarshaller unmarshallerCategories = contextCategories.createUnmarshaller();
            unmarshallerCategories.setEventHandler(new DefaultValidationEventHandler());
            Categories categories = (Categories) unmarshallerCategories.unmarshal(new File("data/data/categories.xml"));

            /*
             * ===================================================
             * Hydration to model entities
             * ===================================================
            */

            HashSet<Studio> studios = new HashSet<>();
            HashSet<Publisher> publishers = new HashSet<>();
            HashSet<Person> persons = new HashSet<>();
            HashSet<Label> labels = new HashSet<>();
            HashSet<ListmaniaList> listmaniaLists = new HashSet<>();
            HashSet<DVD.DVDLanguage> dvdLanguages = new HashSet<>();
            HashMap<String, Product> products = new HashMap<>();

            HashMap<Studio, HashSet<String>> studioProductIndex = new HashMap<>();
            HashMap<Publisher, HashSet<String>> publisherProductIndex = new HashMap<>();
            HashMap<Person, HashSet<String>> personProductIndex = new HashMap<>();
            HashMap<Label, HashSet<String>> labelProductIndex = new HashMap<>();
            HashMap<ListmaniaList, HashSet<String>> listmaniaProductIndex = new HashMap<>();
            HashMap<DVD.DVDLanguage, HashSet<String>> dvdlanguageProductIndex = new HashMap<>();

            HydrationErrorHolder hydrationErrors = new HydrationErrorHolder();

            studios = LeipzigHydrator.hydrateToStudios(shopLeipzig, studios, studioProductIndex, hydrationErrors);
            publishers = LeipzigHydrator.hydrateToPublishers(shopLeipzig, publishers, publisherProductIndex, hydrationErrors);
            persons = LeipzigHydrator.hydrateToPersons(shopLeipzig, persons, personProductIndex, hydrationErrors);
            labels = LeipzigHydrator.hydrateToLabels(shopLeipzig, labels, labelProductIndex, hydrationErrors);
            listmaniaLists = LeipzigHydrator.hydrateToListmania(shopLeipzig, listmaniaLists, listmaniaProductIndex, hydrationErrors);
            dvdLanguages = LeipzigHydrator.hydrateToDVDLanguages(shopLeipzig, dvdLanguages, dvdlanguageProductIndex, hydrationErrors);
            products = LeipzigHydrator.hydrateToProducts(shopLeipzig, products, hydrationErrors);

            System.out.print(String.format("Hydrated:\nStudios: %d\nPublishers: %d\nPersons: %d\nLabels: %d\nListmania: %d\nDVDLanguage: %d\nProducts: %d\n",
            studios.size(),
            publishers.size(),
            persons.size(),
            labels.size(),
            listmaniaLists.size(),
            dvdLanguages.size(),
            products.size()
             ));

            hydrationErrors.prettyPrintToFile("test.txt");
            
        } catch (JAXBException e) {
            System.out.print(e.getMessage());
            System.exit(1);
        }

    }
}
