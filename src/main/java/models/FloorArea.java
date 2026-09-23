package models;

import LinkedList.ProjectLinkedList;

public class FloorArea {

    private String floorName;
    private String floorLevel;
    private ProjectLinkedList<Aisle> aisles =  new ProjectLinkedList<>();

    public FloorArea(String floorName, String floorLevel) {
        this.floorName = floorName;
        this.floorLevel = floorLevel;
    }

    public String getFloorName() {
        return floorName;
    }

    public ProjectLinkedList<Aisle> getAisles() {
        return aisles;
    }

    @Override
    public String toString() {
        return "FloorArea [floorName=" + floorName + ", floorLevel=" + floorLevel + "]";
    }
}
