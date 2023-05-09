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

    public void updateDonorData(Integer id, String column, String newData) throws SQLException{
        // Prepare query with placeholders
        String query = String.format("UPDATE donors SET %s = '%s' WHERE `id` = %d", column, newData, id);
        // Execute update query
        stm.execute(query);
    }

    public Integer getNextID() throws SQLException{
        ResultSet resultSet = stm.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'bravis' AND TABLE_NAME = 'donors'; ");
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return null; // or handle the case where no result is found
    }
}
