package Aufgabe1.models;

import java.util.List;

public class Store {
    private int id;
    private String name;
    private String street;
    private String zip;
    private List<Product> items;


    public Store(String name, String street, String zip, List<Product> items) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.items = items;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public void addItem(Product item) {
        this.items.add(item);
    }
}
