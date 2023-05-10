package com.example.blooddonation.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BloodBag {
    private Integer id;
    private String bloodType;
    private Integer amount;
    private Date date;
    private Integer donorID;

    public BloodBag(Integer id, String bloodType, Integer amount, Date date, Integer donorID) {
        this.id = id;
        this.bloodType = bloodType;
        this.amount = amount;
        this.date = date;
        this.donorID = donorID;
    }

    /**
     * Blood bag constructor
     * @param result ResultSet
     * @throws SQLException SQLException
     */

    public BloodBag(ResultSet result) throws SQLException{
        this.id = result.getInt("id");
        this.bloodType = result.getString("bloodType");
        this.amount = result.getInt("amount");
        this.date = result.getDate("date");
        this.donorID = result.getInt("donorID");
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDonorID() {
        return donorID;
    }

    public void setDonorID(int donorID) {
        this.donorID = donorID;
    }
}
