package com.x460dot11.servlet;

//import com.x460dot11.data.Bug;
//import com.x460dot11.data.Database;
//import com.x460dot11.data.User;
//import com.x460dot11.exception.LostUpdateException;

import com.x460dot11.model.DaoException;
import com.x460dot11.model.DaoFactory;
import com.x460dot11.model.User;
import com.x460dot11.model.Bug;

import org.joda.time.LocalDate;

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
 *
 * This servlet updates database with new Bug data entered by user.
 */
public class ProcessEditBug extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    User user;
    Bug v1bug;
    Bug v2bug;

    try {
      int bug_id = Integer.parseInt(request.getParameter("bug_id"));
      String date;
      LocalDate due_date = ((date = request.getParameter("due_date")) == null)
          ? new LocalDate() : LocalDate.parse(date);
      LocalDate close_date = ((date = request.getParameter("close_date")) == null)
          ? new LocalDate("1970-01-01") : LocalDate.parse(date);
      String assignee = request.getParameter("assignee");
      int priority = Integer.parseInt(request.getParameter("priority"));
      String summary = request.getParameter("summary");
      String history = request.getParameter("history");
      String comment = request.getParameter("new_comment");
      String final_result = request.getParameter("final_result");
      user = (User) request.getSession().getAttribute("user");
      v1bug = (Bug) request.getSession().getAttribute("bug");

      if (comment.length() > 0) {
        history = formatNewComment(history, comment, user.getUserId());
      }

      v2bug = new Bug();
//      v2bug.setBug_id(bug_id);
//      v2bug.setDue_date(due_date);
//      v2bug.setClose_date(close_date);
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
//      Database.getInstance().updateBug(v1bug, v2bug, user);

      //TODO:2012-09-07:ambantis:Include edit bug fields close_date and final_result

    } catch (IllegalArgumentException e) {
      // this is in the case of an illegal format exception for parsing the dates.
      e.printStackTrace();
//    } catch (LostUpdateException e) {
      } catch (DaoException e) {
      e.printStackTrace();
//    } catch (SQLException e) {
//      e.printStackTrace();
    }
    RequestDispatcher view = request.getRequestDispatcher("/listBugs.do");
    view.forward(request, response);
  }
}
