package com.x460dot11.listener;

import com.x460dot11.data.Database;
import com.x460dot11.mail.Gmail;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContextListener implements ServletContextListener {
  @Override
  /**
   * Start monitoring the application and database connection
   *
   */

  public void contextInitialized(ServletContextEvent event) {
    ServletContext servletContext = event.getServletContext();
    Connection connection;
    String driver = servletContext.getInitParameter("db-driver");
    String url = servletContext.getInitParameter("db-url");
    String user = servletContext.getInitParameter("db-user");
    String password = servletContext.getInitParameter("db-password");
//        ArrayList<Bug> bugs;

    try {
      Class.forName(driver);
      connection = DriverManager.getConnection(url, user, password);
      Database.getInstance().init(connection);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }

//        String smtpAuth = servletContext.getInitParameter("mail.smtp.auth");
//        String smtpStartTls = servletContext.getInitParameter("mail.smtp.starttls.enable");
//        String smtpHost = servletContext.getInitParameter("mail.smtp.host");
//        String smtpPort = servletContext.getInitParameter("mail.smtp.port");
    Gmail.getInstance().init();
  }

  @Override

  /**
   * Close database connection
   */
  public void contextDestroyed(ServletContextEvent event) {

    Connection connection = (Connection) event.getServletContext().getAttribute("connection");
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
