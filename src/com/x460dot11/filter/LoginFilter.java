package com.x460dot11.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Alexandros Bantis
 * Date: 12/30/12
 * Time: 8:45 PM
 *
 * http://brendangraetz.wordpress.com/2010/06/17/use-servlet-filters-for-user-authentication/
 */
public abstract class LoginFilter implements Filter {
  protected ServletContext servletContext;

  public void destroy() {
  }

  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    if (!isAuth()) {
      res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      // break filter chain, requested JSP/servlet will not be executed
    }

    // propagate to next element in the filter chain, ultimately JSP/servlet gets executed
    chain.doFilter(req, res);
  }

  protected abstract boolean isAuth();


  public void init(FilterConfig config) throws ServletException {
    servletContext = config.getServletContext();

  }

}
