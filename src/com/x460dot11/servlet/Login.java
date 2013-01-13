package com.x460dot11.servlet;

//import com.x460dot11.data.Bug;
//import com.x460dot11.data.Database;
//import com.x460dot11.data.User;

import com.x460dot11.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
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

//    ArrayList<Bug> bugs = Database.getInstance().getBugs();
//    ArrayList<Bug> bugs = (ArrayList<Bug>) DaoFactory.getInstance().getBugDao().list();
//    session.setAttribute("bugs", bugs);
//    session.setAttribute("bug", new Bug());

    String username;
    String role;
//    username = request.getUserPrincipal().getName();
//    if (request.isUserInRole("bug-qa"))
//      role = "qa";
//    else if (request.isUserInRole("bug-mngr"))
//      role = "manager";
//    else if (request.isUserInRole("bug-dev"))
//      role = "developer";
//    else
//      return;
    String userId = request.getParameter("username");
    String password = request.getParameter("password");
    User user = null;
    try {
      user = DaoFactory.getInstance().getUserDao().find(userId, password);
    } catch (DaoException e) {
      e.printStackTrace();
    }
    if (user == null) {
      RequestDispatcher view = request.getRequestDispatcher("error.html");
      view.forward(request, response);
      return;
    }




//    try {
////    user = Database.getInstance().validateUser(userId, password);
//      user = DaoFactory.getInstance().getUserDao().find(userId, password);
//      if (user == null) {
//
//      }
//    } catch (DaoException e) {
//      RequestDispatcher view = request.getRequestDispatcher("error.html");
//      view.forward(request, response);
//      return;
//    }

    session.setAttribute("user", user);
//    ArrayList<String> coders = Database.getInstance().getCoders();
    ArrayList<String> coders = DaoFactory.getInstance().getUserDao().developers();
    session.setAttribute("coders", coders);

    // TODO:2012-09-05:ambantis:Implement page redirect based upon user role
    RequestDispatcher view = request.getRequestDispatcher("listBugs.do");
    view.forward(request, response);
  }
}
