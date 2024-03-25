package com.javarush.bikeservice.repositories.statements;

import com.javarush.bikeservice.entities.bike_service_entities.Brand;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveBrandPreparedStatementCallBack implements PreparedStatementCallback<Integer> {
    private final Brand brand;

    public SaveBrandPreparedStatementCallBack(Brand brand) {
        this.brand = brand;
    }

    @Override
    public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
        ps.setInt(1, brand.getId());
        ps.setString(2, brand.getName());
        ps.setObject(3, brand.getCountryOfHeadOffice());
        ps.setObject(4, brand.getCountryOfProduction());

        ps.execute();
        return ps.getUpdateCount();
    }
}
