package com.ambantis.bugtracker.model;

import com.ambantis.bugtracker.exception.DaoConfigurationException;
import com.ambantis.bugtracker.exception.DaoConnectionException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 11:27 AM
 */
public class DaoFactoryPostgres extends DaoFactory {

  private static Logger logger = Logger.getLogger(DaoFactoryPostgres.class);

  private String driver = null;
  private String url = null;
  private String userName = null;
  private String password = null;

  private static DaoFactoryPostgres daoFactoryPostgres = null;
  private DaoFactoryPostgres() {}

  public static DaoFactoryPostgres getInstance() {
    if (daoFactoryPostgres == null) {
      logger.error("cannot get instance of database as it has not yet been initialized");
      throw new DaoConfigurationException("Database has not been initialized.");
    }
    return daoFactoryPostgres;
  }

  public static void init(String driver, String url, String userName, String password)
      throws DaoConfigurationException, DaoConnectionException {
    if (daoFactoryPostgres != null) {
      logger.error("unable to initialize database as it has already been initialized");
      throw new DaoConfigurationException("Database has already been initialized.");
    } else {
      daoFactoryPostgres = new DaoFactoryPostgres();
      daoFactoryPostgres.driver = driver;
      daoFactoryPostgres.url = url;
      daoFactoryPostgres.userName = userName;
      daoFactoryPostgres.password = password;
    }
    try {
      Connection connection = getInstance().getConnection();
      connection.close();
    } catch (SQLException e) {
      logger.error("unable to initialize database", e);
      throw new DaoConnectionException(e);
    }
  }

  @Override
  Connection getConnection() throws DaoConnectionException {
    Connection connection;
    try {
      Class.forName(driver);
      connection = DriverManager.getConnection(url, userName, password);
    } catch (SQLException e) {
      logger.error("unable to connect to database", e);
      throw new DaoConnectionException(e);
    } catch (ClassNotFoundException e) {
      logger.error("unable to connect to database", e);
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
