package com.x460dot11.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: ambantis
 * Date: 8/28/12
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class PostgresBugDAO implements BugDAO {

    @Override
    public boolean newBug(String summary, String description) {
        boolean success = true;
        Connection connection = null;
        try {
            connection = PostgresDAOFactory.createConnection();
            Statement statement = connection.createStatement();
            // TODO the sql statement for newBug doesn't work if there is an apostrophe in a field.
            String sqlStmt = "INSERT INTO bug (summary, description) " +
                    "VALUES (E'" + summary + "', E'" + description + "');";
            statement.execute(sqlStmt);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        return true;
    }

    @Override
    public boolean assignBug(int bugID, String user) {
        return false;
    }

    @Override
    public boolean updateBug(String due_date, int priority, String summary, String description) {
        return false;
    }
}
