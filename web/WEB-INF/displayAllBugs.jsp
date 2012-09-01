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
    <style type="text/css">
        p.show_bugs { font-family: monospace; }
    </style>
</head>
<body>
    <%@ page import="com.x460dot11.transaction.TransactionShowAllBugHeaders" %>
    <p class="show_bugs">
    <%
        TransactionShowAllBugHeaders trans = new TransactionShowAllBugHeaders();
        out.print(trans.processShowAllBugHeadersRequest());
    %>
    </p>

    <br>
    <br>

    <h2>Edit A Bug</h2>
    <p>Enter bug id to update a bug</p>
    <form method="get" action="editBug.do">
        <label for="b_id">Bug ID Number</label>
        <input name="b_id" id="b_id">
        <button type="submit">Submit</button>
    </form>



    <p>
        <a href="/welcome.do">Return to Welcome Page</a>
        <br>
    </p>

</body>
</html>