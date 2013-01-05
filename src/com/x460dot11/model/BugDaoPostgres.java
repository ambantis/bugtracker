package com.x460dot11.model;

import org.joda.time.LocalDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.x460dot11.model.DaoUtil.*;
/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 8:17 PM
 */
public class BugDaoPostgres implements BugDao {
  private static final String SQL_FIND_BY_ID =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result FROM bug WHERE bug_id = ?";

  private static final String SQL_LIST_ORDER_BY_ID =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result FROM bug ORDER BY bug_id";

  private static final String SQL_LIST_ORDER_BY_ID_FIND_BY_ASSIGNEE =
      "SELECT bug_id, due_date, close_date, assignee, priority, summary, history, final_result FROM bug " +
          "ORDER BY bug_id WHERE assignee = ?";

  private static final String SQL_INSERT =
      "INSERT INTO bug (due_date, close_date, assignee, priority, summary, history, final_result) " +
          "VALUES (?, ?, ?, ?, ?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE bug SET due_date = ?, close_date = ?, assignee = ?, priority = ?, summary = ?, history = ?, final_result = ?)";

  private static final String SQL_DELETE =
      "DELETE FROM bug WHERE user_id = ?";

  private DaoFactory daoFactory;

  BugDaoPostgres(DaoFactory daoFactory) {
    this.daoFactory = daoFactory;
  }

  @Override
  public Bug find(Integer bugId) throws DaoException {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Bug> list() throws DaoException {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public List<Bug> list(String assignee) throws DaoException {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void create(Bug bug) throws IllegalArgumentException, DaoException {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void update(Bug bug) throws IllegalArgumentException, DaoException {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void delete(Bug bug) throws DaoException {
    //To change body of implemented methods use File | Settings | File Templates.
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