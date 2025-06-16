package org.example;

import java.util.*;

public class OrderSystem {
    private Map<Integer, Order> orders = new HashMap<>();
    private int nextOrderId = 0;

    public int createOrder(String userId, String restaurantId) {
        int id = nextOrderId++;
        orders.put(id, new Order(id, userId, restaurantId));
        return id;
    }

    public void addItem(int orderId, String dishName, int amount, double price) {
        // Проверка orderId
        if (orderId < 0) {
            throw new IllegalArgumentException("Номер заказа задан в некорректном виде");
        }

        // Проверка dishName
        if (dishName == null || dishName.isBlank()) {
            throw new IllegalArgumentException("Одно из названий блюд задано в некорректном виде");
        }

        // Проверка amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Количество блюда задано в некорректном виде");
        }

        // Проверка price
        if (price <= 0) {
            throw new IllegalArgumentException("Цена задана в некорректном виде");
        }
        Order order = orders.get(orderId);
        if (order != null) {
            order.items.add(new String[]{dishName, String.valueOf(amount), String.valueOf(price)});
        }
    }

    public void updateStatus(int orderId, String status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.status = status;
        }
    }

    public Order getOrderById(int orderId) {
        if (orderId < 0) {
            throw new IllegalArgumentException("ID заказа не может быть пустым");
        }
        try{
            orders.get(orderId);
        } catch (Exception e){
            throw new IllegalArgumentException("Заказ не найден");
        }

        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Заказ с ID " + orderId + " не найден");
        }

        return order;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
}