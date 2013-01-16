<%--
  Created by IntelliJ IDEA.
  User: ambantis
  Date: 1/12/13
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="navbar">
  <div class="navbar-inner">
    <a class="brand" href="#">BugTracker</a>
    <ul class="nav">
      <li class="divider-vertical"></li>
      <li class="active"><a href="#">Home</a></li>
      <li><a href="#">Contact Us</a></li>
    </ul>
    <form class="navbar-search">
      <input type="text" class="search-query" placeholder="Search">
    </form>

    <ul class="nav pull-right">
      <li>
        <p class="navbar-text pull-right">Logged in as
          <a href="#" class="navbar-link">${sessionScope.user.userId}</a>
        </p>
      </li>
      <li class="divider-vertical"></li>
      <li>
        <form class="navbar-form" action="logout.do">
          <button class="btn">Log out</button>
        </form>
      </li>
    </ul>
  </div>
</header>