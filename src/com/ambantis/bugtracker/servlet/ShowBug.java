package com.ambantis.bugtracker.servlet;

//import com.ambantis.data.Bug;
//import com.ambantis.data.Database;
import com.ambantis.bugtracker.model.Bug;
import com.ambantis.bugtracker.model.DaoConnectionException;
import com.ambantis.bugtracker.model.DaoFactory;
import com.ambantis.bugtracker.model.DaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 * User: Alexandros Bantis
 * Date: 9/3/12
 * Time: 2:55 PM
 *
 * This servlet gets the Bug ID input from user and retrieve the Bug object associated
 * with Bug ID from the database, then forward to JSP page for editing.
 */
public class ShowBug extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher view;
    boolean closeBug;
    int id;
    String close = request.getParameter("close_this_bug");
    if (close == null || close.equals("")) {
      id = Integer.parseInt(request.getParameter("pick_id"));
      closeBug = false;
    } else {
      id = Integer.parseInt(request.getParameter("close_this_bug"));
      closeBug = true;
    }

    Bug v2Bug = null;
    try {
//      v2Bug = Database.getInstance().getBug(id);
        v2Bug = DaoFactory.getInstance().getBugDao().find(id);
    } catch (DaoException e) {
      e.printStackTrace();
    } catch (DaoConnectionException e) {
      e.printStackTrace();
    }
    request.getSession().setAttribute("bug", v2Bug);
    request.setAttribute("v2bug", v2Bug);

    if (closeBug)
      view = request.getRequestDispatcher("closeBug.do");
    else
      view = request.getRequestDispatcher("showBug.do");
    view.forward(request, response);
  }
}
