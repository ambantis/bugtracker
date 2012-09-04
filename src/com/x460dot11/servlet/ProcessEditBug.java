package com.x460dot11.servlet;

import com.x460dot11.data.Bug;
import com.x460dot11.data.Database;
import com.x460dot11.transaction.TransactionEditBug;

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
public class ProcessEditBug extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // TODO edit bug process needs to lock record to prevent concurrent edits.

        int bug_id = Integer.parseInt(request.getParameter("bug_id"));
        String due_date = request.getParameter("due_date");
        String assignee = request.getParameter("assignee");
        int priority = Integer.parseInt(request.getParameter("priority"));
        String summary = request.getParameter("summary");
        String description = request.getParameter("description") + " " +
                request.getParameter("new_comment");
        String final_result = "";

        Bug bug = new Bug(bug_id, priority, due_date, assignee, summary, description, final_result);
        try {
            Database.getInstance().updateBug(bug);
        } catch (SQLException e) {
            e.printStackTrace();
        }

            RequestDispatcher view = request.getRequestDispatcher("/welcome.do");
            view.forward(request, response);
    }
}
