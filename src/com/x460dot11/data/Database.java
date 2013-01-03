package com.x460dot11.data;

import com.x460dot11.exception.LostUpdateException;
import org.joda.time.LocalDate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
  private ArrayList<Bug> bugs = null;
  private ArrayList<String> coders = null;
  private ArrayList<User> users = null;
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
    coders = new ArrayList<String>();
    users = new ArrayList<User>();
    initialize();
  }

  private void initialize() throws SQLException {
    initializeBugList();
    initializeDeveloperList();
    initializeUserList();
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

  private void initializeDeveloperList() throws SQLException {
    Statement statement = connection.createStatement();
    String sqlStmt =
        "SELECT users.user_id FROM users WHERE users.role_id = $$dev$$;";

    statement.execute(sqlStmt);
    ResultSet resultSet = statement.getResultSet();

    while (resultSet.next()) {
      coders.add(resultSet.getString("user_id"));
    }
  }

  private void initializeUserList () throws SQLException {
    Statement statement = connection.createStatement();
    String sqlStmt =
        "SELECT users.user_id, users.password, users.full_name, users.role_id, users.email FROM users;";

    statement.execute(sqlStmt);
    ResultSet resultSet = statement.getResultSet();

    while (resultSet.next()) {
      String userId = resultSet.getString("user_id");
      String password = resultSet.getString("password");
      String fullName = resultSet.getString("full_name");
      String roleId = resultSet.getString("role_id");
      String email = resultSet.getString("email");
      users.add(new User(userId, password, fullName, roleId, email));
    }
  }

//  public boolean isOnEmailList (int id, String username) throws SQLException {
//    // we don't want to insert the default value of "unk" to the email table
//    if (username.equals("unk"))
//      return true;
//
//    Statement statement = connection.createStatement();
//    String sqlStmt = "SELECT bug_id, username FROM email WHERE bug_id = " + id +
//        " AND username = $$" + username + "$$;";
//    statement.execute(sqlStmt);
//    ResultSet resultSet = statement.getResultSet();
//    if (resultSet.next())
//      return true;
//    else
//      return false;
//  }

  public ArrayList<Bug> getBugs() {
    return bugs;
  }

  public ArrayList<User> getUsers() {
    return users;
  }

  public ArrayList<String> getCoders() {
    return coders;
  }

  public User getUser(String id) throws SQLException {
    refresh();
    for (User user : users) {
      if (user.getUserId().equals(id))
        return user;
    }

    //  todo:2013-01-02:ambantis:Create UserNotFoundException
    throw new SQLException("User not found");
  }

  public User validateUser(String id, String password) throws SQLException {
    refresh();
    for (User user : users) {
      if (user.getUserId().equals(id) && user.getPassword().equals(password) && !user.getUserId().equals("unk"))
        return user;
    }
    //  todo:2013-01-02:ambantis:Create UserNotFoundException
    throw new SQLException("User name and/or password invalid");
  }

  public Bug getBug(int id) throws SQLException {
    refresh();
    for (Bug bug : bugs) {
      if (bug.getBug_id() == id)
        return bug;
    }
    // TODO:2012-09-05:ambantis:Create BugNotFoundException
    throw new SQLException("Bug not found");
  }

//  private Bug getLatestBug() {
//    return bugs.get(bugs.size() - 1);
//  }

  public void refresh() throws SQLException {
    bugs.clear();
    initializeBugList();
    coders.clear();
    initializeDeveloperList();
    users.clear();
    initializeUserList();
  }

  public synchronized void addBug (String newSummary, String newComment, User user)
      throws SQLException {
    Statement statement = connection.createStatement();
    String sqlStmt =
        "BEGIN; " +
            "INSERT INTO bug (summary, history, created_by, modified_by) " +
            "VALUES ($$" + newSummary + "$$, $$" + newComment + "$$, $$" +
            user.getUserId() + "$$, $$" + user.getUserId() + "$$); " +
//            "INSERT INTO email (bug_id, username, created_by) VALUES ((SELECT MAX(bug_id) " +
//            "FROM bug), $$" + user.getUserId() + "$$, $$" +  user.getUserId() + "$$); " +
            "COMMIT;";
    statement.execute(sqlStmt);
    refresh();
  }

  public synchronized void updateBug (Bug v1bug, Bug v2bug, User user)
      throws SQLException, LostUpdateException {
    // if there was no change, then there is nothing to update
    if (v1bug.hasSameValuesAs(v2bug))
      return;
    // if the pre-change version of the bug doesn't match what is currently in the
    // database, then we have a lost update condition.
    if (!v1bug.hasSameValuesAs(getBug(v1bug.getBug_id())))
      throw new LostUpdateException("ERROR: cannot update bug ID"  + v1bug.getBug_id() +
          " . Please refresh and try again");

    Statement statement = connection.createStatement();
    StringBuilder sqlStmt = new StringBuilder();
    // do the sql update as a transaction, so everything must update or nothing at all
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
            "modified_by = $$" + user.getUserId() + "$$ " +
            "WHERE bug_id = " + v2bug.getBug_id() + "; ");
//    if (!isOnEmailList(v2bug.getBug_id(), v2bug.getAssignee())) {
//      sqlStmt.append(
//          "INSERT INTO email(bug_id, username, created_by) VALUES (" + v2bug.getBug_id() +
//              ", $$" + v2bug.getAssignee() + "$$, $$" + user.getUserId() + "$$); ");
//    }
//    if (!isOnEmailList(v2bug.getBug_id(), user.getUserId())) {
//      sqlStmt.append(
//          "INSERT INTO email(bug_id, username, created_by) VALUES (" + v2bug.getBug_id() +
//              ", $$" + user.getUserId() + "$$, $$" + user.getUserId() + "$$); ");
//    }
//    if (!v1bug.getAssignee().equals(v2bug.getAssignee()) &&
//        isOnEmailList(v1bug.getBug_id(), v1bug.getAssignee())) {
//      sqlStmt.append(
//          "DELETE FROM email WHERE bug_id = " + v1bug.getBug_id() + " AND username = $$" +
//              v1bug.getAssignee() + "$$; ");
//    }
    sqlStmt.append("COMMIT;");
    statement.execute(sqlStmt.toString());
    refresh();

  }

  public synchronized void closeBug(Bug v1bug, Bug v2bug, User user)
      throws SQLException, LostUpdateException {
    // if the pre-change version of the bug doesn't match what is currently in the
    // database, then we have a lost update condition.
    if (!v1bug.hasSameValuesAs(getBug(v1bug.getBug_id())))
      throw new LostUpdateException("ERROR: cannot update bug ID"  + v1bug.getBug_id() +
          " . Please refresh and try again");

    Statement statement = connection.createStatement();
    StringBuilder sqlStmt = new StringBuilder();
    sqlStmt.append(
        "BEGIN; ");
    sqlStmt.append("INSERT INTO archive (SELECT * FROM bug WHERE bug_id = " +
        v2bug.getBug_id() + "); ");
    sqlStmt.append("" +
        "UPDATE bug SET due_date = NULL, close_date = current_date, assignee = DEFAULT, " +
        "final_result = $$" + v2bug.getFinal_result() + "$$, modified = now(), " +
        "modified_by = $$" + user.getUserId() + "$$ " +
        "WHERE bug_id = " + v2bug.getBug_id() + "; ");
    sqlStmt.append("COMMIT;");
    statement.execute(sqlStmt.toString());
    refresh();
  }
}