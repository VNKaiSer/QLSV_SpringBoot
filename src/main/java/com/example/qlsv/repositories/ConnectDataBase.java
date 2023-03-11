package com.example.qlsv.repositories;

import java.sql.Connection;
import java.sql.DriverManager;

import com.example.qlsv.repositories.config.ConfigDataBase;

public class ConnectDataBase implements ConfigDataBase {

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(ConfigDataBase.url, ConfigDataBase.username, ConfigDataBase.password);
        } catch (Exception ex) {
            System.out.println("Connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
