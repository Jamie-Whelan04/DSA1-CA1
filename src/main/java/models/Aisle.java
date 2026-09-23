package models;

import LinkedList.ProjectLinkedList;

public class Aisle {

    private String aisleName;
    private ProjectLinkedList<Shelf> shelves =  new ProjectLinkedList<>();

    public Aisle(String aisleName) {
        this.aisleName = aisleName;
    }

    public String getAisleName() {
        return aisleName;
    }

    public ProjectLinkedList<Shelf> getShelves() {
        return shelves;
    }

    @Override
    public String toString() {
        return aisleName;
    }
}
