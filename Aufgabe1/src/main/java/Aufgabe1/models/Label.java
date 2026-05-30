package Aufgabe1.models;

import java.util.Objects;

public class Label {
    private int id;
    private String name;

    public Label(String name) {
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
        if (!(o instanceof Label label)) return false;
        return Objects.equals(name, label.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
