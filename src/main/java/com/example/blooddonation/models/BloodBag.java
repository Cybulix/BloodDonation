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
    private Integer workerID;
    private String workerName;
    private Integer hospitalID;
    private String hospitalName;

    public BloodBag(Integer id, String bloodType, Integer amount, Date date, Integer donorID , Integer workerID, Integer hospitalID) {
        this.id = id;
        this.bloodType = bloodType;
        this.amount = amount;
        this.date = date;
        this.donorID = donorID;
        this.workerID = workerID;
        this.hospitalID = hospitalID;
    }

    public BloodBag(Integer id, String bloodType, Integer amount, Date date, Integer donorID , String workerName, String hospitalName) {
        this.id = id;
        this.bloodType = bloodType;
        this.amount = amount;
        this.date = date;
        this.donorID = donorID;
        this.workerName = workerName;
        this.hospitalName = hospitalName;
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
        this.workerName = result.getString("workerName");
        this.hospitalName = result.getString("hospitalName");
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

    public Integer getWorkerID() {
        return workerID;
    }

    public void setWorkerID(Integer workerID) {
        this.workerID = workerID;
    }

    public Integer getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(Integer hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
