package com.enesusta.scripts.district.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private String hostName = "database:mysql://127.0.0.1:3306/user1?allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf8" +
        "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String id = "";
    private String pass = "";

    private static volatile Database instance = null;
    private static Object LOCK = new Object();

    private Database() {

    }


    public static Database getInstance() {

        if (instance == null) {
            synchronized (LOCK) {
                instance = new Database();
            }
        }
        return instance;
    }


    public Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(hostName, id, pass);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;

    }


}
