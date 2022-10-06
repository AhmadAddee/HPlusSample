package com.test.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testDB {
    public static void main(String[] args) {
        Connection connection = null;
        System.out.println("hiii");
        try {

            // load the driver class
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");

            // get hold of the DriverManager
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hplus", "root", "rootroot");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();

        }

        catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println(e.getCause());
            e.printStackTrace();

        }

        if (connection != null) {
            System.out.println("Connection made to DB!");
        }
    }
}
