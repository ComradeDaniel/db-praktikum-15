package Aufgabe1.models;

public class Customer {
    private int id;
    private String username;
    private String deliveryAddress;
    private String account_number;

    public Customer(String username, String deliveryAddress, String account_number) {
        this.username = username;
        this.deliveryAddress = deliveryAddress;
        this.account_number = account_number;
    }
}
