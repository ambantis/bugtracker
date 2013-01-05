package com.x460dot11.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 4:53 PM
 */
public class DriverManagerDaoFactory extends DaoFactory {
  private String url;
  private String userName;
  private String password;

  DriverManagerDaoFactory(String url, String userName, String password) {
    this.url = url;
    this.userName = userName;
    this.password = password;
  }

  @Override
  Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, userName, password);
  }
}
