package com.ambantis.bugtracker.listener;

import com.ambantis.bugtracker.model.DaoConfigurationException;
import com.ambantis.bugtracker.model.DaoConnectionException;
import com.ambantis.bugtracker.model.DaoFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ContextListener implements ServletContextListener {
  static Logger logger = null;

  public void contextInitialized(ServletContextEvent event) {
    ServletContext servletContext = event.getServletContext();
    initLog4j(servletContext);
    logger.debug("Entering application");
    initBugDb(servletContext);
  }

  public void contextDestroyed(ServletContextEvent event) {}

  private void initLog4j(ServletContext servletContext) {
    String prefix =  servletContext.getRealPath("/");
    String file = servletContext.getInitParameter("log4j-properties-file");
    if (file != null) {
      PropertyConfigurator.configure(prefix + file);
    } else {
      System.err.println("*** No log4j-properties-file found, so initializing log4j with BasicConfigurator");
      BasicConfigurator.configure();
    }
    logger = Logger.getRootLogger();
  }

  private void initBugDb(ServletContext servletContext) {
    String driver = servletContext.getInitParameter("db-driver");
    String url = servletContext.getInitParameter("db-url");
    String user = servletContext.getInitParameter("db-user");
    String password = servletContext.getInitParameter("db-password");

    try {
      logger.info("Attempting to configure database");
      DaoFactory.init(driver, url, user, password);
      DaoFactory dao = DaoFactory.getInstance();
      servletContext.setAttribute("db", dao);
    } catch (DaoConfigurationException e) {
      logger.error("Unable to configure database", e);
      System.err.println("Unable to connect to database");
    } catch (DaoConnectionException e) {
      logger.error("Unable to connect to database", e);
    }
  }

}
