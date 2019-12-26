package com.udacity.course3.lesson1.exercise1.exercise1;


import org.flywaydb.core.Flyway;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Application {

    public static void main(String[] args) {
        try {
            Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost/JDND-C3", "root", "rootpass").load();
            flyway.migrate();
            try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/JDND-C3?user=root&password=rootpass")) {
                System.out.println("Connected to " + conn.getMetaData().getDatabaseProductName());
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("INSERT INTO `member` (first_name,last_name,age,gender) VALUES('Rodrigo','Santiago',29,'male'),('Ruben','Nieves',30,'male'); ");
                }
                try(Statement stmt = conn.createStatement()) {
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `member`");
                    while (rs.next()) {
                        System.out.print("id: " + rs.getInt("id") + ", ");
                        System.out.print("first_name: " + rs.getString("first_name") + ", ");
                        System.out.print("last_name: " + rs.getString("last_name") + ", ");
                        System.out.print("age: " + rs.getInt("age") + ", ");
                        System.out.print("gender: " + rs.getString("gender") + ", ");
                        System.out.println("balance: " + rs.getDouble("balance"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError" + ex.getErrorCode());
        }
    }

}