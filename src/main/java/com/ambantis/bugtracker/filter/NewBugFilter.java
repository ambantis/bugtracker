package com.ambantis.bugtracker.filter;

import com.ambantis.bugtracker.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Alexandros Bantis
 * Date: 1/15/13
 * Time: 5:37 PM
 */
public class NewBugFilter implements Filter {
  FilterConfig filterConfig;

  public void init(FilterConfig config) throws ServletException {
    this.filterConfig = config;
  }


  public void destroy() {
  }

  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    User user = (User) request.getSession().getAttribute("user");

    if (user == null || !user.getRoleId().equals("qat")) {
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    chain.doFilter(req, resp);
    }


}
