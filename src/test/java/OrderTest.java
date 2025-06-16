import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.*;
class OrderTest {

    @Test
    void testOrderCreation() {
        Order order = new Order(1, "user1", "rest1");
        assertEquals(1, order.id);
        assertEquals("user1", order.userId);
        assertEquals("rest1", order.restaurantId);
        assertEquals("Создан", order.status);
        assertTrue(order.items.isEmpty());
    }

    @Test
    void testToString() {
        Order order = new Order(1, "user1", "rest1");
        order.items.add(new String[]{"Пицца", "2", "12.50"});

        String expected = "Заказ №1 от user1 в rest1\n" +
                "Блюда:\n" +
                "- Пицца (2 шт.) - 12.50₽\n" +
                "Статус: Создан";
        assertEquals(expected, order.toString());
    }
}