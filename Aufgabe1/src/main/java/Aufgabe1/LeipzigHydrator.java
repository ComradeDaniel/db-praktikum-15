package Aufgabe1;

import Aufgabe1.models.Studio;
import leipzig.Shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LeipzigHydrator {
    public static HashSet<Studio> hydrateToStudios(
            Shop shop,
            HashSet<Studio> out,
            HashMap<Studio, List<Integer>> studioProductIndex,
            List<String> hydrationErrors
    ) {
        for (int i = 0; i < shop.getItem().size(); i++) {
            Shop.Item product = shop.getItem().get(i);

            if (!product.getPgroup().equals("DVD")) {
                continue;
            }
            Shop.Item.Studios itemStudios = product.getStudios();
            if (itemStudios == null || itemStudios.getStudio() == null || itemStudios.getStudio().isEmpty()) {
                hydrationErrors.add(String.format("No studio specification found for asin: %s", product.getAsin()));
                continue;
            }

            int finalI = i;
            itemStudios.getStudio().forEach(studio -> {
                Studio domainStudio = new Studio(studio.getName());
                studioProductIndex.putIfAbsent(domainStudio, new ArrayList<>());
                studioProductIndex.computeIfPresent(domainStudio, (s, l) -> {
                    l.add(finalI);
                    return l;
                });
                out.add(domainStudio);
            });
        }

        return out;
    }
}
