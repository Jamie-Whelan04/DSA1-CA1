package models;

import LinkedList.ProjectLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupermarketTest {

    private Supermarket supermarket;
    private FloorArea floor1;
    private FloorArea floor2;

    @BeforeEach
    void setUp() {
        supermarket = new Supermarket();

        floor1 = new FloorArea("Baking & Bread", "Ground");
        floor2 = new FloorArea("Dairy", "First");


        Aisle aisle1 = new Aisle("Bread");
        Aisle aisle2 = new Aisle("Pastries");
        floor1.getAisles().add(aisle1);
        floor1.getAisles().add(aisle2);

        Aisle aisle3 = new Aisle("Cheese");
        floor2.getAisles().add(aisle3);
    }

    @Test
    void addFloorArea() {
        supermarket.addFloorArea(floor1);
        supermarket.addFloorArea(floor2);

        assertEquals(2, supermarket.getFloorAreas().size());
        assertTrue(supermarket.getFloorAreas().get(0) == floor1);
        assertTrue(supermarket.getFloorAreas().get(1) == floor2);
    }

    @Test
    void getFloorAreas() {
        supermarket.addFloorArea(floor1);
        supermarket.addFloorArea(floor2);

        ProjectLinkedList<FloorArea> floors = supermarket.getFloorAreas();
        assertEquals(2, floors.size());
        assertEquals("Baking & Bread", floors.get(0).getFloorName());
        assertEquals("Dairy", floors.get(1).getFloorName());
    }

    @Test
    void aisleExists() {
        supermarket.addFloorArea(floor1);
        supermarket.addFloorArea(floor2);


        assertTrue(supermarket.aisleExists("Bread"));
        assertTrue(supermarket.aisleExists("Cheese"));


        assertTrue(supermarket.aisleExists("bread"));
        assertTrue(supermarket.aisleExists("CHEESE"));


        assertFalse(supermarket.aisleExists("Meat"));
    }
}
