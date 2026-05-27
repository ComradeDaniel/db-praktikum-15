package Aufgabe1.models;

public class Category {
    private int id;
    private String name;
    private int parentId;

    public Category(String name, int parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}
