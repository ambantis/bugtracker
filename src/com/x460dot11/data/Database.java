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
public class Database {
    private static String sqlStmt;
    private ArrayList<Bug> bugs = null;
    private Connection connection = null;
    private static Database database = null;

    private Database() {}

    public static Database getInstance () {
        if (database == null)
            database = new Database();
        return database;
    }

    public void init(Connection newConnection) throws SQLException {
        // TODO:2012-09-05:ambantis:Create DatabaseIsDownException
        connection = newConnection;
        bugs = new ArrayList<Bug>();
        initializeBugList();
    }

    private void initializeBugList () throws SQLException {
        Bug bug;
        int bug_id;
        String due_date;
        String assignee;
        int priority;
        String summary;
        String history;
        String final_result;
        boolean is_open;

        Statement statement = connection.createStatement();
        String sqlStmt = "SELECT * FROM bug ORDER BY bug_id;";
        statement.execute(sqlStmt);
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            bug_id = Integer.parseInt(resultSet.getString("bug_id"));

            due_date = resultSet.getString("due_date");
            assignee = resultSet.getString("assignee");

            try {
                priority = Integer.parseInt(resultSet.getString("priority"));
            } catch (NumberFormatException ex) {
                priority = 0; // invalid number format
                ex.printStackTrace();
            }

            summary = resultSet.getString("summary");
            history = resultSet.getString("history");
            final_result = resultSet.getString("final_result");
            is_open = (resultSet.getString("is_open") == "TRUE") ? true : false;

            bug = new Bug(bug_id, due_date, assignee, priority, summary, history,
                    final_result, is_open);
            bugs.add(bug);
        }
    }

    public ArrayList<Bug> getBugList () {
        return bugs;
    }

    public Bug getBug(int id) {
        for (Bug bug : bugs) {
            if (bug.getBug_id() == id)
                return bug;
        }
        // TODO:2012-09-05:ambantis:Create BugNoFoundException
        return null;
    }

    public void refreshBugList () throws SQLException {
        bugs.clear();
        initializeBugList();
    }

    public void addBug (String newSummary, String newComment) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlStmt =
                "INSERT INTO bug (summary, history) " +
                "VALUES ($$" + newSummary + "$$, $$" + newComment + "$$);";
        statement.execute(sqlStmt);
        refreshBugList();
    }

    public void updateBug (Bug newBug) throws SQLException {
        // TODO:2012-09-05:ambantis:Create SQL Transaction to prevent lost update
        Statement statement = connection.createStatement();
        String sqlStmt = "UPDATE bug SET " +
                "due_date = \'" + newBug.getDue_date() + "\', " +
                "assignee = $$" + newBug.getAssignee() + "$$, " +
                "priority = " + newBug.getPriority() + ", " +
                "summary = $$" + newBug.getSummary() + "$$, " +
                "history = $$" + newBug.getHistory() + "$$, " +
                "final_result = $$" + newBug.getFinal_result() + "$$, " +
                "is_open = " + newBug.isIs_open() + " " +
                "WHERE bug_id = " + newBug.getBug_id() + ";";
        statement.execute(sqlStmt);
        refreshBugList();
     }
}