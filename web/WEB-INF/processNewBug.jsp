<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 8/29/12
  Time: 7:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.x460dot11.tracker.TransactionNewBug" %>
<%-- new TransactionNewBug("the summary", "the description"); --%>
<% new TransactionNewBug(request.getParameter("summary"), request.getParameter("description")); %>

<html>
<head>
    <title>Let's take a look at the request body</title>
</head>
<body>
    <%= request.getRequestURI() %>
    <%= request.getRequestURL() %>
    <br>
    <%= request.getParameter("summary")%>
    <%= request.getParameter("description")%>


</body>
</html>