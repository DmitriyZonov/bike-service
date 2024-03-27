package com.javarush.bikeservice.repositories;

import com.javarush.bikeservice.entities.bike_service_entities.Brand;
import com.javarush.bikeservice.repositories.mappers.BrandRowMapper;
import com.javarush.bikeservice.repositories.statements.SaveBrandPreparedStatementCallBack;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.javarush.bikeservice.constants.BrandQueries.*;

@Repository
public class BrandRepository implements CrudRepository<Brand>{
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Brand> brandRowMapper = new BrandRowMapper();
    public BrandRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer save(@NotNull Brand brand) {
        return jdbcTemplate.execute(
                SAVE_OR_UPDATE_BRAND,
                new SaveBrandPreparedStatementCallBack(brand));
    }
    @Override
    public int update(@NotNull Brand brand) {
        return jdbcTemplate.update(
                SAVE_OR_UPDATE_BRAND,
                brand.getId(),
                brand.getName(),
                brand.getCountryOfHeadOffice(),
                brand.getCountryOfProduction());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update(
                DELETE_BY_ID,
                id);
    }

    @Override
    public List<Brand> findAll() {
        return jdbcTemplate.query(FIND_ALL_BRANDS, brandRowMapper);
    }

    @Override
    public Brand findById(Integer id) {
        List<Brand> result = jdbcTemplate.query(FIND_BY_ID, brandRowMapper, id);
        return result.get(0);
    }
}
