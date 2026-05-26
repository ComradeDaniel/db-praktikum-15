package Aufgabe1.models;

public class Category {
    public int id;
    public String name;
    public int parentId;

    public Category(String name, int parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}
