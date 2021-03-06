package com.ambantis.bugtracker.servlet;

import com.ambantis.bugtracker.exception.DaoConnectionException;
import com.ambantis.bugtracker.exception.DaoException;
import com.ambantis.bugtracker.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: Alexandros Bantis
 * Date: 8/31/12
 * Time: 7:39 AM
 *
 * This servlet redirects to role-specific page based on user role input.  
 *
 */
public class Login extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession();

    String userId = request.getParameter("username");
    String password = request.getParameter("password");
    User user = null;
    try {
      user = DaoFactory.getInstance().getUserDao().find(userId, password);
    } catch (DaoException e) {
      e.printStackTrace();
    } catch (DaoConnectionException e) {
      e.printStackTrace();
    }
    if (user == null) {
      RequestDispatcher view = request.getRequestDispatcher("error.html");
      view.forward(request, response);
      return;
    }

    session.setAttribute("user", user);
    ArrayList<String> coders = null;
    try {
      coders = DaoFactory.getInstance().getUserDao().developers();
    } catch (DaoConnectionException e) {
      e.printStackTrace();
    }
    session.setAttribute("coders", coders);

    // TODO:2012-09-05:ambantis:Implement page redirect based upon user role
    RequestDispatcher view = request.getRequestDispatcher("listBugs.do");
    view.forward(request, response);
  }
}
