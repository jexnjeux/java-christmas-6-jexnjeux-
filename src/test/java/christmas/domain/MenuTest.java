package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        MenuCategory appetizers = new MenuCategory(MenuType.APPETIZER);
        appetizers.addMenuItem(new MenuItem("양송이수프", 6000));
        appetizers.addMenuItem(new MenuItem("타파스", 5500));
        menu.addMenu(appetizers);

        MenuCategory mains = new MenuCategory(MenuType.MAIN);
        mains.addMenuItem(new MenuItem("티본스테이크", 55000));
        mains.addMenuItem(new MenuItem("바비큐립", 54000));
        menu.addMenu(mains);
    }

    @Test
    void testContainsItem() {
        assertTrue(menu.containsItem("양송이수프"));
        assertTrue(menu.containsItem("바비큐립"));
        assertFalse(menu.containsItem("샐러드"));
    }

    @Test
    void testFindMenuType() {
        assertEquals(MenuType.APPETIZER, menu.findMenuType("양송이수프"));
        assertEquals(MenuType.MAIN, menu.findMenuType("바비큐립"));
        assertNull(menu.findMenuType("샐러드"));
    }
}