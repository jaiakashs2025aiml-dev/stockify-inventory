
package com.stockify.config;

import java.sql.Connection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBTest implements CommandLineRunner {

    private final DBConnection dbConnection;

    public DBTest(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void run(String... args) {

        try (Connection connection = dbConnection.getConnection()) {

            System.out.println("================================");
            System.out.println("MYSQL CONNECTION SUCCESSFUL!");
            System.out.println("Database: " +
                    connection.getCatalog());
            System.out.println("================================");

        } catch (Exception e) {

            System.out.println("MYSQL CONNECTION FAILED!");
            e.printStackTrace();

        }
    }
}

