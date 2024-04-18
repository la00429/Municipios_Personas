package co.edu.uptc.model;

public class Inhabitant {
    private String name;
    private String id;

    public Inhabitant(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
