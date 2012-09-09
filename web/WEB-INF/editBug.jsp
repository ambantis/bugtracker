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

    <%--TODO:2012-09-05:ambantis:Replace html5Forms with jQuery--%>
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
    <%--TODO:2012-09-05:ambantis:Add checkbox to close bug--%>
</head>
<body>
    <h2>Update Information and then press submit</h2>
    <form method="post" action="processEditBug.do">
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
               value="${v2bug.due_date}">
        <br>

        <label for="assignee">Coder</label>
        <select name="assignee" id="assignee">
            <option value="${v2bug.assignee}" selected>${v2bug.assignee}</option>
                <c:forEach var="coder" items="${coders}">
                    <c:if test="${coder != v2bug.assignee}">
                        <option value="${coder}">${coder}
                    </c:if>
                </option>
            </c:forEach>
        </select>

        <br>
        <label for="priority">Priority (from 1-10, 10=highest)</label>
        <input id="priority"
               name="priority"
               required="required"
               pattern="[1-9]|10?"
               type="number"
               value="${v2bug.priority}">
        <br>
        <table>
        <tr><td><label class="noWrite" for="summary">Summary</label></td></tr>
        <tr><td><textarea class="noWrite" id="summary" name="summary" cols="80" readonly="readonly">${v2bug.summary}</textarea></td></tr>
        <tr><td><label for="history">Description</label></td></tr>
        <tr><td><textarea class="noWrite" id="history" name="history" cols="80" readonly="readonly">${v2bug.history}</textarea></td></tr>
        <tr><td><label for="new_comment">New Comment</label></td></tr>
        <tr><td><textarea id="new_comment" name="new_comment" cols="80"></textarea></td></tr>
        <tr><td><label for="final_result">Final Result</label></td></tr>
        <tr><td><textarea class="noWrite" id="final_result" name="final_result"  cols="80" readonly="readonly">${v2bug.final_result}</textarea></td></tr>
        </table>
        <br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>