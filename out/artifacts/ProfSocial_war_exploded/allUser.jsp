<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="userManager.UserManager" %>
<%@ page import="userManager.FriendManager" %><%--
  Created by IntelliJ IDEA.
  User: 99559
  Date: 21.06.2020
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/allUserStyle.css">
</head>
<body>

<%
    User user = (User) session.getAttribute("user");
    UserManager userManager = new UserManager();
    FriendManager friendManager = new FriendManager();
    List<User> friends = friendManager.myFriend(user.getId());
    List<User> friends1 = friendManager.myFriend1(user.getId());
    List<User> list = userManager.getAllUser();
    List<User> requestList = friendManager.requestFriend(user.getId());
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

        </ul>
    </nav>
</header>
<div>
    <%
        for (User user1 : list) {
            if (user1.getId() != user.getId()) {
    %>
    <div class="im">
        <img src="/getAllPictures?j=<%=user1.getId()%>" id="image" alt="">
        <h6><%=user1.getName()%> <%=user1.getSurname()%>
        </h6>
        <p>Age:<%=user1.getAge()%>
        </p>
        <%
            if (friends.contains(user1) || friends1.contains(user1)) {%>
        <a href="/messengerServlet" class="btn btn-warning">Message</a>
        <%
        }else if (requestList.contains(user1)) {%>
        <div class="spinner-border text-primary"></div>
        <a href="/deleteRequest?id=<%=user1.getId()%>" class="btn btn-danger">Delete</a>
        <%
        } else {
        %>
        <form action="/sendRequest">
            <button class="btn btn-dark" name="id" value="<%=user1.getId()%>">Add Friend</button>
        </form>
        <%
            }
        %>
    </div>
    <%
            }
        }
    %>
</div>

</body>
</html>
