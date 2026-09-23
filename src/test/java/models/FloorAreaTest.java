package models;

import LinkedList.ProjectLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorAreaTest {

    private FloorArea floorArea;
    private Aisle aisle1;
    private Aisle aisle2;

    @BeforeEach
    void setUp() {
        floorArea = new FloorArea("Baking & Bread", "Ground");

        aisle1 = new Aisle("Bread");
        aisle2 = new Aisle("Pastries");

        floorArea.getAisles().add(aisle1);
        floorArea.getAisles().add(aisle2);
    }

    @Test
    void getFloorName() {
        assertEquals("Baking & Bread", floorArea.getFloorName());
    }

    @Test
    void getAisles() {
        ProjectLinkedList<Aisle> aisles = floorArea.getAisles();
        assertEquals(2, aisles.size());
        assertEquals(aisle1, aisles.get(0));
        assertEquals(aisle2, aisles.get(1));

        // Add a new aisle and check
        Aisle aisle3 = new Aisle("Cakes");
        aisles.add(aisle3);
        assertEquals(3, aisles.size());
        assertEquals(aisle3, aisles.get(2));
    }

    @Test
    void testToString() {
        String expected = "FloorArea [floorName=Baking & Bread, floorLevel=Ground]";
        assertEquals(expected, floorArea.toString());

        FloorArea floor2 = new FloorArea("Dairy", "First");
        String expected2 = "FloorArea [floorName=Dairy, floorLevel=First]";
        assertEquals(expected2, floor2.toString());
    }
}
