<%--
  Created by IntelliJ IDEA.
  User: 99559
  Date: 16.06.2020
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>ProfSocial</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style.css">

  </head>

  <body>
  <div id="login">
    <h3 class="text-center text-white pt-5">Login</h3>
    <div class="container">
      <div id="login-row" class="row justify-content-center align-items-center">
        <div id="login-column" class="col-md-6">
          <div id="login-box" class="col-md-12">
            <form action="/singIn" method="post" id="login-form" class="form">
              <div class="form-group">
                <label >Email:</label>
                <input type="text" class="form-control" name="email" required class="form-control">
              </div>
              <div class="form-group">
                <label >Password:</label>
                <input type="password" class="form-control" name="password" required  class="form-control">
              </div>
              <button class="btn btn-primary" type="submit">Login</button>
              <a href="/register">Register</a>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  </body>
</html>
