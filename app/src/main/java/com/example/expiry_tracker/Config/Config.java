package com.example.expiry_tracker.Config;


import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Database Connector
public class Config {
    // Database url, user and password
    String DB_URL = "jdbc:mysql://localhost:3306/ExpiryTracker";
    String DB_USER = "root";
    String DB_PASSWORD = "";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("infoffff", e.toString());
            return null;
        }catch(Exception e)
        {
            Log.d("infoffff", e.toString());
            return null;
        }
    }
}


