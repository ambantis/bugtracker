package com.ambantis.btracker.listener;

//import com.ambantis.data.Database;
import com.ambantis.btracker.model.DaoException;
import com.ambantis.btracker.model.DaoFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
  @Override
  /**
   * Start monitoring the application and database connection
   *
   */

  public void contextInitialized(ServletContextEvent event) {
    ServletContext servletContext = event.getServletContext();
//  Connection connection;
    String driver = servletContext.getInitParameter("db-driver");
    String url = servletContext.getInitParameter("db-url");
    String user = servletContext.getInitParameter("db-user");
    String password = servletContext.getInitParameter("db-password");



    try {
      DaoFactory.init(driver, url, user, password);
      DaoFactory dao = DaoFactory.getInstance();
      servletContext.setAttribute("db", dao);


//      Class.forName(driver);
//      connection = DriverManager.getConnection(url, user, password);
        //DaoFactory.init(driver, url, user, password);
//      Database.getInstance().init(connection);
//    } catch (ClassNotFoundException e) {
//      e.printStackTrace();
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
    } catch (DaoException e) {
      e.printStackTrace();
    }
  }


  /**
   * Close database connection
   */
  public void contextDestroyed(ServletContextEvent event) {

//    DaoFactory dao = (DaoFactory) event.getServletContext().getAttribute("db");
//    if (dao != null)

//    Connection connection = (Connection) event.getServletContext().getAttribute("connection");


//    try {
//      connection.close();
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
  }
}
