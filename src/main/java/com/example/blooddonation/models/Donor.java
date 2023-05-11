package com.example.blooddonation.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Donor {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private String bsn;

    public Donor(Integer id, String firstName, String lastName, String phoneNumber, String email, Date birthDate, String bsn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.bsn = bsn;
    }

    /**
     * Donor constructor
     * @param result ResultSet
     * @throws SQLException SQLException
     */
    public Donor(ResultSet result) throws SQLException{
        this.id = result.getInt("id");
        this.firstName = result.getString("firstName");
        this.lastName = result.getString("lastName");
        this.phoneNumber = result.getString("phoneNumber");
        this.email = result.getString("email");
        this.birthDate = result.getDate("birthDate");
        this.bsn = result.getString("bsn");
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    @Override
    // Return donors full name as string not object
    public String toString() {
        return firstName + " " + lastName;
    }
}
