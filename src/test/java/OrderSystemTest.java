import org.example.Order;
import org.example.OrderSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderSystemTest {
    private OrderSystem system;

    @BeforeEach
    void setUp() {
        system = new OrderSystem();
    }

    @Test
    void testCreateOrder() {
        int id = system.createOrder("user1", "rest1");
        assertEquals(0, id);
        assertNotNull(system.getOrderById(id));
    }

    @Test
    void testAddItem() {
        int id = system.createOrder("user1", "rest1");
        system.addItem(id, "Пицца", 2, 12.50);

        Order order = system.getOrderById(id);
        assertEquals(1, order.items.size());
        assertEquals("Пицца", order.items.get(0)[0]);
    }

    @Test
    void testAddItemInvalidOrderId() {
        assertThrows(IllegalArgumentException.class,
                () -> system.addItem(-1, "Пицца", 1, 10));
    }

    @Test
    void testUpdateStatus() {
        int id = system.createOrder("user1", "rest1");
        system.updateStatus(id, "Готовится");
        assertEquals("Готовится", system.getOrderById(id).status);
    }

    @Test
    void testGetAllOrders() {
        system.createOrder("user1", "rest1");
        system.createOrder("user2", "rest2");
        assertEquals(2, system.getAllOrders().size());
    }

    @Test
    void testGetOrderByIdNotFound() {
        assertThrows(IllegalArgumentException.class,
                () -> system.getOrderById(999));
    }
}