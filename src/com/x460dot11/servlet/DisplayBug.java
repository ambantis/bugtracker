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
 * Date: 9/3/12
 * Time: 2:55 PM
 */
public class DisplayBug extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("pick_id"));
        Bug v2Bug = null;
        try {
            v2Bug = Database.getInstance().getBug(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("bug", v2Bug);
        request.setAttribute("v2bug", v2Bug);

        RequestDispatcher view = request.getRequestDispatcher("editBug.do");
        view.forward(request, response);

    }
}
