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
  <title></title>
</head>
<body>
<h3>
  Welcome <c:out value="${user.username}"/>!!!
  You are logged in as a <c:out value="${user.role}"/>
</h3>

<p>
  <c:if test="${user.role eq 'qa'}">
    <a href="inputNewBug.do">Make A Bug</a>
    <br>
  </c:if>
  <a href="displayAllBugs.do">Display All Bugs</a>
  <br>
  <a href="invalidate.do">Exit Bug Tracking</a>
</p>
<%--
  <% 
if(request.getAttribute("userRole")=="qa"){
	out.println("<a href='inputNewBug.do'>Click to enter new Bug</a><br>");} 
else {
	out.println("<a href='displayAllBugs.do'>Click to display all Bugs</a><br>");}
%>

 <%@ include file="footer.jsp" %>
 --%>
</body>
</html>