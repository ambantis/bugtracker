package com.ambantis.bugtracker.model;

import com.ambantis.bugtracker.exception.DaoConnectionException;
import com.ambantis.bugtracker.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.ambantis.bugtracker.model.DaoUtil.*;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 6:20 PM
 */
public class UserDaoPostgres implements UserDao {

  private static Logger logger = Logger.getLogger(UserDaoPostgres.class);

  private static final String SQL_FIND_BY_ID =
      "SELECT user_id, password, full_name, role_id, email FROM users WHERE user_id = ?";
  private static final String SQL_FIND_BY_EMAIL_AND_PASSWORD =
      "SELECT user_id, password, full_name, role_id, email FROM users WHERE user_id = ? AND password = ?";
  private static final String SQL_LIST_ORDER_BY_ID =
      "SELECT user_id, password, full_name, role_id, email FROM users ORDER BY user_id";
  private static final String SQL_LIST_FIND_DEVELOPERS =
      "SELECT user_id FROM users WHERE role_id = 'dev'";
  private static final String SQL_INSERT =
      "INSERT INTO users (user_id, password, full_name, role_id, email) VALUES (?, ?, ?, ?, ?)";
  private static final String SQL_UPDATE =
      "UPDATE users SET full_name = ?, role_id = ?, email = ? WHERE user_id = ?";
  private static final String SQL_DELETE =
      "DELETE FROM users WHERE user_id = ?";
  private static final String SQL_EXIST_USER_ID =
      "SELECT user_id FROM users WHERE user_id = ?";
  private static final String SQL_CHANGE_PASSWORD =
      "UPDATE users SET password = MD5(?) WHERE user_id = ?";

  private DaoFactory daoFactory;

  UserDaoPostgres(DaoFactory daoFactory) {
    this.daoFactory = daoFactory;
  }

  @Override
  public User find(String userId) throws DaoException, DaoConnectionException {
    return find(SQL_FIND_BY_ID, userId);
  }

  @Override
  public User find(String userId, String password) throws DaoException, DaoConnectionException {
    return find(SQL_FIND_BY_EMAIL_AND_PASSWORD, userId, password);
  }

  private User find(String sql, Object... values) throws DaoException, DaoConnectionException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    User user = null;

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, sql, false, values);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        user = map(resultSet);
      }
    } catch (SQLException e) {
      logger.error("unable to find user", e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return user;
  }

  @Override
  public ArrayList<String> developers() throws DaoException, DaoConnectionException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<String> coders = new ArrayList<String>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = connection.prepareStatement(SQL_LIST_FIND_DEVELOPERS);
      preparedStatement.execute();
      resultSet = preparedStatement.getResultSet();
      while (resultSet.next()) {
        coders.add(resultSet.getString("user_id"));
      }
    } catch (SQLException e) {
      logger.error("unable to retrieve developers list", e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
      return coders;
  }

  @Override
  public ArrayList<User> list() throws DaoException, DaoConnectionException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ArrayList<User> users = new ArrayList<User>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        users.add(map(resultSet));
      }
    } catch (SQLException e) {
      logger.error("unable to retrieve user list", e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return users;
  }

  @Override
  public void create(User user) throws IllegalArgumentException, DaoException, DaoConnectionException {
    if (user.getUserId() != null) {
      throw new IllegalArgumentException("User is already created, the user ID is not null");
    }

    Object[] values = {
      user.getUserId(),
      user.getPassword(),
      user.getFullName(),
      user.getRoleId(),
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_INSERT, true, values);
      int affectedRows = preparedStatement.executeUpdate();
      if (affectedRows == 0) {
        logger.error("unable to create user: " + user);
        throw new DaoException("Creating user failed, no rows affected.");
      }
    } catch (SQLException e) {
      logger.error("unable to create user: " + user, e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement);
    }
  }

  @Override
  public void update(User user) throws IllegalArgumentException, DaoException, DaoConnectionException {
    if (user.getUserId() == null) {
      throw new IllegalArgumentException("User is not created yet, the user ID is null.");
    }

    Object[] values = {
        user.getUserId(),
        user.getFullName(),
        user.getRoleId()
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_UPDATE, false, values);
      int affectedRows = preparedStatement.executeUpdate();
      if (affectedRows == 0) {
        logger.error("unable to update user: " + user);
        throw new DaoException("Updating user failed, no rows affected.");
      }
    } catch (SQLException e) {
      logger.error("unable to update user: " + user, e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement);
    }
  }

  @Override
  public void delete(User user) throws DaoException, DaoConnectionException {
    Object[] values = {
        user.getUserId()
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_DELETE, false, values);
      int affectedRows = preparedStatement.executeUpdate();
      if (affectedRows == 0) {
        logger.error("unable to delete user: " + user);
        throw new DaoException("Deleting user failed, no rows affected.");
      } else {
        user.setUserId(null);
      }
    } catch (SQLException e) {
      logger.error("unable to delete user: " + user, e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement);
    }
  }

  @Override
  public boolean existUserId(String userId) throws DaoException, DaoConnectionException {
    Object[] values = {
      userId
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Boolean exist = false;

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_EXIST_USER_ID, false, values);
      resultSet = preparedStatement.executeQuery();
      exist = resultSet.next();
    } catch (SQLException e) {
      logger.error("unable to validate that this user id exists: " + userId);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return exist;
  }

  @Override
  public void changePassword(User user) throws DaoException, DaoConnectionException {
    if (user.getUserId() == null) {
      throw new IllegalArgumentException("User is not created yet, the user ID is null");
    }

    Object[] values = {
        user.getUserId(),
        user.getPassword()
    };

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = daoFactory.getConnection();
      preparedStatement = prepareStatement(connection, SQL_CHANGE_PASSWORD, false, values);
      int affectedRows = preparedStatement.executeUpdate();
      if (affectedRows == 0) {
        logger.error("unable to update password for user: " + user);
        throw new DaoException("Changing password failed, no rows affected.");
      }
    } catch (SQLException e) {
      logger.error("unable to update password for user: " + user, e);
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement);
    }
  }

  private static User map(ResultSet resultSet) throws SQLException {
    User user = new User();
    user.setUserId(resultSet.getString("user_id"));
    user.setFullName(resultSet.getString("full_name"));
    user.setRoleId(resultSet.getString("role_id"));
    return user;
  }
}
