package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {

    private Shelf shelf;
    private Goods goods1;
    private Goods goods2;

    @BeforeEach
    void setUp() {
        shelf = new Shelf("Shelf A");

        goods1 = new Goods("Milk", 10, 1.5, "Cold", 1.0, "url1");
        goods2 = new Goods("Bread", 5, 2.0, "Room", 0.5, "url2");

        shelf.getItems().add(goods1);
        shelf.getItems().add(goods2);
    }

    @Test
    void getShelfName() {
        assertEquals("Shelf A", shelf.getShelfName());
    }

    @Test
    void getItems() {

        assertEquals(2, shelf.getItems().size());
        assertEquals(goods1, shelf.getItems().get(0));
        assertEquals(goods2, shelf.getItems().get(1));


        Goods goods3 = new Goods("Cheese", 3, 3.0, "Cold", 0.5, "url3");
        shelf.getItems().add(goods3);
        assertEquals(3, shelf.getItems().size());
        assertEquals(goods3, shelf.getItems().get(2));
    }

    @Test
    void testToString() {
        assertEquals("Shelf A", shelf.toString());

        Shelf shelf2 = new Shelf("Shelf B");
        assertEquals("Shelf B", shelf2.toString());
    }
}
