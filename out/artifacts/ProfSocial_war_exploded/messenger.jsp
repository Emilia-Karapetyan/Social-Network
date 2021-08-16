<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="userManager.*" %>
<%@ page import="model.Comment" %>
<%@ page import="model.Post" %>
<%@ page import="model.Message" %><%--
  Created by IntelliJ IDEA.
  User: 99559
  Date: 01.07.2020
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messenger</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/messengerStyle.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css"
          rel="stylesheet">

</head>
<body>
<%
    UserManager userManager = new UserManager();
    List<User> allUser = userManager.getAllUser();
    User user = (User) session.getAttribute("user");
    FriendManager friendManager = new FriendManager();
    List<User> MyRequest = friendManager.MyrequestFriend(user.getId());
    PhotoManager photoManager = new PhotoManager();
    List<String> imageList = photoManager.getAllPictures(user.getId());
    List<User> myFriends = friendManager.myFriend(user.getId());
    List<User> myFriends1 = friendManager.myFriend1(user.getId());
    PostManager postManager = new PostManager();
    CommentManager commentManager = new CommentManager();
    LikeManager likeManager = new LikeManager();
    List<Comment> comments = commentManager.getComment();
    List<Post> posts = postManager.getPostByUserId(user.getId());
    List<User> list = userManager.getAllUser();
    List<User> requestList = friendManager.requestFriend(user.getId());
    List<Post> myFriendPosts = postManager.myFriendPost(user.getId());
    List<Post> myFriendPosts1 = postManager.myFriendPost1(user.getId());
    MessageManager messageManager = new MessageManager();


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

<input type="hidden" id="usId" value="<%=user.getId()%>">

<div class="container">
    <div class="messaging">
        <div class="inbox_msg">
            <div class="inbox_people">
                <div class="headind_srch">
                    <div class="recent_heading">
                        <h4>Recent</h4>
                    </div>
                    <div class="srch_bar">
                        <div class="stylish-input-group">
                            <input type="text" class="search-bar" placeholder="Search">
                            <span class="input-group-addon">
                <button type="button"> <i class="fa fa-search" aria-hidden="true"></i> </button>
                </span></div>
                    </div>
                </div>
                <div class="inbox_chat">
                    <%
                        if (!myFriends.isEmpty()) {
                            for (User myFriend : myFriends) {%>
                    <div class="chat_list active_chat">
                        <div class="chat_people">
                            <div class="chat_img">
                                <img src="/getAllPictures?j=<%=myFriend.getId()%>" alt="">
                            </div>
                            <div>
                                <button id="<%=myFriend.getId()%>" value="<%=myFriend.getId()%>" class="us">
                                    <%=myFriend.getName()%> <%=myFriend.getSurname()%>
                                </button>
                            </div>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                    <%
                        if (!myFriends1.isEmpty()) {
                            for (User myFriend : myFriends1) {%>
                    <div class="chat_list active_chat">
                        <div class="chat_people">
                            <div class="chat_img"><img src="/getAllPictures?j=<%=myFriend.getId()%>"
                                                       alt="">
                            </div>
                            <div style="display: inline-block;padding: 10px 25px">
                                <button id="<%=myFriend.getId()%>" value="<%=myFriend.getId()%>" class="us">
                                    <%=myFriend.getName()%> <%=myFriend.getSurname()%>
                                </button>
                            </div>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
            <div class="mesgs">
                <div class="msg_history history">

                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="script/messengerScript.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</html>
