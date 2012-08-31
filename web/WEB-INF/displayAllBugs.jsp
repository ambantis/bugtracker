<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 8/31/12
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
    <%@ page import="com.x460dot11.tracker.TransactionShowAllBugHeaders" %>
    <p>
    <%
        TransactionShowAllBugHeaders trans = new TransactionShowAllBugHeaders();
        out.print(trans.processShowAllBugHeadersRequest());
    %>
    </p>

    <p><a href="/welcome.do">Return to Welcome Page</a></p>
</body>
</html>