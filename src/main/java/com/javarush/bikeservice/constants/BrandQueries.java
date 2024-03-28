package com.javarush.bikeservice.constants;

public class BrandQueries {
    public static final String SAVE_OR_UPDATE_BRAND = "INSERT INTO bikes_and_parts_brands(id, name, office_country, production_country) VALUES(?, ?, ?, ?)";
    public static final String DELETE_BY_ID = "DELETE FROM bikes_and_parts_brands AS b WHERE b.id = ?";
    public static final String FIND_ALL_BRANDS = "SELECT * FROM bikes_and_parts_brands";
    public static final String FIND_BY_ID = "SELECT * FROM bikes_and_parts_brands AS b WHERE b.id = ?";
}
