package Aufgabe1.models;

public class Customer {
    public int id;
    public String username;
    public String deliveryAddress;
    public String account_number;

    public Customer(String username, String deliveryAddress, String account_number) {
        this.username = username;
        this.deliveryAddress = deliveryAddress;
        this.account_number = account_number;
    }
}
