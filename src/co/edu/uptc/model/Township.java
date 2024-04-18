package co.edu.uptc.model;

import co.edu.uptc.structures.AVLTree;

import java.util.Comparator;

public class Township {
    private String name;
    private AVLTree<Inhabitant> inhabitants;

    public Township(String name) {
        this.name = name;
        this.inhabitants = new AVLTree<>(Comparator.comparing(Inhabitant::getName));
    }

    public void addInhabitant(Inhabitant inhabitant) {
        this.inhabitants.insert(inhabitant);
    }


}
