package com.x460dot11.servlet;

import com.x460dot11.data.Bug;
import com.x460dot11.data.Database;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * User: Alexandros Bantis
 * Date: 9/1/12
 * Time: 1:10 PM
 */
public class ProcessNewBug extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bug bug = new Bug();
        String summary = request.getParameter("summary");
        String description = request.getParameter("description");
        bug.setSummary(summary);
        bug.setDescription(description);
        try {
            Database.getInstance().addBug(summary, description);
        } catch (SQLException e) {
            e.printStackTrace();
        }

            RequestDispatcher view = request.getRequestDispatcher("/welcome.do");
            view.forward(request, response);
    }
}
