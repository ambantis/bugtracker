package com.x460dot11.servlet;

import com.x460dot11.data.Bug;
import com.x460dot11.data.Database;
import com.x460dot11.data.User;
import org.joda.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.x460dot11.util.Converter.formatNewComment;


public class ProcessCloseBug extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    User user;
    Bug v1bug;
    Bug v2bug;

    try {
      int bug_id = Integer.parseInt(request.getParameter("bug_id"));
      String date;
      LocalDate due_date = ((date = request.getParameter("due_date")) == null)
          ? null : LocalDate.parse(date);
      String assignee = request.getParameter("assignee");
      int priority = Integer.parseInt(request.getParameter("priority"));
      String summary = request.getParameter("summary");
      String history = request.getParameter("history");
      String final_result = request.getParameter("final_result");

      user = (User) request.getSession().getAttribute("user");
      v1bug = (Bug) request.getSession().getAttribute("bug");

      //TODO:2012-09-09:ambantis:get rid of closeDate from ProcessEditBug

      v2bug = new Bug();
      v2bug.setBug_id(bug_id);
      v2bug.setDue_date(due_date);
      v2bug.setAssignee(assignee);
      v2bug.setPriority(priority);
      v2bug.setSummary(summary);
      v2bug.setHistory(history);
      v2bug.setFinal_result(final_result);

      Database.getInstance().closeBug(v1bug, v2bug, user);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    RequestDispatcher view = request.getRequestDispatcher("/welcome.do");
    view.forward(request, response);

  }

}
