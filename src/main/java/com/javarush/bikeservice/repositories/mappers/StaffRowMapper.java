package com.javarush.bikeservice.repositories.mappers;

import com.javarush.bikeservice.entities.bike_service_entities.City;
import com.javarush.bikeservice.entities.bike_service_entities.Staff;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StaffRowMapper implements RowMapper<Staff> {
    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        Staff staff = new Staff();
        staff.setId(rs.getInt("id"));
        staff.setPassport(rs.getString("passport"));
        staff.setName(rs.getString("name"));
        staff.setOccupation(rs.getString("occupation"));
        staff.setPhoneNumber("phone_number");
        City city = (City) rs.getObject("city");
        staff.setCity(city);
        staff.setAddress(rs.getString("address"));
        staff.setIsHired(rs.getBoolean("is_hired"));

        return staff;
    }
}
