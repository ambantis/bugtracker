package com.x460dot11.model;

import java.sql.*;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 5:28 PM
 */
public final class DaoUtil {

  private DaoUtil() {}

  public static PreparedStatement prepareStatement(Connection connection,
                                                   String sql,
                                                   boolean returnGeneratedKeys,
                                                   Object... values) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(sql,
        returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
    setValues(preparedStatement, values);
    return preparedStatement;
  }

  public static void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException {
    for (int i = 0; i < values.length; i++)
      preparedStatement.setObject(i + 1, values[i]);
  }

  public static void close(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        System.err.println("Closing Connection failed: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  public static void close(Statement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        System.err.println("Closing Statement failed: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  public static void close(ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        System.err.println("Closing ResultSet failed: " + e.getMessage());
        e.printStackTrace();
      }
    }
  }

  public static void close(Connection connection, Statement statement) {
    close(statement);
    close(connection);
  }
  public static void close(Connection connection, Statement statement1, Statement statement2) {
    close(statement1);
    close(statement2);
    close(connection);
  }

  public static void close(Connection connection, Statement statement, ResultSet resultSet) {
    close(resultSet);
    close(statement);
    close(connection);
  }

  public static void close(Connection connection, Statement statement1, Statement statement2, Statement statement3) {
    close(statement3);
    close(statement2);
    close(statement1);
    close(connection);
  }

  public static void close(Connection connection, Statement statement1, Statement statement2, Statement statement3, ResultSet resultSet) {
    close(resultSet);
    close(statement3);
    close(statement2);
    close(statement1);
    close(connection);
  }
}
