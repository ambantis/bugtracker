<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
  <meta charset="UTF-8">
  <title>Bug Tracker</title>
  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" >
</head>
<body>

  <!-- The body section, which should be moved to another template -->
  <header class="navbar">
    <div class="navbar-inner">
      <div class="container-fluid">
        <a class="brand active" href="#">Bug Tracker</a>
        <ul class="nav">
          <li class="active"><a href="#">Getting Started</a></li>
          <li><a href="#">Demo</a></li>
          <li><a href="#">Pricing</a></li>
        </ul>

        <ul class="nav pull-right">
          <li>
            <!-- Button to trigger modal -->
            <a id="loginModalButton" href="#loginModal" data-toggle="modal">Sign In</a>
          </li>
          <li class="divider-vertical"></li>
          <li><a href="#newUserModal" data-toggle="modal">Sign Up</a></li>
        </ul> <!-- /navbarItems -->

        <!-- Login Modal -->
        <div class="modal hide fade" id="loginModal" tabindex="-1" role="dialog"
             aria-labelledby="loginModalLabel" aria-hidden="true">
          <div class="modal-header">
            <button class="close" type="button" aria-hidden="true"></button>
            <h2 id="loginModalLabel">Login</h2>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" action="login.do" method="post">
              <div class="control-group">
                <label class="control-label" for="username">Username</label>
                <div class="controls">
                  <input id="username" type="text" name="username" placeholder="username">
                </div>
              </div>
              <div class="control-group">
                <label class="control-label" for="password">Password</label>
                <div class="controls">
                  <input type="password" id="password" name="password" placeholder="Password">
                </div>
              </div>

              <div class="control-group modal-footer">
                <div class="controls">
                  <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                  <button type="submit" class="btn btn-primary">Sign In</button>
                </div>
              </div>
            </form>
          </div>
        </div> <!-- /loginModal -->


        <!-- Sign Up Modal -->
        <div class="modal hide face" id="newUserModal" tabindex="-1" role="dialog"
            aria-labelledby="signUpModalLabel" aria-hidden="true">
          <div class="modal-header">
            <button type="button" class="close"data-dismiss="modal" aria-hidden="true">x</button>
            <h3 id="signUpModalLabel">Sign Up!</h3>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" action="#" method="#">
              <div class="control-group">
                <label class="control-label" for="newUsername">Username</label>
                <div class="controls">
                  <input type="text" id="newUsername" name="newUsername" placeholder="Username">
                </div>
              </div>
              <div class="control-group">
                <label for="newEmail">Email</label>
                <div class="controls">
                  <input type="email" id="newEmail" name="newEmail" placeholder="Email">
                </div>
              </div>
              <div class="control-group">
                <label for="newPassword1">Password</label>
                <div class="controls">
                  <input type="password" id="newPassword1" name="newPassword1" placeholder="Password">
                </div>
              </div>
              <div class="control-group">
                <label for="newPassword2">Re-enter Password</label>
                <div class="controls">
                  <input type="password" id="newPassword2" name="newPassword2" placeholder="Re-enter Password">
                </div>
              </div>
              <div class="control-group modal-footer">
                <div class="control">
                  <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                  <button class="btn btn-primary" type="submit">Sign Up!</button>
                </div>
              </div>
            </form>
          </div>
        </div> <!-- /signUpModal -->
      </div> <!-- /container-fluid -->
    </div> <!-- /navbar-inner -->
  </header>

  <div class="row-fluid">
    <div class="span10 offset1">
      <div class="hero-unit">
        <h1>Bugtracker is the best thing since sliced bread!</h1>
        <p>
          You've come to the right place to learn how Bug Tracker zap all the hassles of software development.
        </p>
        <p>
          <a class="btn btn-primary btn-large" href="#">Learn more &raquo;</a>
        </p>
      </div>
    </div>
  </div>

  <div class="row-fluid">
    <div class="span3 offset1">
      <h2>Testimonials</h2>
      <p>lots of testimonials here.</p>
    </div>
    <div class="span3 offset1">
      <h2>Features</h2>
      <p>lots of features here.</p>
    </div>
    <div class="span3 offset1">
      <h2>Benefits</h2>
      <p>lots of benefits here.</p>
    </div>
  </div>

  <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
  <script type="text/javascript" src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js"></script>
</body>
</html>