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
        // TODO should through a 'database is down exception'
        connection = newConnection;
        bugs = new ArrayList<Bug>();
        initializeBugList();
    }

    private void initializeBugList () throws SQLException {
        Bug bug;
        int bug_id;
        int priority;
        String due_date;
        String assignee;
        String summary;
        String description;
        String final_result;

        Statement statement = connection.createStatement();
        String sqlStmt = "SELECT * FROM bug ORDER BY bug_id;";
        statement.execute(sqlStmt);
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
    }

    public ArrayList<Bug> getBugList () {
        return bugs;
    }

    public Bug getBug(int id) {
        for (Bug bug : bugs) {
            if (bug.getBugID() == id)
                return bug;
        }
        // TODO should return a bug not found exception
        return null;
    }

    public void refreshBugList () throws SQLException {
        bugs.clear();
        initializeBugList();
    }

    public void addBug (String newBugSummary, String newBugDescription) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlStmt =
                "INSERT INTO bug (summary, description) " +
                "VALUES ($$" + newBugSummary + "$$, $$" + newBugDescription + "$$);";
        statement.execute(sqlStmt);
        refreshBugList();
    }

    public void updateBug (Bug newBug) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlStmt = "UPDATE bug SET " +
                "due_date = \'" + newBug.getDueDate() + "\', " +
                "assignee = $$" + newBug.getAssignee() + "$$, " +
                "priority = " + newBug.getPriority() + ", " +
                "summary = $$" + newBug.getSummary() + "$$, " +
                "description = $$" + newBug.getDescription() + "$$ " +
                "WHERE bug_id = " + newBug.getBugID() + ";";
        statement.execute(sqlStmt);
        refreshBugList();
     }
}