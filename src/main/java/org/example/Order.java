package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public int id;
    public String userId;
    public String restaurantId;
    public List<String[]> items = new ArrayList<>();
    public String status = "Создан";

    public Order(int id, String userId, String restaurantId) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Заказ №").append(id)
                .append(" от ").append(userId)
                .append(" в ").append(restaurantId)
                .append("\nБлюда:\n");

        for (String[] item : items) {
            result.append("- ").append(item[0])
                    .append(" (").append(item[1])
                    .append(" шт.) - ").append(item[2]).append("₽\n");
        }

        result.append("Статус: ").append(status);
        return result.toString();
    }
}