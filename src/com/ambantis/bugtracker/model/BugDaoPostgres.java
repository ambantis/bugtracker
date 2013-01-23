package com.ambantis.bugtracker.model;

import com.ambantis.bugtracker.exception.DaoConnectionException;
import com.ambantis.bugtracker.exception.DaoException;
import com.ambantis.bugtracker.exception.DaoLostUpdateException;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import static com.ambantis.bugtracker.model.DaoUtil.*;
/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 8:17 PM
 */
public class BugDaoPostgres implements BugDao {

  private static Logger logger = Logger.getLogger(BugDaoPostgres.class);

  private static final String SQL_FIND_BY_ID =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result " +
          "FROM bug " +
          "WHERE bug_id = ?";

  private static final String SQL_LIST_ALL_BUGS =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result " +
          "FROM bug " +
          "ORDER BY bug_id";

  private static final String SQL_LIST_ALL_BUGS_BY_ASSIGNEE =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result " +
          "FROM bug " +
          "WHERE assignee = ? " +
          "ORDER BY bug_id ";

  private static final String SQL_LIST_OPEN_BUGS =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result " +
          "FROM bug " +
          "WHERE close_date = '1970-01-01' " +
          "ORDER BY bug_id";

  private static final String SQL_LIST_OPEN_BUGS_BY_ASSIGNEE =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result " +
          "FROM bug " +
          "WHERE assignee = ? AND close_date = '1970-01-01' " +
          "ORDER BY bug_id ";

  private static final String SQL_LIST_CLOSED_BUGS =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result " +
          "FROM bug " +
          "WHERE close_date != '1970-01-01' " +
          "ORDER BY bug_id";

