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
}
