package com.x460dot11.model;

import org.joda.time.LocalDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import static com.x460dot11.model.DaoUtil.*;
/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 8:17 PM
 */
public class BugDaoPostgres implements BugDao {
  private static final String SQL_FIND_BY_ID =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result " +
          "FROM bug " +
          "WHERE bug_id = ?";

  private static final String SQL_LIST_ORDER_BY_ID =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result " +
          "FROM bug " +
          "ORDER BY bug_id";

  private static final String SQL_LIST_ORDER_BY_ID_FIND_BY_ASSIGNEE =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result " +
          "FROM bug " +
          "ORDER BY bug_id " +
          "WHERE assignee = ?";

  private static final String SQL_INSERT =
      "INSERT INTO bug (due_date, close_date, assignee, priority, summary, history, final_result, created_by, " +
          "modified_by) VALUES (current_date, '1970-01-01', ?, ?, ?, ?, ?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE bug SET due_date = ?, close_date = ?, assignee = ?, priority = ?, summary = ?, history = ?, " +
          "final_result = ?, modified = now(), modified_by = ? WHERE bug_id = ?";

  private static final String SQL_DELETE =
      "DELETE FROM bug WHERE user_id = ?";

  private static final String SQL_UPDATE_MODIFIED_BY =
      "UPDATE bug SET modified = now(), modified_by = ? WHERE bug_id = ?";

  private static final String SQL_ARCHIVE =
      "INSERT INTO archive (SELECT * FROM bug WHERE bug_id = ?)";

  private DaoFactory daoFactory;

  BugDaoPostgres(DaoFactory daoFactory) {
    this.daoFactory = daoFactory;
  }

  @Override
  public Bug find(Integer id) throws DaoException {

    Object[] values = {
        id
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Bug bug = null;

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_FIND_BY_ID, false, values);
      preparedStatement.execute();
      resultSet = preparedStatement.getResultSet();
      if (resultSet.next())
        bug = map(resultSet);
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return bug;
  }

