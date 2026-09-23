package models;

import LinkedList.ProjectLinkedList;

public class Shelf { //class thats stores the products
    private String shelfName;
    //List of items on the shelf
    private ProjectLinkedList<Goods> items = new ProjectLinkedList<>();
    //Constructor for the shelf
    public Shelf(String shelfName) {
        this.shelfName = shelfName;
    }
    //getter for the shelfName
    public String getShelfName() {
        return shelfName;
    }

    //gets all items on this shelf
    public ProjectLinkedList<Goods> getItems() {
        return items;
    }

    @Override
    public String toString() { //prints the shelf name
        return shelfName;
    }
}
