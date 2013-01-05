package com.x460dot11.model;

import org.apache.taglibs.standard.lang.jstl.test.StaticFunctionTests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 11:27 AM
 */
public class DaoFactoryPostgres extends DaoFactory {
  private String url = null;
  private String userName = null;
  private String password = null;

  private static DaoFactoryPostgres daoFactoryPostgres = null;

  public static DaoFactoryPostgres getInstance(String url, String userName, String password) {
    if (daoFactoryPostgres == null)
      daoFactoryPostgres = new DaoFactoryPostgres(url, userName, password);
    return daoFactoryPostgres;
  }




  DaoFactoryPostgres(String url, String userName, String password) {
    this.url = url;
    this.userName = userName;
    this.password = password;
  }

  @Override
  Connection getConnection() throws SQLException {
    Connection connection = null;
    try {
      String url = "jdbc:postgresql://54.245.109.81:5432/bugdb";
      String user = "roach";
      String password = "motel";

      connection = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public BugDao getBugDao() {
    return null;
  }

  public UserDao getUserDao() {
    return new UserDaoPostgres(this);
  }

}
