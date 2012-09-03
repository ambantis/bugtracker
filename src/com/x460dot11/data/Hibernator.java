package com.x460dot11.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * User: Alexandros Bantis
 * Date: 9/2/12
 * Time: 2:33 PM
 */
public class Hibernator {
        private static final String SELECT_ALL = "SELECT * FROM bug;";
        private static String sqlStmt;

    public static ArrayList<Bug> initializeBugList (Connection connection) throws SQLException {
        ArrayList<Bug> bugs = new ArrayList<Bug>();
        Bug bug;
        int bug_id;
        int priority;
        String due_date;
        String assignee;
        String summary;
        String description;
        String final_result;

        Statement statement = connection.createStatement();
        statement.execute(SELECT_ALL);
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            bug_id = Integer.parseInt(resultSet.getString("bug_id"));
            priority = Integer.parseInt(resultSet.getString("priority"));
            due_date = resultSet.getString("due_date");
            assignee = resultSet.getString("assignee");
            summary = resultSet.getString("summary");
            description = resultSet.getString("description");
            final_result = resultSet.getString("final_result");
            bug = new Bug(bug_id, priority, due_date, assignee, summary, description, final_result);
            bugs.add(bug);
        }
        return bugs;
    }
}
