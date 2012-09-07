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
public class ProcessEditBug extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        int bug_id = Integer.parseInt(request.getParameter("bug_id"));
        String due_date = request.getParameter("due_date");
        String assignee = request.getParameter("assignee");
        int priority = Integer.parseInt(request.getParameter("priority"));
        String summary = request.getParameter("summary");
        String prior_history = request.getParameter("history");
        String new_history = request.getParameter("new_comment");
        StringBuilder history = new StringBuilder();
        history.append(prior_history);
        history.append("\n\nNEW ENTRY: ");
        history.append(new_history);

        //TODO:2012-09-07:ambantis:Include edit bug fields is_open and final_result

        Bug bug = new Bug();
        bug.setBug_id(bug_id);
        bug.setDue_date(due_date);
        bug.setAssignee(assignee);
        bug.setPriority(priority);
        bug.setSummary(summary);
        bug.setHistory(history.toString());

        try {
            Database.getInstance().updateBug(bug);
        } catch (SQLException e) {
            e.printStackTrace();
        }

            RequestDispatcher view = request.getRequestDispatcher("/welcome.do");
            view.forward(request, response);
    }
}
