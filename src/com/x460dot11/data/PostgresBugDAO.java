package com.x460dot11.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.apache.commons.lang3.StringUtils.repeat;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/28/12
 * Time: 9:37 PM
 */
public class PostgresBugDAO implements BugDAO {

    @Override
    public boolean newBug(String summary, String description) {
        boolean success = true;
        Connection connection = null;
        try {
            connection = PostgresDAOFactory.createConnection();
            Statement statement = connection.createStatement();
            // TODO implement org.apache.commons.lang.StringEscapeUtils for all SQL statements
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
        String space = "&nbsp";
        int len;
        String bug_id;
        String due_date;
        String assignee;
        String priority;
        String summary;
        final int STD_FIELD_LEN = 10;
        final int SUMMARY_FIELD_LEN = 35;
        try {
            connection = PostgresDAOFactory.createConnection();
            Statement statement = connection.createStatement();
            String sqlStmt = "SELECT * FROM bug;";
            statement.execute(sqlStmt);
            resultSet = statement.getResultSet();

            if (resultSet != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("&nbspBUG_ID&nbsp&nbsp&nbsp|&nbspDUE_DATE&nbsp" +
                        "|&nbspASSIGNEE&nbsp|&nbspPRIORITY&nbsp|&nbspSUMMARY<br>");
                stringBuilder.append(repeat("=", 80));
                stringBuilder.append("<br>");

                while (resultSet.next()) {
                    bug_id = resultSet.getString("bug_id");
                    due_date = resultSet.getString("due_date");
                    assignee = resultSet.getString("assignee");
                    priority = resultSet.getString("priority");
                    summary = resultSet.getString("summary");

                                        // bug_id can't be null per database schema
                    stringBuilder.append(bug_id);
                    stringBuilder.append(repeat(space, STD_FIELD_LEN - bug_id.length()));
                    stringBuilder.append("|");

                    if (due_date == null) {
                        stringBuilder.append(repeat(space, STD_FIELD_LEN));
                    } else {
                        stringBuilder.append(due_date);
                        stringBuilder.append(repeat(space, STD_FIELD_LEN - due_date.length()));
                    }
                    stringBuilder.append("|");

                    if (assignee == null) {
                        stringBuilder.append(repeat(space, STD_FIELD_LEN));
                    } else {
                        stringBuilder.append(assignee);
                        stringBuilder.append(repeat(space, STD_FIELD_LEN - assignee.length()));
                    }
                    stringBuilder.append("|");

                    if (priority == null) {
                        stringBuilder.append(repeat(space, STD_FIELD_LEN));
                    } else {
                        stringBuilder.append(priority);
                        stringBuilder.append(repeat(space, STD_FIELD_LEN - priority.length()));
                    }
                    stringBuilder.append("|");

                    // summary can't be null per database schema
                    len = (summary.length() > SUMMARY_FIELD_LEN) ? SUMMARY_FIELD_LEN : summary.length();
                    stringBuilder.append(summary.substring(0, len));
                    stringBuilder.append((len > SUMMARY_FIELD_LEN) ? "..." : "");

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

    @Override
    public Bug getBug(int bugID) {
        Connection connection = null;
        ResultSet resultSet = null;
        String bug_id;
        String due_date;
        String assignee;
        String priority;
        String summary;
        String description;
        Bug bug = null;
        try {
            connection = PostgresDAOFactory.createConnection();
            Statement statement = connection.createStatement();
            String sqlStmt = "SELECT * FROM bug WHERE bug_id = " + bugID + ";";
            statement.execute(sqlStmt);
            resultSet = statement.getResultSet();

            if (resultSet != null) {
                resultSet.next();
                bug_id = resultSet.getString("bug_id");
                due_date = resultSet.getString("due_date");
                assignee = resultSet.getString("assignee");
                priority = resultSet.getString("priority");
                summary = resultSet.getString("summary");
                description = resultSet.getString("description");
                if (due_date == null)
                    due_date = "";
                if (assignee == null)
                    assignee = "";
                if (priority == null)
                    priority = "";
                if (summary == null)
                    summary = "";
                if (description == null)
                    description = "";
                bug = new Bug(bug_id, priority, due_date, assignee, summary, description);
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bug;
    }

    @Override
    public boolean editBug(Bug bug) {
        Connection connection = null;
        ResultSet resultSet = null;
        int bug_id = bug.getBugID();
        String due_date = bug.getDueDate();
        String assignee = bug.getAssignee();
        String priority = bug.getPriority();
        String summary = bug.getSummary();
        String description = bug.getDescription();
        try {
            connection = PostgresDAOFactory.createConnection();
            Statement statement = connection.createStatement();
            String sqlStmt = "UPDATE bug " +
                    "SET due_date = \'" + due_date + "\', " +
                    "assignee = \'" + assignee + "\', "  +
                    "priority = \'" + priority + "\', " +
                    "summary = \'" + summary + "\', " +
                    "description = \'" + description + "\' " +
                    "WHERE bug_id = \'" + bug_id + "\';";
            statement.execute(sqlStmt);
            connection.close();

    } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}