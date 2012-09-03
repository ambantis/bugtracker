<%@ page import="com.x460dot11.data.User" %>
<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 8/28/12
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Bug Tracker Application</title>
  </head>
  <body>
    <h3><c:out value="look ma, jstl works!"></c:out></h3>
    <jsp:useBean id="user" class="com.x460dot11.data.User" scope="session"/>
    You are logged in as: <jsp:getProperty name="user" property="username" />
    <br>
    ${user.role}
    <br>
    Your role is: <jsp:getProperty name="user" property="role"/>
    <br>



    <table>
        <c:forEach var="bug" items="${bugs}">
            <tr>
                <td>the bug id is <c:out value="${bug.bugID}"/> </td>
            </tr>
        </c:forEach>
    </table>


    <br>
    <h2>Click the link below to create a new bug</h2>
    <a href="/inputNewBug.do">New Bug</a>
    <a href="/displayAllBugs.do">Show All Bugs</a>
  </body>
</html>