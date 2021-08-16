<%@ page import="userManager.PhotoManager" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.File" %>
<%@ page import="userManager.FriendManager" %><%--
  Created by IntelliJ IDEA.
  User: 99559
  Date: 17.06.2020
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Pictures</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/myPicStyle.css">
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    PhotoManager photoManager = new PhotoManager();
    List<String> imageList = photoManager.getAllPictures(user.getId());
    FriendManager friendManager = new FriendManager();
    List<User> MyRequest = friendManager.MyrequestFriend(user.getId());

%>
<header>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Brand/logo -->
        <a class="navbar-brand" href="/userPage">
            <img src="/buttonImg/home.png" alt="logo" style="width:40px;">
        </a>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/myPicturesServlet">My Pictures</a>
            </li>
            <li class="nav-item">
                <%if (!MyRequest.isEmpty()) {%>
                <a class="nav-link" href="/myRequestServlet">My Request <img src="buttonImg/notification.png" alt=""
                                                                             width="30px" class="notify"></a>

                <%} else {%>
                <a class="nav-link" href="/myRequestServlet">My Request</a>
                <%}%>

            </li>
            <li class="nav-item">
                <a class="nav-link" href="/myFriendServlet">My Friend</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/allUserServlet">All User</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/postServlet">My Post</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/messengerServlet">Messenger</a>
            </li>
            <li>
                <input class="form-control mr-sm-2" type="text" id="search" placeholder="Search">
            </li>
        </ul>
    </nav>
</header>
<div class="k">
    <form action="/addPictures" method="post" enctype="multipart/form-data">
        <input type="file" name="filee">
        <input type="submit" disabled name="addNew" value="Add new Photo">
    </form>
</div>
<br>
<div class="l" style="position: absolute;display: inline-block;">
    <%
        for (String i : imageList) {%>
    <div class="im" id="<%=i%>">
        <form action="/deleteMyPic">
            <button class="x" name="delPic" value="<%=i%>">X</button>
        </form>
        <img src="/getNewPic?i=<%=i%>" alt="" id="image"><br>
        <form action="/profilePic">
            <button class="btn btn-success" name="name" value="<%=i%>">Profile Pictures</button>
        </form>
    </div>
    <%
        }
    %>
</div>

<div id="searchBody" class="hide in">

</div>
</body>

<script src="script/myPicScript.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</html>
