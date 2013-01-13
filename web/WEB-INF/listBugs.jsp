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
  <link rel="stylesheet" type="text/css" href="../shared/css/bootstrap.min.css" media="all">
  <link rel="stylesheet" type="text/css" href="../shared/css/DT_bootstrap.css" media="all">
</head>

<body>
  <div class="container-fluid">
    <jsp:include page="/WEB-INF/template/header.jsp"/>

    <div class="row-fluid">
      <div class="span2">
        <jsp:include page="/WEB-INF/template/navigation.jsp"/>
      </div>

      <div class="span10">
        <h2>Here is your open bug list</h2>
        <br>
        <br>
        <table class="table table-striped table-bordered" id="bug-list">
          <thead>
            <tr>
              <th>Bug ID</th>
              <th>Assignee</th>
              <th>Due Date</th>
              <th>Close Date</th>
              <th>Summary</th>
            </tr>
          </thead>
          <tbody>
            <c:set var="bugs" value="${applicationScope.db.bugDao.list()}"/>
            <c:set var="user" value="${sessionScope.user}"/>
            <c:forEach var="bug" items="${bugs}">
              <c:if test="${((user.roleId eq 'dev') && (user.userId eq bug.assignee)) || (user.roleId ne 'dev')}">
                <tr>
                  <th><a href="displayBug.do?pick_id=${bug.bugId}"><c:out value="${bug.bugId}"/></a></th>
                  <th><c:out value="${bug.assignee}"/></th>
                  <th>
                    <c:choose>
                      <c:when test="${bug.dueDate eq '1970-01-01'}">
                        n/a
                      </c:when>
                      <c:otherwise>
                        <c:out value="${bug.dueDate}"/>
                      </c:otherwise>
                    </c:choose>
                  </th>

                  <th>
                    <c:choose>
                      <c:when test="${bug.closeDate eq '1970-01-01'}">
                        n/a
                      </c:when>
                      <c:otherwise>
                        <c:out value="${bug.closeDate}"/>
                      </c:otherwise>
                    </c:choose>
                  </th>
                  <th><c:out value="${bug.summary}"/></th>
                </tr>
              </c:if>
            </c:forEach>
          </tbody>
        </table>
        <br>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="../shared/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="../shared/js/jquery.dataTables.min.js"></script>
  <script type="text/javascript" src="../shared/js/DT_bootstrap.js"></script>
</body>
</html>