  private static final String SQL_LIST_CLOSED_BUGS_BY_ASSIGNEE =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result " +
          "FROM bug " +
          "WHERE assignee = ? AND close_date != '1970-01-01' " +
          "ORDER BY bug_id ";

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
  public Bug find(Integer id) throws DaoException, DaoConnectionException {

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
      logger.error("unable to locate bug id #" + id, e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return bug;
  }

  @Override
  public ArrayList<Bug> listAll() throws DaoException, DaoConnectionException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<Bug> bugs = new ArrayList<Bug>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = connection.prepareStatement(SQL_LIST_ALL_BUGS);
      preparedStatement.execute();
      resultSet = preparedStatement.getResultSet();
      while (resultSet.next())
        bugs.add(map(resultSet));
    } catch (SQLException e) {
      logger.error("unable to retrieve list of all bugs", e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return bugs;
  }

  @Override
  public ArrayList<Bug> listAll(String assignee) throws DaoException, DaoConnectionException {
    Object[] values = {
        assignee
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<Bug> bugs = new ArrayList<Bug>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_LIST_ALL_BUGS_BY_ASSIGNEE, false, values);
      preparedStatement.execute();
      resultSet = preparedStatement.getResultSet();
      while (resultSet.next())
        bugs.add(map(resultSet));
    } catch (SQLException e) {
      logger.error("unable to retrieve all bugs for assignee: " + assignee, e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return bugs;
  }

  @Override
  public ArrayList<Bug> listOpen() throws DaoException, DaoConnectionException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<Bug> bugs = new ArrayList<Bug>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = connection.prepareStatement(SQL_LIST_OPEN_BUGS);
      preparedStatement.execute();
      resultSet = preparedStatement.getResultSet();
      while (resultSet.next())
        bugs.add(map(resultSet));
    } catch (SQLException e) {
      logger.error("unable to retrieve list of all open bugs", e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return bugs;
  }

  @Override
  public ArrayList<Bug> listOpen(String assignee) throws DaoException, DaoConnectionException {
    Object[] values = {
        assignee
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<Bug> bugs = new ArrayList<Bug>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_LIST_ALL_BUGS_BY_ASSIGNEE, false, values);
      preparedStatement.execute();
      resultSet = preparedStatement.getResultSet();
      while (resultSet.next())
        bugs.add(map(resultSet));
    } catch (SQLException e) {
      logger.error("unable to open list of open bugs for assignee: " + assignee, e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return bugs;
  }

  @Override
  public ArrayList<Bug> listClosed() throws DaoException, DaoConnectionException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<Bug> bugs = new ArrayList<Bug>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = connection.prepareStatement(SQL_LIST_CLOSED_BUGS);
      preparedStatement.execute();
      resultSet = preparedStatement.getResultSet();
      while (resultSet.next())
        bugs.add(map(resultSet));
    } catch (SQLException e) {
      logger.error("unable to open list of closed bugs", e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return bugs;
  }

  @Override
  public ArrayList<Bug> listClosed(String assignee) throws DaoException, DaoConnectionException {
    Object[] values = {
        assignee
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<Bug> bugs = new ArrayList<Bug>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_LIST_CLOSED_BUGS_BY_ASSIGNEE, false, values);
      preparedStatement.execute();
      resultSet = preparedStatement.getResultSet();
      while (resultSet.next())
        bugs.add(map(resultSet));
    } catch (SQLException e) {
      logger.error("unable to access list of closed bugs for assignee: " + assignee, e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return bugs;
  }



  @Override
  public void create(Bug bug, User user) throws IllegalArgumentException, DaoException, DaoConnectionException {
    if (bug.getBugId() != 0) {
      logger.error("unable to create bug: " + bug + " for user: " + user + ". Bug is already created, the bug ID is not zero.");
      throw new IllegalArgumentException("Bug is already created, the bug ID is not zero.");
    }

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
        logger.error("unable to create bug: " + bug + " for user: " + user + ". Creating bug failed, no rows affected.");
        throw new DaoException("Creating bug failed, no rows affected.");
      }
      generatedKeys = preparedStatement.getGeneratedKeys();
      if (generatedKeys.next()) {
        bug.setBugId(generatedKeys.getInt(1));
      } else {
        logger.error("unable to create bug " + bug + " for user " + user + ", no generated key obtained.");
        throw new DaoException("Creating bug failed, no generated key obtained.");
      }
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, generatedKeys);
    }
  }

  @Override
  public synchronized void update(Bug bugOld, Bug bugNew, User user) throws IllegalArgumentException, DaoException, DaoConnectionException {
    if (bugNew.getBugId() == 0) {
      logger.error("unable to update bug (not created yet): " + bugNew);
      throw new IllegalArgumentException("Bug is not created yet, bug ID is 0.");
    }
    if (bugOld.hasSameValues(bugNew)) {
      logger.error("unable to update bug as values are unchanged, old version: " + bugOld + ". New version: " + bugNew);
      throw new IllegalArgumentException("Bug is unchanged, nothing to update.");
    }

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
        if (!(bugOld.hasSameValues(map(testLostUpdateResultSet)))) {
          logger.error("Cannot update bug (lost update exception): " + bugNew);
          throw new DaoLostUpdateException("Cannot update bug ID due to another update. Please refresh and try again.");
        }
      int archiveAffectedRows = archivePreparedStmt.executeUpdate();
      int updateAffectedRows = updatePreparedStmt.executeUpdate();
      if (archiveAffectedRows == 0 || updateAffectedRows == 0) {
        logger.error("Updating bug failed, no rows affected: " + bugNew);
        throw new DaoException("Updating bug failed, no rows affected");
      }
      connection.commit();
    } catch (SQLException e) {
      logger.error("Unable to process update for bug: " + bugNew, e);
      throw new DaoException(e);
    } finally {
      close(connection, testLostUpdatePreparedStmt, archivePreparedStmt, updatePreparedStmt, testLostUpdateResultSet);
    }
  }

  @Override
  public void delete(Bug bug, User user) throws DaoException, DaoConnectionException {
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
        logger.error("unable to delete bug: " + bug);
        throw new DaoException("Deleting bug failed, no rows affected");
      }
      connection.commit();
    } catch (SQLException e) {
      logger.error("unable to execute delete request for bug: " + bug, e);
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