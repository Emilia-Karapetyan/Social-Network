<%@ page import="userManager.FriendManager" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 99559
  Date: 21.06.2020
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Request</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/reqStyle.css">
</head>
<body>
<%
    User user= (User) session.getAttribute("user");
    FriendManager friendManager=new FriendManager();
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
         <%--   <li>
                <input class="form-control mr-sm-2" type="text"  id="search" placeholder="Search">
            </li>--%>
        </ul>
    </nav>
</header>


<%for (User user1 : MyRequest) {%>
    <div class="im">
        <img src="/getAllPictures?j=<%=user1.getId()%>" id="image" alt="">
        <h6><%=user1.getName()%> <%=user1.getSurname()%>
        </h6>
        <p>Age:<%=user1.getAge()%>
        </p>
        <a href="/accept?id=<%=user1.getId()%>" class="btn btn-success">Accept</a>
        <a href="/ignor?id=<%=user1.getId()%>" class="btn btn-danger">Ignor</a>
    </div>
<%
    }

%>
<div id="searchBody" class="hide in">

</div>
<%--<div id="searchBody" class="hide in">

</div>--%>
</body>
<script>
    let searchBody=document.querySelector("#searchBody");
    searchBody.classList.remove('in');
    document.querySelector("#search").oninput=function () {
        searchBody.innerHTML="";
        let val=this.value.trim();
        if(val!=""){
            searchBody.classList.remove('hide');
            searchBody.classList.add('in');
            $.ajax({
                url:'searchUser',
                data:{
                    str:val
                },success : function (user) {
                    let str = user.split(",")
                    for (var i = 0; i < str.length; i++) {

                        var s = str[i];
                        let arr = s.split("/");
                        if (arr[0] != undefined && arr[1] != undefined) {
                            let us = document.createElement("a");
                            let img = document.createElement("img");
                            var br = document.createElement("br");
                            us.style.padding = 5;
                            us.style.color = "white";
                            img.style.paddingBottom = 5;

                            img.src = "/getAllPictures?j=" + arr[1];
                            img.width = 35;
                            us.href = "/guestServlet?id=" + arr[1];
                            us.innerHTML = arr[0];
                            let p=us.innerText;
                            if(us.innerText.toLowerCase().includes(val.toLowerCase())) {
                                us.innerHTML = insertMark(p, p.toLowerCase().indexOf(val.toLowerCase()), val.length);
                                searchBody.append(img, us, br);
                            }
                        }
                    }
                }
            })

        }else{
            searchBody.classList.remove('in');
            searchBody.classList.add('hide');
            searchBody.innerHTML="";
        }
    }
    function insertMark(str,pos,len) {
        return str.slice(0,pos)+'<mark>'+str.slice(pos,pos+len)+'</mark>'+str.slice(pos+len)
    }
</script>
</html>
