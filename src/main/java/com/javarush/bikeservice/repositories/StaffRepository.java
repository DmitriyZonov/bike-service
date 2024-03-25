package com.javarush.bikeservice.repositories;

import com.javarush.bikeservice.entities.bike_service_entities.Staff;
import com.javarush.bikeservice.repositories.mappers.StaffRowMapper;
import com.javarush.bikeservice.repositories.statements.SaveStaffPreparedStatementCallBack;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.javarush.bikeservice.constants.StaffQueries.*;

@Repository
public class StaffRepository implements CrudRepository<Staff>{
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Staff> staffRowMapper = new StaffRowMapper();
    public StaffRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer save (@NotNull Staff staff) {
        return jdbcTemplate.execute(
                SAVE_OR_UPDATE_STAFF,
                new SaveStaffPreparedStatementCallBack(staff));
    }
    @Override
    public int update(@NotNull Staff staff) {
        return jdbcTemplate.update(
                SAVE_OR_UPDATE_STAFF,
                staff.getId(),
                staff.getPassport(),
                staff.getName(),
                staff.getOccupation(),
                staff.getPhoneNumber(),
                staff.getCity(),
                staff.getAddress(),
                staff.getIsHired());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update(
                DELETE_BY_ID,
                id);
    }

    @Override
    public List<Staff> findAll() {
        return jdbcTemplate.query(FIND_ALL_STAFF, staffRowMapper);
    }

    @Override
    public Staff findById(Integer id) {
        List<Staff> result = jdbcTemplate.query(FIND_BY_ID, staffRowMapper, id);
        return result.get(0);
    }
}
