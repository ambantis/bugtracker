<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 8/29/12
  Time: 5:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Bug</title>
</head>
<body>
    <%--TODO:2012-09-05:ambantis:Change NewBug textarea fields to require input--%>
    <form method="post" action="processNewBug.do">
        <p>Enter a new bug with this form</p>
        <table>
        <tr><td><label for="summary">Summary</label></td></tr>
        <tr><td><textarea name="summary" id="summary" cols="80" maxlength="100"></textarea></td></tr>
        <tr><td><label for="description">Description</label></td></tr>
        <tr><td><textarea id="description" name="description" cols="80" maxlength="500"></textarea></td></tr>
        </table>
        <button type="submit">Submit</button>
    </form>
</body>
</html>

