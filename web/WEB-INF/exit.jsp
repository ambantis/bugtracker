<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 9/9/12
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>
  Thanks <c:out value="${user.username}"/>!!!
  You are now logged off!!
</h3>
<jsp:include page="footer.jsp"/>
</body>
</html>