package Aufgabe1.models;

import java.util.Objects;

public class Studio {
    private int id;
    private String name;

    public Studio(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Studio studio)) return false;
        return Objects.equals(name, studio.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
