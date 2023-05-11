package com.example.blooddonation.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Hospital {
    private Integer id;
    private String name;
    private String city;
    private String adres;
    private String postalCode;
    private String phoneNumber;
    private String email;

    public Hospital(Integer id, String name, String city, String adres, String postalCode, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.adres = adres;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Hospital constructor
     * @param result ResultSet
     * @throws SQLException SQLException
     */
    public Hospital(ResultSet result) throws SQLException{
        this.id = result.getInt("id");
        this.name = result.getString("name");
        this.city = result.getString("city");
        this.adres = result.getString("adres");
        this.postalCode = result.getString("postalCode");
        this.phoneNumber = result.getString("phoneNumber");
        this.email = result.getString("email");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    // Return hospital name as string not object
    public String toString() {
        return name;
    }
}
