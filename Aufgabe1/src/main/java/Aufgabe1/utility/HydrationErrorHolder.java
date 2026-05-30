package Aufgabe1.utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HydrationErrorHolder extends HashMap<String, List<String>> {
    public HydrationErrorHolder() {
        super();
    }

    public void add(String asin, String msg) {
        this.putIfAbsent(asin, new ArrayList<>());
        this.computeIfPresent(asin, (k, errs) -> {
            errs.add(msg);
            return errs;
        });
    }

    public void prettyPrintToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Map.Entry<String, List<String>> entry : this.entrySet()) {
                writer.println("ASIN: " + entry.getKey());
                for (String msg : entry.getValue()) {
                    writer.println("  - " + msg);
                }
                writer.println("----------------------------------------");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
        }
    }
}
