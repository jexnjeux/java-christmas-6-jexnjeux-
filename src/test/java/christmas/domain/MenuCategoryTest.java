package christmas.domain;

import static org.junit.jupiter.api.Assertions.*;

import christmas.type.MenuType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuCategoryTest {

    private MenuCategory menuCategory;

    @BeforeEach
    void setUp() {
        menuCategory = new MenuCategory(MenuType.APPETIZER);
        menuCategory.addMenuItem(new MenuItem("양송이수프", 6000));
        menuCategory.addMenuItem(new MenuItem("타파스", 5500));
    }

    @Test
    void testAddMenuItem() {
        menuCategory.addMenuItem(new MenuItem("시저샐러드", 8000));
        assertEquals(3, menuCategory.getItems().size());
    }

    @Test
    void testContainsMenuItem() {
        assertTrue(menuCategory.containsMenuItem("양송이수프"));
        assertFalse(menuCategory.containsMenuItem("샐러드"));
    }

    @Test
    void testFindMenuType() {
        assertEquals(MenuType.APPETIZER, menuCategory.findMenuType("양송이수프"));
        assertNull(menuCategory.findMenuType("샐러드"));
    }

    @Test
    void testGetType() {
        assertEquals(MenuType.APPETIZER, menuCategory.getType());
    }
}