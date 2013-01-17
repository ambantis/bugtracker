package com.ambantis.bugtracker.servlet;

//import com.ambantis.data.Bug;
//import com.ambantis.data.Database;
//import com.ambantis.data.User;

import com.ambantis.bugtracker.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ambantis.bugtracker.util.Converter.formatNewComment;

/**
 * User: Alexandros Bantis
 * Date: 9/1/12
 * Time: 1:10 PM
 *
 * This servlet creates a new Bug in the database with Bug summary and comment.
 */
public class ProcessNewBug extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Bug bug = new Bug();
    User user = (User) request.getSession().getAttribute("user");
    String history = "";
    String summary = request.getParameter("summary");
    String comment = request.getParameter("comment");
    comment = formatNewComment(history, comment, user.getUserId());

    bug.setBugId(0);
    bug.setSummary(summary);
    bug.setHistory(comment);
    try {
      DaoFactory.getInstance().getBugDao().create(bug, user);
//      Database.getInstance().addBug(summary, comment, user);
//    } catch (SQLException e) {
      } catch (DaoException e) {
      e.printStackTrace();
    } catch (DaoConnectionException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }

    RequestDispatcher view = request.getRequestDispatcher("/listBugs.do");
    view.forward(request, response);
  }
}
