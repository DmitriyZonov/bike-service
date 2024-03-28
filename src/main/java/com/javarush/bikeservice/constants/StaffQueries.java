package com.javarush.bikeservice.constants;

public class StaffQueries {
    public static final String SAVE_OR_UPDATE_STAFF = "INSERT INTO employee(id, passport, name, occupation, phone_number, city, address, is_hired) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_BY_ID = "DELETE FROM employee AS s WHERE s.id = ?";
    public static final String FIND_ALL_STAFF = " SELECT * FROM employee";
    public static final String FIND_BY_ID = "SELECT * FROM employee AS s WHERE s.id = ?";
}
