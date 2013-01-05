package com.x460dot11.model;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 11:09 AM
 */
public abstract class DaoFactory {
  private static DaoFactory daoFactory;

  public static DaoFactory getInstance(String url, String userName, String password) throws DaoConfigurationException {
    if (daoFactory == null)
      daoFactory = DaoFactoryPostgres.getInstance(url, userName, password);
    return daoFactory;
  }

  abstract Connection getConnection() throws SQLException;

  public BugDao getBugDao() {
    return new BugDaoPostgres(this);
  }

  public UserDao getUserDao() {
    return new UserDaoPostgres(this);
  }


}