  @Override
  public ArrayList<Bug> list() throws DaoException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<Bug> bugs = new ArrayList<Bug>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
      preparedStatement.execute();
      resultSet = preparedStatement.getResultSet();
      while (resultSet.next())
        bugs.add(map(resultSet));
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return bugs;
  }

  @Override
  public ArrayList<Bug> list(String assignee) throws DaoException {
    Object[] values = {
        assignee
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<Bug> bugs = new ArrayList<Bug>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_LIST_ORDER_BY_ID_FIND_BY_ASSIGNEE, false, values);
      preparedStatement.execute();
      resultSet = preparedStatement.getResultSet();
      while (resultSet.next())
        bugs.add(map(resultSet));
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return bugs;
  }

  @Override
  public void create(Bug bug, User user) throws IllegalArgumentException, DaoException {
    if (bug.getBugId() != 0)
      throw new IllegalArgumentException("Bug is already created, the bug ID is not zero.");

    Object[] values = {
        bug.getAssignee(),
        bug.getPriority(),
        bug.getSummary(),
        bug.getHistory(),
        bug.getFinalResult(),
        user.getUserId(),
        user.getUserId()
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet generatedKeys = null;

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_INSERT, true, values);
      int affectedRows = preparedStatement.executeUpdate();
      if (affectedRows == 0) {
        throw new DaoException("Creating bug failed, no rows affected.");
      }
      generatedKeys = preparedStatement.getGeneratedKeys();
      if (generatedKeys.next()) {
        bug.setBugId(generatedKeys.getInt(1));
      } else {
        throw new DaoException("Creating user failed, no generated key obtained.");
      }
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, generatedKeys);
    }
  }

  @Override
  public void update(Bug bugOld, Bug bugNew, User user) throws IllegalArgumentException, DaoException {
    if (bugNew.getBugId() == 0)
      throw new IllegalArgumentException("Bug is not created yet, bug ID is 0.");
    if (bugOld.hasSameValues(bugNew))
      throw new IllegalArgumentException("Bug is unchanged, nothing to update.");

    Object[] archiveValues = {
        bugNew.getBugId()
    };

    Object[] updateValues = {
        new Date(bugNew.getDueDate().toDate().getTime()),
        new Date(bugNew.getCloseDate().toDate().getTime()),
        bugNew.getAssignee(),
        bugNew.getPriority(),
        bugNew.getSummary(),
        bugNew.getHistory(),
        bugNew.getFinalResult(),
        user.getUserId(),
        bugNew.getBugId()
    };

    Connection connection = null;
    PreparedStatement testLostUpdatePreparedStmt = null;
    ResultSet testLostUpdateResultSet = null;
    PreparedStatement archivePreparedStmt = null;
    PreparedStatement updatePreparedStmt = null;

    try {
      connection = daoFactory.getConnection();
      testLostUpdatePreparedStmt = prepareStatement(connection, SQL_FIND_BY_ID, false, archiveValues);
      archivePreparedStmt = prepareStatement(connection, SQL_ARCHIVE, false, archiveValues);
      updatePreparedStmt = prepareStatement(connection, SQL_UPDATE, false, updateValues);
      connection.setAutoCommit(false);
      testLostUpdatePreparedStmt.execute();
      archivePreparedStmt.execute();
      updatePreparedStmt.execute();
      testLostUpdateResultSet = testLostUpdatePreparedStmt.getResultSet();
      if (testLostUpdateResultSet.next())
        if (!(bugOld.hasSameValues(map(testLostUpdateResultSet))))
          throw new DaoException("Cannot update bug ID due to another update. Please refresh and try again.");
      int archiveAffectedRows = archivePreparedStmt.executeUpdate();
      int updateAffectedRows = updatePreparedStmt.executeUpdate();
      if (archiveAffectedRows == 0 || updateAffectedRows == 0) {
        throw new DaoException("Updating bug failed, no rows affected");
      }
      connection.commit();
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, testLostUpdatePreparedStmt, archivePreparedStmt, updatePreparedStmt, testLostUpdateResultSet);
    }
  }

  @Override
  public void delete(Bug bug, User user) throws DaoException {
    if (bug.getBugId() == 0) {
      throw new IllegalArgumentException("Bug not created yet, the bug ID is null");
    }

    Object[] deleteValues = {
        bug.getBugId()
    };

    Object[] updateModifiedByValues = {
        bug.getBugId(),
        user.getUserId()
    };

    Connection connection = null;
    PreparedStatement updateModifiedByStatement = null;
    PreparedStatement archivePreparedStatement = null;
    PreparedStatement deletePreparedStatement = null;

    try {
      connection = daoFactory.getConnection();
      archivePreparedStatement = prepareStatement(connection, SQL_ARCHIVE, false, deleteValues);
      updateModifiedByStatement = prepareStatement(connection, SQL_UPDATE_MODIFIED_BY, false, updateModifiedByValues);
      deletePreparedStatement = prepareStatement(connection, SQL_DELETE, false, deleteValues);
      connection.setAutoCommit(false);
      int modifiedByRowsAffected = updateModifiedByStatement.executeUpdate();
      int archiveRowsAffected = archivePreparedStatement.executeUpdate();
      int deleteRowsAffected = deletePreparedStatement.executeUpdate();
      if (modifiedByRowsAffected == 0 || archiveRowsAffected == 0 || deleteRowsAffected == 0) {
        throw new DaoException("Deleting bug failed, no rows affected");
      }
      connection.commit();
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, updateModifiedByStatement, archivePreparedStatement, deletePreparedStatement);
    }
  }

  private static Bug map(ResultSet resultSet) throws SQLException {
    Bug bug = new Bug();

    bug.setBugId(resultSet.getInt("bug_id"));
    bug.setDueDate(LocalDate.parse(resultSet.getString("due_date")));
    bug.setCloseDate(LocalDate.parse(resultSet.getString("close_date")));
    bug.setAssignee(resultSet.getString("assignee"));
    bug.setPriority(resultSet.getInt("priority"));
    bug.setSummary(resultSet.getString("summary"));
    bug.setHistory(resultSet.getString("history"));
    bug.setFinalResult(resultSet.getString("final_result"));
    return bug;
  }
}