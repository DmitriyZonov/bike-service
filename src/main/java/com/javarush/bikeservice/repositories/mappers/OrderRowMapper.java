package com.javarush.bikeservice.repositories.mappers;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.entities.bike_service_entities.Bike;
import com.javarush.bikeservice.entities.bike_service_entities.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("order_id"));
        Client client = (Client) rs.getObject("client_id");
        order.setClient(client);
        Bike bike = (Bike) rs.getObject("bike_id");
        order.setBike(bike);
        order.setCost(rs.getInt("cost"));
        LocalDateTime acceptanceDate = (LocalDateTime) rs.getObject("acceptance_date");
        order.setDateOfAcceptance(acceptanceDate);
        LocalDateTime paymentDate = (LocalDateTime) rs.getObject("payment_date");
        order.setPaymentDate(paymentDate);
        order.setInWork(rs.getBoolean("in_work"));

        return order;
    }
}
