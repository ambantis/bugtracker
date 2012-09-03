package com.x460dot11.servlet;

import com.x460dot11.data.Bug;
import com.x460dot11.data.Hibernator;
import com.x460dot11.data.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * User: Alexandros Bantis
 * Date: 8/31/12
 * Time: 7:39 AM
 */
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username;
        String role;

        username = request.getUserPrincipal().getName();
        if (request.isUserInRole("bug-qa"))
            role = "qa";
        else if (request.isUserInRole("bug-mngr"))
            role = "manager";
        else if (request.isUserInRole("bug-dev"))
            role = "developer";
        else
            return;

        User user = new User(username, role);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        try {
            ArrayList<Bug> bugs = Hibernator.initializeBugList(connection);
            session.setAttribute("bugs", bugs);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher view = request.getRequestDispatcher("/welcome.do");
        view.forward(request, response);
    }
}
