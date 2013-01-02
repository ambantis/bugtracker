<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 9/9/12
  Time: 7:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Close A Bug</title>

</head>
<body>
<h2>Update Information and then press submit</h2>

<form method="post" action="processCloseBug.do">
<label for="bug_id">Bug ID</label>
<input class="noWrite"
       id="bug_id"
       name="bug_id"
       value="${v2bug.bug_id}"
       readonly="readonly">
<br>
<label for="due_date">Due Date</label>
<input id="due_date"
       name="due_date"
       type="date"
       value="${v2bug.due_date}"
       class="noWrite"
       readonly="readonly">
<br>
<label for="assignee">Coder</label>
<input id="assignee"
       name="assignee"
       value="${v2bug.assignee}"
       class="noWrite"
       readonly="readonly">
<br>
<label for="priority">Priority (from 1-10, 10=highest)</label>
<input id="priority"
       name="priority"
       type="number"
       value="${v2bug.priority}"
       class="noWrite"
       readonly="readonly">
<br>
<table>
  <tr><td><label for="summary">Summary</label></td></tr>
  <tr><td><textarea class="noWrite" id="summary" name="summary" cols="80" readonly="readonly">${v2bug.summary}</textarea></td></tr>
  <tr><td><label for="history">Description</label></td></tr>
  <tr><td><textarea class="noWrite" id="history" name="history" cols="80" readonly="readonly">${v2bug.history}</textarea></td></tr>
  <tr><td><label for="final_result">Final Result</label></td></tr>
  <tr><td><textarea id="final_result" name="final_result"  cols="80">${v2bug.final_result}</textarea></td></tr>
</table>
<br>
<button type="submit">Close Bug</button>
</form>








</body>
</html>