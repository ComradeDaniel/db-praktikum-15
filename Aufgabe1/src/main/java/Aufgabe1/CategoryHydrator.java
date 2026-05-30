package Aufgabe1;

import java.util.ArrayList;
import java.util.List;

import Aufgabe1.models.Category;
import Aufgabe1.utility.HydrationErrorHolder;
import jakarta.xml.bind.JAXBElement;

// Wandelt die verschachtelte categories.xml in eine flache Category-Liste
// Eigene fortlaufende IDs (Namen sind nicht eindeutig), parentId 0 = Hauptkategorie
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
                // Name = Text VOR dem ersten Kind-Element; danach nur Whitespace
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
