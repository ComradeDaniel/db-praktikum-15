package Aufgabe1;

import java.util.ArrayList;
import java.util.List;

import Aufgabe1.models.Category;
import Aufgabe1.utility.HydrationErrorHolder;
import jakarta.xml.bind.JAXBElement;

/**
 * Wandelt die verschachtelte categories.xml in eine flache Liste von Category-Domainobjekten.
 *
 * Die JAXB-Struktur ist "mixed content": jede categories.Category enthält in content eine
 * gemischte Liste aus
 *   - categories.Category  (Unterkategorien),
 *   - JAXBElement<String>  (die <item>-Elemente mit der ASIN als Wert) und
 *   - String               (Kategoriename + Whitespace zwischen den Elementen).
 *
 * Da Kategorienamen NICHT eindeutig sind (z.B. "Country" unter mehreren Oberkategorien,
 * siehe Offene-Fragen.md), kann der Name kein Schlüssel sein. Wir vergeben deshalb beim
 * Tree-Walk fortlaufende eigene IDs (ab 1) und setzen darüber die parent-Beziehung.
 * parentId == 0 markiert eine Hauptkategorie (ohne Oberkategorie) -> beim Insert NULL.
 *
 * Der Name einer Kategorie ist der zusammengefügte Text VOR dem ersten Kind-Element
 * (robust gegen mehrere String-Fragmente, falls der Parser Entities aufsplittet).
 *
 * Hinweis: Der Parametertyp ist categories.Categories, voll qualifiziert, damit der
 * Paketname "categories" nicht mit einem gleichnamigen Bezeichner kollidiert.
 */
public class CategoryHydrator {

    public static List<Category> hydrateToCategories(
            categories.Categories categoriesXml,
            List<Category> out,
            HydrationErrorHolder hydrationErrors
    ) {
        int[] nextId = {0};
        for (categories.Category root : categoriesXml.getCategory()) {
            hydrateNode(root, 0, nextId, out, hydrationErrors);
        }
        return out;
    }

    private static void hydrateNode(
            categories.Category node,
            int parentId,
            int[] nextId,
            List<Category> out,
            HydrationErrorHolder hydrationErrors
    ) {
        StringBuilder nameBuf = new StringBuilder();
        boolean firstChildSeen = false;
        List<String> productIDs = new ArrayList<>();
        List<categories.Category> children = new ArrayList<>();

        for (Object o : node.getContent()) {
            if (o instanceof categories.Category childCat) {
                firstChildSeen = true;
                children.add(childCat);
            } else if (o instanceof JAXBElement<?> item) {
                firstChildSeen = true;
                Object value = item.getValue();
                if (value != null) {
                    String asin = value.toString().trim();
                    if (!asin.isBlank()) {
                        productIDs.add(asin);
                    }
                }
            } else if (o instanceof String text) {
                // Nur Text vor dem ersten Kind-Element gehört zum Namen,
                // alles danach ist Whitespace zwischen den Elementen.
                if (!firstChildSeen) {
                    nameBuf.append(text);
                }
            }
        }

        String name = nameBuf.toString().trim();

        int id = ++nextId[0];
        Category domainCategory = new Category(name, parentId, productIDs);
        domainCategory.setId(id);
        out.add(domainCategory);

        if (name.isBlank()) {
            hydrationErrors.add("Category#" + id, "Leerer Kategoriename (parentId=" + parentId + ")");
        }

        for (categories.Category child : children) {
            hydrateNode(child, id, nextId, out, hydrationErrors);
        }
    }
}
