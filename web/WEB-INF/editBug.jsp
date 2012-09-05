<%--
  Created by IntelliJ IDEA.
  User: Alexandros Bantis
  Date: 8/31/12
  Time: 2:40 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit A Bug</title>
    <script type="text/javascript"
            src="shared/js/modernizr.com/Modernizr-2.5.3.forms.js">
    </script>
    <script type="text/javascript" data-webforms2-support="date,validation"
            data-lang="en" src="shared/js/html5Forms.js">
    </script>

    <style type="text/css">
        .noWrite {
            color: grey;
            background-color: #F0F0F0;
        }
    </style>
</head>
<body>
    <h2>Update Information and then press submit</h2>
    <form method="post" action="processEditBug.do">
        <label for="bug_id">Bug ID</label>
        <input class="noWrite"
               id="bug_id"
               name="bug_id"
               value="${v2bug.bugID}"
               readonly="readonly">
        <br>
        <label for="due_date">Due Date</label>
        <input id="due_date"
               name="due_date"
               type="date"
               value="${v2bug.dueDate}">
        <br>
        <label for="assignee">Assignee</label>
        <input id="assignee"
               name="assignee"
               value="${v2bug.assignee}">
        <br>
        <label for="priority">Priority (from 1-10, 10=highest</label>
        <input id="priority"
               name="priority"
               required="required"
               pattern="[1-9]|10?"
               type="number"
               value="${v2bug.priority}">
        <br>
        <label class="noWrite" for="summary">Summary</label>
        <textarea class="noWrite" id="summary" name="summary" readonly="readonly">${v2bug.summary}</textarea>
        <br>
        <label for="description">Description</label>
        <textarea class="noWrite" id="description" name="description" readonly="readonly">${v2bug.description}</textarea>
        <br>
        <label for="new_comment">New Comment</label>
        <textarea id="new_comment" name="new_comment"></textarea>
        <br>
        <label for="final_result">Final Result</label>
        <textarea class="noWrite" id="final_result" name="final_result" readonly="readonly"></textarea>
        <br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>