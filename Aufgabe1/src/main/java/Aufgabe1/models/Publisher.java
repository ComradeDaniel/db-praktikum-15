package Aufgabe1.models;

import java.util.Objects;

public class Publisher {
    private int id;
    private String name;

    public Publisher(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Publisher publisher)) return false;
        return id == publisher.id && Objects.equals(name, publisher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
