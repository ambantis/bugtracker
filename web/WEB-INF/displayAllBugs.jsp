<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 8/31/12
  Time: 9:24 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Display All Bugs</title>
    <style type="text/css">
        p.show_bugs { font-family: monospace; }
    </style>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th scope="col">BugID</th>
                <th scope="col">Priority</th>
                <th scope="col">Due Date</th>
                <th scope="col">Assignee</th>
                <th scope="col">Summary</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="bug" items="${bugs}">
                <tr>
                    <td><c:out value="${bug.bugID}"/></td>
                    <td><c:out value="${bug.priority}"/></td>
                    <td><c:out value="${bug.dueDate}"/></td>
                    <td><c:out value="${bug.assignee}"/></td>
                    <td><c:out value="${bug.summary}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

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