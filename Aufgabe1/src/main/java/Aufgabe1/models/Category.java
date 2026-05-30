package Aufgabe1.models;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private int parentId;
    private List<String> productIDs;

    public Category(String name, int parentId, List<String> productIDs) {
        this.name = name;
        this.parentId = parentId;
        this.productIDs = productIDs;
    }
}
