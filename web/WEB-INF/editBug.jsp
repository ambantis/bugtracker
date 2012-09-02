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
    <style type="text/css">
        .noWrite {
            color: grey;
            background-color: #F0F0F0;
        }

    </style>
</head>
<body>

    <%! Bug bug; %>
    <% bug = new TransactionGetBug().doGetBug(request.getParameter("b_id")); %>


    <h2>Update Information and then press submit</h2>
    <form method="post" action="processEditBug.do">
        <label for="bug_id">Bug ID</label>
        <input class="noWrite"
               id="bug_id"
               name="bug_id"
               value="<% out.print(bug.getBugID()); %>"
               readonly="readonly">
        <br>
        <label for="due_date">Due Date</label>
        <input id="due_date"
               name="due_date"
               value="<% out.print(bug.getDueDate()); %>">
        <br>
        <label for="assignee">Assignee</label>
        <input id="assignee"
               name="assignee"
               value="<% out.print(bug.getAssignee()); %>">
        <br>
        <label for="priority">Priority</label>
        <input id="priority"
               name="priority"
               value="<% out.print(bug.getPriority()); %>">
        <br>
        <label class="noWrite" for="summary">Summary</label>
        <textarea class="noWrite" id="summary" name="summary" readonly="readonly"><% out.print(bug.getSummary()); %></textarea>
        <br>
        <label for="description">Description</label>
        <textarea class="noWrite" id="description" name="description" readonly="readonly"><% out.print(bug.getDescription()); %></textarea>
        <br>
        <label for="new_comment">New Comment</label>
        <textarea id="new_comment" name="new_comment"></textarea>
        <br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>