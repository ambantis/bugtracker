package com.x460dot11.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/28/12
 * Time: 8:20 PM
 */
public class PostgresDAOFactory extends DAOFactory {
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String URL = "jdbc:postgresql://50.112.254.147:5432/bugdb";
    public static final String USER = "roach";
    public static final String PASSWORD = "motel";

    public static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load driver");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("Could not create connection");
        }
        return connection;
    }

    public BugDAO getBugDAO() {
        return new PostgresBugDAO();
    }
}
