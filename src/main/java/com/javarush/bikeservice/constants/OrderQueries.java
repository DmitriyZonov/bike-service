package com.javarush.bikeservice.constants;

public class OrderQueries {
    public static final String SAVE_NEW_ORDER = """
            INSERT INTO orders(order_id, client_id, bike_id, acceptance_date, in_work)
            VALUES(?, ?, ?, ?, ?)
            """;
    public static final String UPDATE_ORDER = """
            INSERT INTO orders(order_id, client_id, bike_id, cost, acceptance_date, payment_date, in_work)
            VALUES(?, ?, ?, ?, ?, ?, ?)
            """;
    public static final String CLOSE_ORDER = """
            INSERT INTO orders(cost, payment_date, in_work)
            VALUES(?, ?, ?)
            """;
    public static final String DELETE_BY_ID = """
            DELETE FROM orders AS o
            WHERE o.order_id = ?
            """;
    public static final String FIND_ALL_ORDERS = """
            SELECT *
            FROM orders
            """;
    public static final String FIND_BY_ID = """
            SELECT *
            FROM orders AS o
            WHERE o.order_id = ?
            """;
}
