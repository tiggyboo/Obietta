package edu.mayo.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by afs01 on 9/28/17.
 */
public class ConnectionManager {
    private static String url = "jdbc:sybase:Tds:testrch17-15-ip.mayo.edu:2025/mdsdb_rch01_test";

    private static String driverName = "com.sybase.jdbc4.jdbc.SybDriver";
    private static String username = "webuser";
    private static String password = "beerme";
    private static Connection con;

    public static Connection getConnection() {

            try {
                Class.forName(driverName);
                try {
                    con = DriverManager.getConnection(url, username, password);
                } catch (SQLException ex) {
                    // log an exception. fro example:
                    System.out.println("Failed to create the database connection.");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Drivername problem!");
            }

        return con;
    }
}
