package com.javarush.bikeservice.repositories;

import com.javarush.bikeservice.entities.Order;
import com.javarush.bikeservice.repositories.mappers.OrderRowMapper;
import com.javarush.bikeservice.repositories.statements.SaveNewOrderPreparedStatementCallBack;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.javarush.bikeservice.constants.OrderQueries.*;

@Repository
public class OrderRepository implements CrudRepository<Order> {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Order> orderRowMapper = new OrderRowMapper();
    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Integer save(@NotNull Order order) {
        return jdbcTemplate.execute(
                SAVE_NEW_ORDER,
                new SaveNewOrderPreparedStatementCallBack(order));
    }
    @Override
    public int update(@NotNull Order order) {
        return jdbcTemplate.update(
                UPDATE_ORDER,
                order.getId(),
                order.getClient(),
                order.getBike(),
                order.getCost(),
                order.getDateOfAcceptance(),
                order.getPaymentDate(),
                order.getInWork()
        );
    }
    public int close_order(@NotNull Order order) {
        return jdbcTemplate.update(
                CLOSE_ORDER,
                order.getCost(),
                order.getPaymentDate(),
                order.getInWork());
    }
    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update(
                DELETE_BY_ID,
                id
        );
    }
    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query(FIND_ALL_ORDERS, orderRowMapper);
    }
    @Override
    public Order findById(Integer id) {
        List<Order> orders = jdbcTemplate.query(FIND_BY_ID, orderRowMapper, id);
        return orders.get(0);
    }
}
