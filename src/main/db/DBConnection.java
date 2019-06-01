package main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TimeZone;

public class DBConnection {

    private DBConnection() {
    }

    private final static String DBURL = "jdbc:mysql://localhost:3306/jobinterviewdb?serverTimezone=" + TimeZone.getDefault().getID();
    private final static String DBUSER = "root";
    private final static String DBPASS = "123qweasdzxc";
    private final static String DBDRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName(DBDRIVER);
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (Exception e) {
            System.err.println("Connection error from data " + e.getMessage());
            return null;
        }
        return connection;
    }


}
