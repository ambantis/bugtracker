package com.x460dot11.servlet;

import com.x460dot11.data.Bug;
import com.x460dot11.data.Database;
import com.x460dot11.data.User;
import com.x460dot11.exception.LostUpdateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.x460dot11.util.Converter.formatNewComment;

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
        String history = request.getParameter("history");
        String comment = request.getParameter("new_comment");
        User user = (User) request.getSession().getAttribute("user");
        Bug v1bug = (Bug) request.getSession().getAttribute("bug");

        if (comment.length() > 0) {
            history = formatNewComment(history, comment, user.getUsername());
        }

        //TODO:2012-09-07:ambantis:Include edit bug fields is_open and final_result

        Bug v2bug = new Bug();
        v2bug.setBug_id(bug_id);
        v2bug.setDue_date(due_date);
        v2bug.setAssignee(assignee);
        v2bug.setPriority(priority);
        v2bug.setSummary(summary);
        v2bug.setHistory(history);

        try {
            Database.getInstance().updateBug(v1bug, v2bug);
        } catch (LostUpdateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

            RequestDispatcher view = request.getRequestDispatcher("/welcome.do");
            view.forward(request, response);
    }

}
