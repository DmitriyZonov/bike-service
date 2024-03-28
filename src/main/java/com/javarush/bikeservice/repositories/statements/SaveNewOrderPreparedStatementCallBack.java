package com.javarush.bikeservice.repositories.statements;

import com.javarush.bikeservice.entities.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveNewOrderPreparedStatementCallBack implements PreparedStatementCallback<Integer> {

    private final Order order;

    public SaveNewOrderPreparedStatementCallBack(Order order) {
        this.order = order;
    }
    @Override
    public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

        ps.setObject(2, order.getClient());
        ps.setObject(3, order.getBike());

        ps.execute();
        return ps.getUpdateCount();
    }
}
