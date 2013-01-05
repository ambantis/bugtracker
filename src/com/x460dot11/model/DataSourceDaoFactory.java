package com.x460dot11.model;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Alexandros Bantis
 * Date: 1/4/13
 * Time: 5:07 PM
 */
public class DataSourceDaoFactory extends DaoFactory {
  private DataSource dataSource;

  DataSourceDaoFactory(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}
