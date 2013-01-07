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
</head>

<body>
<table>
  <thead>
  <tr>
    <th scope="col">BugID</th>
    <th scope="col">Due Date</th>
    <th scope="col">Close Date</th>
    <th scope="col">Assignee</th>
    <th scope="col">Priority</th>
    <th scope="col">Summary</th>
  </tr>
  </thead>

  <tbody>
  <c:forEach var="bug" items="${bugs}">
    <c:if test="${((sessionScope.user.roleId eq 'dev') && (sessionScope.user.userId eq bug.assignee)) ||
                               (sessionScope.user.roleId ne 'dev')}">
      <tr>
        <td><c:out value="${bug.bugId}"/></td>
        <td><c:out value="${bug.dueDate}"/></td>
        <td><c:out value="${bug.closeDate}"/></td>
        <td><c:out value="${bug.assignee}"/></td>
        <td><c:out value="${bug.priority}"/></td>
        <td><c:out value="${bug.summary}"/></td>
      </tr>
    </c:if>
  </c:forEach>
  </tbody>
</table>

<h2>Edit A Bug</h2>
<p>Enter bug id to update a bug</p>
<form method="get" action="displayBug.do">
  <label for="pick_id">Pick ID</label>
  <select name="pick_id" id="pick_id">
    <c:forEach var="bug" items="${bugs}">
      <option value="${bug.bugId}">BugID ${bug.bugId}</option>
    </c:forEach>
  </select>
  <button type="submit">Submit</button>
</form>

<c:if test="${sessionScope.user.roleId eq 'mgr'}">
  <h2>Close A Bug</h2>
  <p>Enter bug id to to close it</p>
  <form method="get" action="displayBug.do">
    <label for="close_this_bug">Pick ID</label>
    <select name="close_this_bug" id="close_this_bug">
      <c:forEach var="bug" items="${bugs}">
        <option value="${bug.bugId}">BugID ${bug.bugId}</option>
      </c:forEach>
    </select>
    <button type="submit">Close Bug</button>
  </form>
</c:if>

<p>
  <a href="welcome.do">Return to Welcome Page</a>
  <br>
</p>

</body>
</html>