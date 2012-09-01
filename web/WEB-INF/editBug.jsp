<%@ page import="com.x460dot11.transaction.TransactionGetBug" %>
<%@ page import="com.x460dot11.data.Bug" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandros Bantis
  Date: 8/31/12
  Time: 2:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit A Bug</title>
</head>
<body>

    <%! Bug bug = new Bug(); %>
    <%bug = new TransactionGetBug().doGetBug(request.getParameter("b_id")); %>
    <%=  request.getParameter("b_id") %>

    <h2>Update Information and then press submit</h2>
    <form method="post" action="changeBug.do">
        <label for="bug_id">Bug ID</label>
        <input id="bug_id" name="bug_id" value="<%= bug.getBugID()%>" disabled="disabled">
        <br>
        <label for="due_date">Due Date</label>
        <input id="due_date" name="due_date" value="<%= bug.getDueDate()%>">
        <br>
        <label for="assignee">Assignee</label>
        <input id="assignee" name="assignee" value="<%= bug.getAssignee() %>">
        <br>
        <label for="priority">Priority</label>
        <input id="priority" name="priority" value="<%= bug.getPriority()%>">
        <br>
        <label for="summary">Summary</label>
        <textarea id="summary" name="summary" disabled="disabled">
            <%= bug.getSummary()%>
        </textarea>
        <br>
        <label for="description">Description</label>
        <textarea id="description" name="description" disabled="disabled">
            <%= bug.getDescription() %>
        </textarea>
        <br>
        <label for="new_comment">New Comment</label>
        <textarea id="new_comment" name="new_comment"></textarea>
        <br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>