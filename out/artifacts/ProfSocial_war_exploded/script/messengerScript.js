let us = document.querySelectorAll(".us");
let myId = document.querySelector("#usId")
let msgHistory = document.querySelector(".msg_history");
let usId = document.querySelector("#usId");
let msgSend_btn = document.querySelector(".msg_send_btn");
let write_msg = document.querySelectorAll(".write_msg");
let frId = 0;
let y=0;
let socket = new WebSocket("ws://localhost:8080/server");
for (var i = 0; i < us.length; i++) {
    us[i].addEventListener("click", showHistory);
}

function showHistory() {
    msgHistory.innerHTML = "";
    let friendId = 0;
    friendId = $(this).val();
    let id = myId.value;
    msgHistory.id=id;
    $.ajax({
        url: "getMessage",
        data: {
            fromId: id,
            toId: friendId
        }, success: function (messages) {
            let str = messages.split(":");
            for (var i = 0; i < str.length; i++) {
                let s = str[i].split(",")
                let mes = s[0];
                let mesDate = s[1];
                y=mesDate;
                let mesId = s[2];
                if (mesId === friendId) {
                    let inc = document.createElement("div");
                    inc.className = "incoming_msg";
                    let usImgDiv = document.createElement("div");
                    usImgDiv.className = "incoming_msg_img";
                    let usImg = document.createElement("img");
                    usImg.src = "/getAllPictures?j=" + friendId;
                    usImgDiv.append(usImg);
                    let msg = document.createElement("div");
                    msg.className = "received_msg";
                    let msg1 = document.createElement("div");
                    msg1.className = "received_withd_msg";
                    let sms = document.createElement("p");
                    sms.innerHTML = mes;
                    let d = document.createElement("span");
                    d.className = "time_date";
                    let time = new Date();
                    d.innerHTML = time.getHours() + ":" + time.getMinutes() + " | " + mesDate;
                    msg1.append(sms, d);
                    msg.append(msg1);
                    inc.append(usImgDiv, msg);
                    msgHistory.append(inc);
                } else if (mesId === id) {
                    let div = document.createElement("div");
                    div.className = "outgoing_msg";
                    let sentMsg = document.createElement("div");
                    sentMsg.className = "sent_msg";
                    let p = document.createElement("p");
                    p.className = "m";
                    p.innerHTML = mes;
                    let span = document.createElement("span");
                    span.className = "time_date";
                    let time = new Date();
                    span.innerHTML = time.getHours() + ":" + time.getMinutes() + " | " + mesDate;
                    sentMsg.append(p, span);
                    div.append(sentMsg);
                    msgHistory.append(div);
                }
            }
        }
    })

    let typeMsg = document.createElement("div");
    typeMsg.className = "type_msg hid";
    let inputMsg = document.createElement("div");
    inputMsg.className = "input_msg_write";
    let t = document.createElement("input");
    t.type = "text";
    t.className = "write_msg";
    t.placeholder = "Type a message";
    t.id = friendId;
    let but = document.createElement("button");
    but.className = "msg_send_btn";
    but.type = "button";
    let v = document.createElement("i");
    v.className = "fa fa-paper-plane-o";
    but.append(v);
    but.value = friendId;
    but.addEventListener("click", addMessage);
    inputMsg.append(t, but);
    typeMsg.append(inputMsg);
    msgHistory.append(typeMsg);
    msgHistory.classList.remove('history');


}

function addMessage() {
    let id = $(this).val();
    frId = id;
    let id1 = myId.value;
    let w = document.querySelectorAll(".write_msg");
    let text = "";

    for (var i = 0; i < w.length; i++) {
        if (w[i].id === id) {
            text = w[i].value;
            w[i].value = null;
            break;
        }
    }

    if (text !== "") {
        $.ajax({
            url: 'addMessage',
            data: {
                friendId: id,
                from_id: id1,
                mes: text
            }, success: function () {
                sendMessage(text+","+id+","+id1);
            }
        })
    }
}

function sendMessage(text) {
    socket.send(text);
}

socket.onmessage = function processMessage(message) {
    let jsonData = JSON.parse(message.data);
    let s = jsonData.message;
    let c=document.querySelectorAll(".msg_history")
    let arr=s.split(",");
    if(msgHistory.id===arr[2]){
        let div = document.createElement("div");
        div.className = "outgoing_msg";
        let sentMsg = document.createElement("div");
        sentMsg.className = "sent_msg";
        let p = document.createElement("p");
        p.className = "m";
        p.innerHTML = arr[0];
        let span = document.createElement("span");
        span.className = "time_date";
        let time = new Date();
        span.innerHTML = time.getHours() + ":" + time.getMinutes() + " | "+time.getFullYear()+"-"+time.getMonth()+"-"+time.getDay();
        sentMsg.append(p, span);
        div.append(sentMsg);
        msgHistory.append(div);
    }else if(msgHistory.id===arr[1]){
        let inc = document.createElement("div");
        inc.className = "incoming_msg";
        let usImgDiv = document.createElement("div");
        usImgDiv.className = "incoming_msg_img";
        let usImg = document.createElement("img");
        usImg.src = "/getAllPictures?j=" + arr[2];
        usImgDiv.append(usImg);
        let msg = document.createElement("div");
        msg.className = "received_msg";
        let msg1 = document.createElement("div");
        msg1.className = "received_withd_msg";
        let sms = document.createElement("p");
        sms.innerHTML = arr[0];
        let d = document.createElement("span");
        d.className = "time_date";
        let time = new Date();
        d.innerHTML = time.getHours() + ":" + time.getMinutes() + " | " +time.getFullYear()+"-"+time.getMonth()+"-"+time.getDay();
        msg1.append(sms, d);
        msg.append(msg1);
        inc.append(usImgDiv, msg);
        msgHistory.append(inc);
    }
}


