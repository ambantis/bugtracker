package com.x460dot11.servlet;

import com.x460dot11.data.Bug;
import com.x460dot11.data.Database;
import com.x460dot11.data.User;
// import com.x460dot11.mail.Gmail;

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
 */
public class Welcome extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // TODO:2012-09-05:ambantis:Use cookies to track session
        // TODO:2012-09-05:ambantis:End session after x minutes or when server goes down

        ArrayList<Bug> bugs = Database.getInstance().getBugList();
        session.setAttribute("bugs", bugs);
        session.setAttribute("bug", new Bug());

        String username;
        String role;
        username = request.getUserPrincipal().getName();
        if (request.isUserInRole("bug-qa"))
            role = "qa";
        else if (request.isUserInRole("bug-mngr"))
            role = "manager";
        else if (request.isUserInRole("bug-dev"))
            role = "developer";
        else
            return;
        User user = new User(username, role);
        session.setAttribute("user", user);
//        try {
//            Gmail.getInstance().sendTestMessage();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }


        // TODO:2012-09-05:ambantis:Implement page redirect based upon user role
        RequestDispatcher view = request.getRequestDispatcher("/welcome.do");
        view.forward(request, response);
    }
}
