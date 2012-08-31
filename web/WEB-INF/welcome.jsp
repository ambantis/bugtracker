<%@ page import="com.x460dot11.tracker.User" %>
<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 8/28/12
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Bug Tracker Application</title>
  </head>
  <body>
    <p>
        The Request body username parameter is
        <%= request.getParameter("username")%>
        <br>
        The request body password is
        <%= request.getParameter("password")%>
        <br>
        The request body role is
        <%= request.getParameter("role") %>
        <%
            User user = (User) session.getAttribute("user");
            out.println("The session user is " + user.getUsername());
            out.println("The session role is " + user.getRole());
            out.println("The session id is " + session.getId());
            out.println("Is the session new?: " + session.isNew());
        %>
    </p>

    <h2>Click the link below to create a new bug</h2>
    <a href="/inputNewBug.do">New Bug</a>
  </body>
</html>