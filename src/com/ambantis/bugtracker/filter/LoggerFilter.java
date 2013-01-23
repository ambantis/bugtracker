package com.ambantis.bugtracker.filter;

import org.apache.log4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: Alexandros Bantis
 * Date: 1/21/13
 * Time: 2:45 PM
 * http://www.ibm.com/developerworks/java/library/j-logging/
 */
public class LoggerFilter implements Filter {
  public void destroy() {
  }

  public void doFilter(ServletRequest req,
                       ServletResponse resp,
                       FilterChain chain) throws ServletException, IOException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    HttpSession session = request.getSession();

    // Put the username into the diagnostic context.
    // Use %X{username} in the layout pattern to include this information.
    MDC.put("username", session.getAttribute("username"));

    // Continue processing the request of the filter chain.
    chain.doFilter(request, response);

    // Remove the username from the diagnostic context
    MDC.remove("username");
  }

  public void init(FilterConfig config) throws ServletException {

  }

}
