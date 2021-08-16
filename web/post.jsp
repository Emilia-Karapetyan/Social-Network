<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Post" %>
<%@ page import="model.Comment" %>
<%@ page import="userManager.*" %>
<%@ page import="method.Controller" %><%--
  Created by IntelliJ IDEA.
  User: 99559
  Date: 22.06.2020
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Status</title>
    <link rel="stylesheet" href="css/postStyle.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    UserManager userManager = new UserManager();
    FriendManager friendManager = new FriendManager();
    PostManager postManager = new PostManager();
    CommentManager commentManager = new CommentManager();
    LikeManager likeManager = new LikeManager();
    List<Comment> comments = commentManager.getComment();
    List<Post> posts = postManager.getPostByUserId(user.getId());
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
            <li>
                <input class="form-control mr-sm-2" type="text"  id="search" placeholder="Search">
            </li>
        </ul>
    </nav>
</header>
<input type="hidden" id="usName" value="<%=user.getName()%>">
<input type="hidden" id="usSurname" value="<%=user.getSurname()%>">
<input type="hidden" id="usId" value="<%=user.getId()%>">
<input type="hidden" id="usPicUrl" value="<%=user.getPicURL()%>">
<h4>Your Posts</h4>
<div class="post">
    <form action="/addPostServlet" method="post" enctype="multipart/form-data">
        <textarea name="text"></textarea><br>
        <input type="file" name="im" required>
        <input type="submit" disabled name="addNew" value="Add Post">
    </form>
</div>
<%--<div class="container" style="float: left">--%>
<div class="main">
    <%
        for (Post post : posts) {

    %>
    <div class="container" id="m" style="float: left" >
        <div class="st">
            <p class="t"><%=post.getDescription()%>
            </p>
            <a href="/deletePost?id=<%=post.getId()%>" class="x">X</a>
            <img src="/getPostPic?pic=<%=post.getPicURL()%>" class="im">
            <button type="button" aria-expanded="false" class="collapsed" data-toggle="collapse"
                    data-target="#H<%=post.getId()%>" style="border: none">
                <img src="buttonImg/comment.png" width="35px" alt="">
            </button>
            <button class="i" value="<%=post.getId()%>"><img src="/buttonImg/like.png" width="30px" alt=""></button>
            <p class="getLike">
        </p>
        </div>
        <div class="str">
            <div id="H<%=post.getId()%>" class="collapse">
                <div class="chat">
                    <%
                        for (Comment comment : comments) {
                            if (comment.getPost_id() == post.getId()) {
                                User temp = Controller.getCommentWriter(comment.getUser_id());
                    %>
                    <a href="/guestServlet?id=<%=comment.getUser_id()%>"><img src="/getAllPictures?j=<%=temp.getId()%>"
                                                                              width="20px"
                                                                              alt=""><%=temp.getName()%> <%=temp.getSurname()%>
                    </a>
                    <p id="<%=post.getId()%>" class="usMsg"><%=comment.getComment()%>
                    </p>
                    <%
                            }
                        }
                    %>
                </div>
                <div>
                    <%--<form action="/addComment" method="post">--%>
                    <textarea class="m" name="comment" cols="18" rows="1" placeholder="Type message" ></textarea>
                        <button class="btn1" name="id" value="<%=post.getId()%>"><img src="buttonImg/send.png" width="30px" alt=""></button>
                    <%-- </form>--%>
                </div>
            </div>
        </div>
    </div>
    <%
        }
    %>
</div>
<div id="searchBody" class="hide in">

</div>
</body>

<script src="script/post.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</html>
