package com.x460dot11.data;

import com.x460dot11.exception.LostUpdateException;

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

            try {
                bug_id = Integer.parseInt(resultSet.getString("bug_id"));
                due_date = resultSet.getString("due_date");
                assignee = resultSet.getString("assignee");
                priority = Integer.parseInt(resultSet.getString("priority"));
                summary = resultSet.getString("summary");
                history = resultSet.getString("history");
                final_result = resultSet.getString("final_result");
                is_open = (resultSet.getString("is_open").equals("TRUE"));
                bug = new Bug(bug_id, due_date, assignee, priority, summary, history,
                        final_result, is_open);
                bugs.add(bug);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Bug> getBugList () {
        return bugs;
    }

    public Bug getBug(int id) throws SQLException {
        refreshBugList();
        for (Bug bug : bugs) {
            if (bug.getBug_id() == id)
                return bug;
        }
        // TODO:2012-09-05:ambantis:Create BugNoFoundException
        throw new SQLException("Bug not found");
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

    public synchronized void updateBug (Bug v1bug, Bug v2bug) throws SQLException {
        if (!v1bug.hasSameValuesAs(getBug(v1bug.getBug_id())))
            throw new LostUpdateException("ERROR: cannot update bug ID"  + v1bug.getBug_id() +
                    " . Please refresh and try again");

        Statement statement = connection.createStatement();
        String sqlStmt = "UPDATE bug SET " +
                "due_date = \'" + v2bug.getDue_date() + "\', " +
                "assignee = $$" + v2bug.getAssignee() + "$$, " +
                "priority = " + v2bug.getPriority() + ", " +
                "summary = $$" + v2bug.getSummary() + "$$, " +
                "history = $$" + v2bug.getHistory() + "$$, " +
                "final_result = $$" + v2bug.getFinal_result() + "$$, " +
                "is_open = " + v2bug.isIs_open() + " " +
                "WHERE bug_id = " + v2bug.getBug_id() + ";";
        statement.execute(sqlStmt);
        refreshBugList();
     }
}