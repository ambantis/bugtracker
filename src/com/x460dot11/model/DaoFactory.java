package com.x460dot11.model;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 11:09 AM
 */
public abstract class DaoFactory {
//  private String url;
//  private String userName;
//  private String password;

  private static DaoFactory daoFactory;

  public static DaoFactory getInstance() {
    if (daoFactory == null)
      daoFactory = DaoFactoryPostgres.getInstance();
    return daoFactory;
  }

  public static void init(String driver, String url, String userName, String password) throws DaoConfigurationException {
    if (daoFactory == null)
      DaoFactoryPostgres.init(driver, url, userName, password);
  }

  abstract Connection getConnection() throws DaoConfigurationException;

  public BugDao getBugDao() {
    return new BugDaoPostgres(this);
  }

  public UserDao getUserDao() {
    return new UserDaoPostgres(this);
  }


}
