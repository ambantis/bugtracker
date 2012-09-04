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
    <title></title>
</head>
<body>

    <form method="post" action="processNewBug.do">
        <p>Enter a new bug with this form</p>
        <label for="summary">Summary</label>
        <textarea name="summary" id="summary" cols="80" maxlength="100"></textarea>
        <label for="description">Description</label>
        <textarea id="description" name="description" cols="80" maxlength="500"></textarea>
        <button type="submit">Submit</button>
    </form>
</body>
</html>

