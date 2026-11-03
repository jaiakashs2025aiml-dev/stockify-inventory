
package com.stockify.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBConnection {

    @Value("${app.db.url}")
    private String url;

    @Value("${app.db.username}")
    private String username;

    @Value("${app.db.password}")
    private String password;

    // Connect Java with MySQL using JDBC
    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
            url,
            username,
            password
        );
    }
}

