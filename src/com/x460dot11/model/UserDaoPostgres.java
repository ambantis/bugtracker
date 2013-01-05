package com.x460dot11.model;

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
 * Time: 6:20 PM
 */
public class UserDaoPostgres implements UserDao {

  private static final String SQL_FIND_BY_ID =
      "SELECT user_id, password, full_name, role_id, email FROM users WHERE user_id = ?";
  private static final String SQL_FIND_BY_EMAIL_AND_PASSWORD =
      "SELECT user_id, password, full_name, role_id, email FROM users WHERE user_id = ? AND password = MD5(?)";
  private static final String SQL_LIST_ORDER_BY_ID =
      "SELECT user_id, password, full_name, role_id, email FROM users ORDER BY user_id";
  private static final String SQL_INSERT =
      "INSERT INTO users (user_id, password, full_name, role_id, email) VALUES (?, MD5(?), ?, ?, ?)";
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
  public User find(String userId) throws DaoException {
    return find(SQL_FIND_BY_ID, userId);
  }

  @Override
  public User find(String email, String password) throws DaoException {
    return find(SQL_FIND_BY_EMAIL_AND_PASSWORD, email, password);
  }

  private User find(String sql, Object... values) throws DaoException {
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
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return user;
  }

  @Override
  public List<User> list() throws DaoException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<User> users = new ArrayList<User>();

    try {
      connection = daoFactory.getConnection();
      preparedStatement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        users.add(map(resultSet));
      }
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return users;
  }

  @Override
  public void create(User user) throws IllegalArgumentException, DaoException {
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
        throw new DaoException("Creating user failed, no rows affected.");
      }
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement);
    }
  }

  @Override
  public void update(User user) throws IllegalArgumentException, DaoException {
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
        throw new DaoException("Updating user failed, no rows affected.");
      }
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement);
    }
  }

  @Override
  public void delete(User user) throws DaoException {
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
        throw new DaoException("Deleting user failed, no rows affected.");
      } else {
        user.setUserId(null);
      }
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement);
    }
  }

  @Override
  public boolean existUserId(String userId) throws DaoException {
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
      throw new DaoException(e);
    } finally {
      close(connection, preparedStatement, resultSet);
    }
    return exist;
  }

  @Override
  public void changePassword(User user) throws DaoException {
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
        throw new DaoException("Changing password failed, no rows affected.");
      }
    } catch (SQLException e) {
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
