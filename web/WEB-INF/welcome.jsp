<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 8/28/12
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Bug Tracker Application</title>
  </head>
  <body>
    <h3>
        Welcome <c:out value="${user.username}"/>
        You are logged in as a <c:out value="${user.role}"/>
    </h3>

    <br>
    <h2>Click the link below to create a new bug</h2>
    <a href="inputNewBug.do">New Bug</a>
    <a href="displayAllBugs.do">Show All Bugs</a>
  </body>
</html>