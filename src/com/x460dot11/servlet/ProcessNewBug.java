package com.x460dot11.servlet;

//import com.x460dot11.data.Bug;
//import com.x460dot11.data.Database;
//import com.x460dot11.data.User;

import com.x460dot11.model.Bug;
import com.x460dot11.model.DaoException;
import com.x460dot11.model.User;
import com.x460dot11.model.DaoFactory;

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

    bug.setSummary(summary);
    bug.setHistory(comment);
    try {
      DaoFactory.getInstance().getBugDao().create(bug, user);
//      Database.getInstance().addBug(summary, comment, user);
//    } catch (SQLException e) {
      } catch (DaoException e) {
      e.printStackTrace();
    }

    RequestDispatcher view = request.getRequestDispatcher("/welcome.do");
    view.forward(request, response);
  }
}
