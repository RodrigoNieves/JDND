package com.udacity.course3.lesson1.exercise1.exercise1;


import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) {
        // STEP 1: Create the JDBC URL for JDND-C3 database
        try {
            Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost/JDND-C3", "root", "rootpass").load();
            flyway.migrate();
            try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/JDND-C3?user=root&password=rootpass")) {
                System.out.println("Connected to " + conn.getMetaData().getDatabaseProductName());

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError" + ex.getErrorCode());
        }
        // STEP 2: Setup and Run Flyway migration that creates the member table using its Java API
        // https://flywaydb.org/getstarted/firststeps/api#integrating-flyway
        // Note the above link talks about connecting to H2 database, for this exercise, MySQL is used. Adapt the code accordingly.


        // STEP 3: Obtain a connection to the JDND-C3 database

        // STEP 4: Use Statement to INSERT 2 records into the member table
        // NOTE: The member table is created using Flyway by placing the migration file in src/main/resources/db/migration


        // STEP 5: Read ALL the rows from the member table and print them here


        // STEP 6: verify that all inserted rows have been printed
    }

}