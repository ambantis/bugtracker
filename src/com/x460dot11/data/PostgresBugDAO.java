package com.x460dot11.data;

import com.x460dot11.tracker.Bug;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    @Override
    public String getAllBugHeaders() {
        boolean success = true;
        Connection connection = null;
        ResultSet resultSet = null;
        StringBuilder stringBuilder = null;
        try {
            connection = PostgresDAOFactory.createConnection();
            Statement statement = connection.createStatement();
            String sqlStmt = "SELECT * FROM bug;";
            statement.execute(sqlStmt);
            resultSet = statement.getResultSet();

            if (resultSet != null) {
                stringBuilder = new StringBuilder();
                while (resultSet.next()) {
                    stringBuilder.append(" BUG_ID | DUE_DATE  | ASSIGNEE | PRIORITY | SUMMARY <br>");
                    stringBuilder.append(resultSet.getString("bug_id"));
                    stringBuilder.append(",");
                    stringBuilder.append(resultSet.getString("due_date"));
                    stringBuilder.append(",");
                    stringBuilder.append(resultSet.getString("assignee"));
                    stringBuilder.append(",");
                    stringBuilder.append(resultSet.getString("priority"));
                    stringBuilder.append(",");
                    stringBuilder.append(resultSet.getString("summary"));
                    stringBuilder.append("<br>");
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        if (stringBuilder != null)
            return stringBuilder.toString();
        else
            return null;
    }
}
