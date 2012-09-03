package com.x460dot11.listener;

import com.x460dot11.data.Bug;
import com.x460dot11.data.Hibernator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Alexandros Bantis
 * Date: 8/31/12
 * Time: 6:38 AM
 */
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        Connection connection = null;
        String driver = servletContext.getInitParameter("db-driver");
        String url = servletContext.getInitParameter("db-url");
        String user = servletContext.getInitParameter("db-user");
        String password = servletContext.getInitParameter("db-password");
//        ArrayList<Bug> bugs;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            servletContext.setAttribute("connection", connection);
//            bugs = Hibernator.initializeBugList(connection);
//            Bug bug = bugs.get(0);
//            servletContext.setAttribute("bug", bug);
//            servletContext.setAttribute("bugs", bugs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        Connection connection = (Connection) event.getServletContext().getAttribute("connection");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
