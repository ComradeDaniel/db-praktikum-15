package Aufgabe1.models;

import java.util.List;

public class Category {
    private int id;
    private String name;
    private int parentId;          // 0 = keine Oberkategorie (Hauptkategorie); beim Insert -> NULL
    private List<String> productIDs;

    public Category(String name, int parentId, List<String> productIDs) {
        this.name = name;
        this.parentId = parentId;
        this.productIDs = productIDs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<String> getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(List<String> productIDs) {
        this.productIDs = productIDs;
    }
}
