package com.example.blooddonation.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Worker {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Worker(Integer id, String firstName, String lastName, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Worker constructor
     * @param result ResultSet
     * @throws SQLException SQLException
     */
    public Worker(ResultSet result) throws SQLException {
        this.id = result.getInt("id");
        this.firstName = result.getString("firstName");
        this.lastName = result.getString("lastName");
        this.phoneNumber = result.getString("phoneNumber");
        this.email = result.getString("email");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
