package Aufgabe1;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.helpers.DefaultValidationEventHandler;
import leipzig.Shop;

public class Main {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(leipzig.ObjectFactory.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            // throwen wenn das Schema nicht passt
            unmarshaller.setEventHandler(new DefaultValidationEventHandler());
            Shop shop = (Shop) unmarshaller.unmarshal(new File("data/data/leipzig_transformed.xml"));

            System.out.printf("number of items: %d\n", shop.getItem().size());
            System.out.print("success - no errors");
        } catch (JAXBException e) {
            System.out.print(e.getMessage());
            System.exit(1);
        }

    }
}
