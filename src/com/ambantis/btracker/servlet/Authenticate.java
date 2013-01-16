package com.ambantis.btracker.servlet;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;


public class Authenticate implements javax.servlet.Filter {
  public void destroy() {
  }

  public void doFilter(javax.servlet.ServletRequest request,
                       javax.servlet.ServletResponse response,
                       javax.servlet.FilterChain chain)
      throws javax.servlet.ServletException, IOException {

    PrintWriter out = response.getWriter();

    String password = request.getParameter("password");
    if (password.equals("admin")) {
      chain.doFilter(request, response); // sends request to next resource
    } else
      out.print("username or password error");
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
    requestDispatcher.include(request, response);
  }

  public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {}

}
