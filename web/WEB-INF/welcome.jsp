<%@ page import="com.x460dot11.data.User" %>
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
        <%
            User user = (User) session.getAttribute("user");
            out.println("The session user is " + user.getUsername());
            out.print("<br>");
            out.println("The session role is " + user.getRole());
            out.print("<br>");
            out.println("The session id is " + session.getId());
            out.print("<br>");
            out.println("Is the session new?: " + session.isNew());
        %>
    </p>

    <h2>Click the link below to create a new bug</h2>
    <a href="/inputNewBug.do">New Bug</a>
    <a href="/displayAllBugs.do">Show All Bugs</a>
  </body>
</html>