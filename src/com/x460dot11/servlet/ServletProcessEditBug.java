package com.x460dot11.servlet;

import com.x460dot11.data.Bug;
import com.x460dot11.transaction.TransactionEditBug;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Alexandros Bantis
 * Date: 9/1/12
 * Time: 1:10 PM
 */
public class ServletProcessEditBug extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // TODO edit bug process needs to lock record to prevent concurrent edits.

        String bug_id = request.getParameter("bug_id");
        String due_date = request.getParameter("due_date");
        String assignee = request.getParameter("assignee");
        String priority = request.getParameter("priority");
        String summary = request.getParameter("summary");
        String description = request.getParameter("description") + " " +
                request.getParameter("new_comment");

        Bug bug = new Bug(bug_id, priority, due_date, assignee, summary, description);
        TransactionEditBug trans = new TransactionEditBug();
        boolean success = trans.doEditBug(bug);

        if (success == true) {
            RequestDispatcher view = request.getRequestDispatcher("/displayAllBugs.do");
            view.forward(request, response);
        } else {
            // TODO error logic needs to redirect to error page if update fails
        }

    }

}
