package com.x460dot11.servlet;

import com.x460dot11.data.Bug;
import com.x460dot11.data.Database;
import com.x460dot11.data.User;
import org.joda.time.LocalDateTime;

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

        if (comment.length() > 0) {
            history = formatNewComment(history, comment, user.getUsername());
        }

        //TODO:2012-09-07:ambantis:Include edit bug fields is_open and final_result

        Bug bug = new Bug();
        bug.setBug_id(bug_id);
        bug.setDue_date(due_date);
        bug.setAssignee(assignee);
        bug.setPriority(priority);
        bug.setSummary(summary);
        bug.setHistory(history);

        try {
            Database.getInstance().updateBug(bug);
        } catch (SQLException e) {
            e.printStackTrace();
        }

            RequestDispatcher view = request.getRequestDispatcher("/welcome.do");
            view.forward(request, response);
    }

}
