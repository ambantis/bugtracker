<%--
  Created by IntelliJ IDEA.
  User: Alexandros Bantis
  Date: 8/29/12
  Time: 7:14 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.x460dot11.transaction.TransactionNewBug" %>
<% new TransactionNewBug(request.getParameter("summary"), request.getParameter("description")); %>

<html>
<head>
    <title></title>
</head>
<body>
</body>
</html>