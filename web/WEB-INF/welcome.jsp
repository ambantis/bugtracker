<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 8/28/12
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ include file="header.jsp" %>


    <h3>
        Welcome <c:out value="${user.username}"/>!!!
        You are logged in as a <c:out value="${user.role}"/>
    </h3>

  <% 
if(request.getAttribute("userRole")=="qa"){
	out.println("<a href='inputNewBug.do'>Click to enter new Bug</a><br>");} 
else {
	out.println("<a href='displayAllBugs.do'>Click to display all Bugs</a><br>");}
%>
 
 <%@ include file="footer.jsp" %>