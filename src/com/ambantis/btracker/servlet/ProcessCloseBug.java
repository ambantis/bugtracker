package com.ambantis.btracker.servlet;

//import com.ambantis.data.Bug;
//import com.ambantis.data.Database;
//import com.ambantis.data.User;

import com.ambantis.btracker.model.DaoException;
import com.ambantis.btracker.model.Bug;
import com.ambantis.btracker.model.DaoFactory;
import com.ambantis.btracker.model.User;


import org.joda.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ProcessCloseBug extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    User user;
    Bug v1bug;
    Bug v2bug;

    try {
      int bug_id = Integer.parseInt(request.getParameter("bug_id"));
      LocalDate due_date = new LocalDate("1970-01-01");
      LocalDate close_date = new LocalDate();
      String assignee = request.getParameter("assignee");
      int priority = Integer.parseInt(request.getParameter("priority"));
      String summary = request.getParameter("summary");
      String history = request.getParameter("history");
      String final_result = request.getParameter("final_result");

      user = (User) request.getSession().getAttribute("user");
      v1bug = (Bug) request.getSession().getAttribute("bug");

      //TODO:2012-09-09:ambantis:get rid of closeDate from ProcessEditBug

      v2bug = new Bug();
//      v2bug.setBug_id(bug_id);
//      v2bug.setDue_date(due_date);
      v2bug.setBugId(bug_id);
      v2bug.setDueDate(due_date);
      v2bug.setCloseDate(close_date);
      v2bug.setAssignee(assignee);
      v2bug.setPriority(priority);
      v2bug.setSummary(summary);
      v2bug.setHistory(history);
//    v2bug.setFinal_result(final_result);
      v2bug.setFinalResult(final_result);

      DaoFactory.getInstance().getBugDao().update(v1bug, v2bug, user);
//      Database.getInstance().closeBug(v1bug, v2bug, user);

    } catch (DaoException e) {
      e.printStackTrace();
    }
    RequestDispatcher view = request.getRequestDispatcher("/listBugs.do");
    view.forward(request, response);

  }

}
