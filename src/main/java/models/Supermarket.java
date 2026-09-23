package models;

import LinkedList.ProjectLinkedList;

public class Supermarket {
    //custom linked list to used to store the floorArea objects.
    private ProjectLinkedList<FloorArea> floorAreas = new ProjectLinkedList<>();
    //Adds a floor area to the supermarket
    public void addFloorArea(FloorArea floorArea){

        floorAreas.add(floorArea);
    }

    //returns the list of floor areas
    public ProjectLinkedList<FloorArea> getFloorAreas() {
        return floorAreas;
    }
    //method to check if an aisle already exists in the supermarket and searches all floor areas for their aisles.
    public boolean aisleExists(String aisleName){
        for (int i = 0; i < floorAreas.size(); i++) {
            FloorArea floorArea = floorAreas.get(i);

            ProjectLinkedList<Aisle> aisles = floorArea.getAisles();
            for (int j = 0; j < aisles.size(); j++) {
                Aisle aisle = aisles.get(j);
                if(aisle.getAisleName().equalsIgnoreCase(aisleName)){
                    return true;
                }
            }
        }
        return false;
    }
}
