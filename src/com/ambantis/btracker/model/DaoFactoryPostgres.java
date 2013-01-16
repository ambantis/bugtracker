package com.ambantis.btracker.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 11:27 AM
 */
public class DaoFactoryPostgres extends DaoFactory {
  private String driver = null;
  private String url = null;
  private String userName = null;
  private String password = null;

  private static DaoFactoryPostgres daoFactoryPostgres = null;
  private DaoFactoryPostgres() {}

  public static DaoFactoryPostgres getInstance() {
    if (daoFactoryPostgres == null)
      throw new DaoConfigurationException("Database has not been initialized.");
    return daoFactoryPostgres;
  }

  public static void init(String driver, String url, String userName, String password) {
    if (daoFactoryPostgres != null) {
      throw new DaoConfigurationException("Database has already been initialized.");
    } else {
      daoFactoryPostgres = new DaoFactoryPostgres();
      daoFactoryPostgres.driver = driver;
      daoFactoryPostgres.url = url;
      daoFactoryPostgres.userName = userName;
      daoFactoryPostgres.password = password;
    }
  }

  @Override
  Connection getConnection() throws DaoConfigurationException {
    Connection connection;
    try {
      Class.forName(driver);
      connection = DriverManager.getConnection(url, userName, password);
    } catch (SQLException e) {
      throw new DaoConfigurationException(e);
    } catch (ClassNotFoundException e) {
      throw new DaoConfigurationException(e);
    }
    return connection;
  }

  public BugDao getBugDao() {
    return new BugDaoPostgres(this);
  }

  public UserDao getUserDao() {
    return new UserDaoPostgres(this);
  }

}
