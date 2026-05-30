package Aufgabe1.models;

import java.util.Objects;

public class Customer {
    private String username;
    private String deliveryAddress;
    private String account_number;

    public Customer(String username, String deliveryAddress, String account_number) {
        this.username = username;
        this.deliveryAddress = deliveryAddress;
        this.account_number = account_number;
    }

    public Customer(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(username, customer.username) && Objects.equals(deliveryAddress, customer.deliveryAddress) && Objects.equals(account_number, customer.account_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, deliveryAddress, account_number);
    }
}
