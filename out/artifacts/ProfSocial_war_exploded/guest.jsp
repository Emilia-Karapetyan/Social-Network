<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="servlet.Guest" %>
<%@ page import="servlet.GuestServlet" %>
<%@ page import="javafx.geometry.Pos" %>
<%@ page import="model.Post" %>
<%@ page import="model.Comment" %>
<%@ page import="method.Controller" %>
<%@ page import="userManager.*" %>
<%@ page import="model.Message" %><%--
  Created by IntelliJ IDEA.
  User: 99559
  Date: 21.06.2020
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guest</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css"
          rel="stylesheet">
    <link rel="stylesheet" href="css/guestStyle.css">

</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    UserManager userManager = new UserManager();
    FriendManager friendManager = new FriendManager();
    PostManager postManager = new PostManager();
    List<User> friends = friendManager.myFriend(user.getId());
    List<User> friends1 = friendManager.myFriend1(user.getId());
    List<User> list = userManager.getAllUser();
    List<User> requestList = friendManager.requestFriend(user.getId());
    List<User> MyRequest = friendManager.MyrequestFriend(user.getId());
    User guest = GuestServlet.temp;
    List<Post> guestPost = postManager.getPostByUserId(guest.getId());
    CommentManager commentManager = new CommentManager();
    List<Comment> comments = commentManager.getComment();
    MessageManager messageManager = new MessageManager();
    List<Message> ms = messageManager.printMessage(user.getId(), guest.getId());

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
            <%--    <li>
                    <input class="form-control mr-sm-2" type="text" id="search" placeholder="Search">
                </li>--%>
        </ul>
    </nav>
</header>
<input type="hidden" id="usName" value="<%=user.getName()%>">
<input type="hidden" id="toId" value="<%=guest.getId()%>">
<input type="hidden" id="usSurname" value="<%=user.getSurname()%>">
<input type="hidden" id="usId" value="<%=user.getId()%>">
<input type="hidden" id="usPicUrl" value="<%=user.getPicURL()%>">
<div class="d">
    <img src="/getAllPictures?j=<%=guest.getId()%>" width="200px" alt="">
    <h5><%=guest.getName()%> <%=guest.getSurname()%>
    </h5>
    <%
        if (friends.contains(guest) || friends1.contains(guest)) {%>
    <a href="/messengerServlet" class="btn btn-warning">Messenger</a>
    <br>
    <%
    } else if (requestList.contains(guest)) {%>
    <div class="spinner-border text-primary"></div>
    <a href="/deleteGuestRequest?id=<%=guest.getId()%>" class="btn btn-danger">Delete</a>
    <%
    } else {
    %>
    <form action="/guestRequest">
        <button class="btn btn-dark" name="id" value="<%=guest.getId()%>">Add Friend</button>
    </form>
    <%
        }
    %>
</div>
<div class="guestPost">
    <%
        for (Post post : guestPost) {%>
    <div class="container">

        <div class="st">
            <p class="t"><%=post.getDescription()%>
            </p>
            <img src="/getPostPic?pic=<%=post.getPicURL()%>" class="im"><br>
            <button type="button" aria-expanded="false" class="collapsed" data-toggle="collapse"
                    data-target="#H<%=post.getId()%>" style="border: none">
                <img src="buttonImg/comment.png" width="35px" alt="">
            </button>
            <button class="i" value="<%=post.getId()%>"><img src="/buttonImg/like.png" class="like" width="20px"
                                                             height="30px" alt=""></button>
            <p class="getLike">
            </p>
        </div>
        <%-- <%
          /*   for (Post post1 : myFriendPosts) {*/
         %>--%>
        <div class="str">
            <div id="H<%=post.getId()%>" class="collapse">
                <div class="chat">
                    <%
                        for (Comment comment : comments) {
                            if (comment.getPost_id() == post.getId()) {
                                // User temp1=commentManager.getUserByPost(post.getId());
                                User temp = Controller.getCommentWriter(comment.getUser_id());
                    %>
                    <a href="/guestServlet?id=<%=temp.getId()%>"><img src="/getAllPictures?j=<%=temp.getId()%>"
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
                    <textarea class="m" name="comment" cols="23" rows="1" placeholder="Type message"></textarea>
                    <button class="btn1" name="id" value="<%=post.getId()%>"><img src="buttonImg/send.png"
                                                                                  width="30px"
                                                                                  alt=""></button>
                    <%-- </form>--%>
                </div>
            </div>
        </div>
        <%-- <%
             }
         %>--%>
    </div>
    <%
        }
    %>


</div>
<div id="searchBody" class="hide in">

</div>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="script/guest.js"></script>
</html>
