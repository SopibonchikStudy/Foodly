package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static OrderSystem service = new OrderSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Выход")) {
                break;
            }
            try {
                processCommand(input);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());

            }
        }
    }

    private static void processCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length == 0) return;
        switch (parts[0]) {
            case "Создать":
                if (parts.length < 3) {
                    throw new IllegalArgumentException("Недостаточно параметров");
                }
                createOrder(parts[1], parts[2]);
                break;
            case "Добавить":
                if (parts.length < 5) {
                    throw new IllegalArgumentException("Недостаточно параметров");
                }
                addItem(parts[1], parts[2], parts[3], parts[4]);
                break;
            case "Заказы":
                listOrders();
                break;
            case "Заказ":
                if (parts.length < 2) {
                    throw new IllegalArgumentException("Укажите номер заказа");
                }
                getOrder(parts[1]);
                break;
            case "Обновить":
                if (parts.length < 3) {
                    throw new IllegalArgumentException("Недостаточно параметров");
                }
                updateStatus(parts[1], parts[2]);
                break;
            default:
                System.out.println("Неизвестная команда. Доступные команды:");
                System.out.println("Создать, Добавить, Заказы, Заказ, Обновить, Выход");
        }
    }

    private static void createOrder(String user, String restaurant) {
        int id = service.createOrder(user, restaurant);
        System.out.println("Создан новый заказ #" + id);
    }

    private static void addItem(String orderIdStr, String dishName, String amountStr, String priceStr) {
        try {
            int orderId = Integer.parseInt(orderIdStr);
            int amount = Integer.parseInt(amountStr);
            double price = Double.parseDouble(priceStr);

            service.addItem(orderId, dishName, amount, price);
            System.out.println("Блюдо '" + dishName + "' добавлено в заказ #" + orderId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неправильный формат числа");
        }
    }

    private static void listOrders() {
        List<Order> orders = service.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("Нет активных заказов");
            return;
        }
        System.out.println("Список всех заказов:");
        orders.forEach(System.out::println);
    }

    private static void getOrder(String orderIdStr) {
        try {
            int orderId = Integer.parseInt(orderIdStr);
            Order order = service.getOrderById(orderId);
            System.out.println(order);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Номер заказа должен быть числом");
        }
    }

    private static void updateStatus(String orderIdStr, String status) {
        try {
            int orderId = Integer.parseInt(orderIdStr);
            service.updateStatus(orderId, status);
            System.out.println("Статус заказа #" + orderId + " изменен на '" + status + "'");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Номер заказа должен быть числом");
        }
    }
}