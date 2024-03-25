package com.javarush.bikeservice.repositories.statements;

import com.javarush.bikeservice.entities.bike_service_entities.Staff;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveStaffPreparedStatementCallBack implements PreparedStatementCallback<Integer> {
    private final Staff s;

    public SaveStaffPreparedStatementCallBack(Staff s) {
        this.s = s;
    }

    @Override
    public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
        ps.setInt(1, s.getId());
        ps.setString(2, s.getPassport());
        ps.setString(3, s.getName());
        ps.setString(4, s.getOccupation());
        ps.setString(5, s.getPhoneNumber());
        ps.setObject(6, s.getCity());
        ps.setString(7, s.getAddress());
        ps.setBoolean(8, s.getIsHired());

        ps.execute();
        return ps.getUpdateCount();
    }
}
