package com.example.blooddonation;

import java.sql.*;

public class Database {
    private Connection conn;
    private Statement stm;
    public Database() throws SQLException{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bravis?user=root&password=");
        // Execute Query
        stm = conn.createStatement();
    }

    public void getData() throws SQLException{
        // Query to get data
        ResultSet rs = stm.executeQuery("SELECT * FROM hospitals");
        while (rs.next()){
            System.out.println(rs.getInt("id"));
        }
    }

    public ResultSet getHospitalData() throws SQLException{
        return stm.executeQuery("SELECT * FROM hospitals");
    }
    public ResultSet getWorkersData() throws SQLException{
        return stm.executeQuery("SELECT * FROM workers");
    }

    public ResultSet getDonorsData() throws SQLException{
        return stm.executeQuery("SELECT * FROM donors");
    }

    public void createDonor(String firstName, String lastName, String phoneNumber, String email, String birthDate, String bsn)
            throws SQLException {
        // Query with placeholders
        String query = String.format("INSERT INTO `donors` (`firstName`, `lastName`, `phoneNumber`, `email`, `birthDate`, `bsn`) " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                firstName, lastName, phoneNumber, email, birthDate, bsn);
        stm.execute(query);
    }

    public void updateDonorData(Integer id, String column, String newData) throws SQLException{
        // Prepare query with placeholders
        String query = String.format("UPDATE donors SET %s = '%s' WHERE `id` = %d", column, newData, id);
        // Execute update query
        stm.execute(query);
    }

    public void deleteDonor(Integer id) throws SQLException {
        String query = String.format("DELETE FROM `donors` WHERE id = %d", id);
        stm.execute(query);
    }

    public Integer getNextID() throws SQLException{
        ResultSet resultSet = stm.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'bravis' AND TABLE_NAME = 'donors'; ");
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return null; // or handle the case where no result is found
    }

    public ResultSet getBloodBagsData() throws SQLException {
        return stm.executeQuery("SELECT blood_bags.id, blood_bags.bloodType, blood_bags.amount, blood_bags.date, blood_bags.donorID, blood_bags.workerID, blood_bags.hospitalID, \n" +
                "CONCAT(workers.firstName, ' ', workers.lastName) AS workerName,\n" +
                "hospitals.name AS hospitalName\n" +
                "FROM `blood_bags`\n" +
                "INNER JOIN workers\n" +
                "ON workers.id = blood_bags.workerID\n" +
                "INNER JOIN hospitals\n" +
                "ON hospitals.id = blood_bags.hospitalID");
    }

    public void updateBloodBagData(Integer id, String column, String newData) throws SQLException{
        // Prepare query with placeholders
        String query = String.format("UPDATE `blood_bags` SET %s = '%s' WHERE `id` = %d", column, newData, id);
        // Execute update query
        stm.execute(query);
    }

    public void deleteBloodBag(Integer id) throws SQLException {
        String query = String.format("DELETE FROM `blood_bags` WHERE id = %d", id);
        stm.execute(query);
    }
}
