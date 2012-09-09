package com.x460dot11.data;

import com.x460dot11.exception.LostUpdateException;
import org.joda.time.LocalDate;
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
        String date;
        LocalDate due_date;
        LocalDate close_date;
        String assignee;
        int priority;
        String summary;
        String history;
        String final_result;

        Statement statement = connection.createStatement();
        String sqlStmt = "SELECT * FROM bug ORDER BY bug_id;";
        statement.execute(sqlStmt);
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {

            try {
                bug_id = Integer.parseInt(resultSet.getString("bug_id"));

                due_date = ((date = resultSet.getString("due_date")) == null)
                        ? null : LocalDate.parse(date);
                close_date = ((date = resultSet.getString("close_date")) == null)
                        ? null : LocalDate.parse(date);
                assignee = resultSet.getString("assignee");
                priority = Integer.parseInt(resultSet.getString("priority"));
                summary = resultSet.getString("summary");
                history = resultSet.getString("history");
                final_result = resultSet.getString("final_result");
                bug = new Bug(bug_id, due_date, close_date, assignee, priority,
                        summary, history, final_result);
                bugs.add(bug);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isOnEmailList (int id, String username) throws SQLException {

        Statement statement = connection.createStatement();
        String sqlStmt = "SELECT bug_id, username FROM email WHERE bug_id = " + id +
                " AND username = $$" + username + "$$;";
        statement.execute(sqlStmt);
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next())
            return false;
        else
            return true;
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

    public synchronized void addBug (String newSummary, String newComment, User user)
            throws SQLException {
        Statement statement = connection.createStatement();
        String sqlStmt =
                "BEGIN; " +
                "INSERT INTO bug (summary, history, created_by, modified_by) " +
                "VALUES ($$" + newSummary + "$$, $$" + newComment + "$$, $$" +
                 user.getUsername() + "$$, $$" + user.getUsername() + "$$); " +
                "INSERT INTO email (bug_id, username, created_by) VALUES ((SELECT MAX(bug_id) " +
                 "FROM bug), $$" + user.getUsername() + "$$, $$" +  user.getUsername() + "$$); " +
                 "COMMIT;";
        statement.execute(sqlStmt);
        refreshBugList();
    }

    public synchronized void updateBug (Bug v1bug, Bug v2bug, User user)
            throws SQLException, LostUpdateException {
        if (!v1bug.hasSameValuesAs(getBug(v1bug.getBug_id())))
            throw new LostUpdateException("ERROR: cannot update bug ID"  + v1bug.getBug_id() +
                    " . Please refresh and try again");

        Statement statement = connection.createStatement();
        StringBuilder sqlStmt = new StringBuilder();
        sqlStmt.append(
                "BEGIN; ");
        sqlStmt.append(
                "INSERT INTO archive (SELECT * FROM bug WHERE bug_id = " + v2bug.getBug_id() + "); ");
        sqlStmt.append(
                "UPDATE bug SET ");

        sqlStmt.append("due_date = ");
        if (v2bug.getDue_date() == null)
            sqlStmt.append("DEFAULT, ");
        else
            sqlStmt.append("$$" + v2bug.getDue_date().toString() + "$$, ");

        sqlStmt.append("close_date = ");
        if (v2bug.getClose_date() == null)
            sqlStmt.append("DEFAULT, ");
        else
            sqlStmt.append("$$" + v2bug.getClose_date().toString() + "$$, ");

        sqlStmt.append(
                "assignee = $$" + v2bug.getAssignee() + "$$, " +
                "priority = " + v2bug.getPriority() + ", " +
                "summary = $$" + v2bug.getSummary() + "$$, " +
                "history = $$" + v2bug.getHistory() + "$$, " +
                "final_result = $$" + v2bug.getFinal_result() + "$$, " +
                "modified = now(), " +
                "modified_by = $$" + user.getUsername() + "$$ " +
                "WHERE bug_id = " + v2bug.getBug_id() + "; ");
        if (!isOnEmailList(v2bug.getBug_id(), v2bug.getAssignee()))
            sqlStmt.append(
                    "INSERT INTO email(bug_id, username, created_by) VALUES (" + v2bug.getBug_id() +
                            ", $$" + v2bug.getAssignee() + "$$, $$" + user.getUsername() + "$$); ");
        if (!v1bug.getAssignee().equals(v2bug.getAssignee()) &&
                isOnEmailList(v1bug.getBug_id(), v1bug.getAssignee())) {
            sqlStmt.append(
                    "DELETE FROM email WHERE bug_id = " + v1bug.getBug_id() + " AND username = $$" +
                            v1bug.getAssignee() + "$$; ");
        }
        sqlStmt.append("COMMIT; ");
        statement.execute(sqlStmt.toString());
        refreshBugList();
     }
}