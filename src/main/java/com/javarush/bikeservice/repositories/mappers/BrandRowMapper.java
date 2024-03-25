package com.javarush.bikeservice.repositories.mappers;

import com.javarush.bikeservice.entities.bike_service_entities.Brand;
import com.javarush.bikeservice.entities.bike_service_entities.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandRowMapper implements RowMapper<Brand> {
    @Override
    public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
        Brand brand = new Brand();
        brand.setId(rs.getInt("id"));
        brand.setName(rs.getString("name"));
        Country countryOfHeadOffice = (Country) rs.getObject("office_country");
        Country countryOfProduction = (Country) rs.getObject("production_country");
        brand.setCountryOfHeadOffice(countryOfHeadOffice);
        brand.setCountryOfProduction(countryOfProduction);

        return brand;
    }
}
