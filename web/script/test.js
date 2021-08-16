let usId = document.querySelector("#usId");
let toId = document.querySelector("#toId");
let msgHistory = document.querySelector(".his");
let msgSend_btn = document.querySelector(".msg_send_btn");
let messageText = document.querySelector(".write_msg");
console.log(msgHistory)
console.log(msgSend_btn)
console.log(messageText)
    msgSend_btn.addEventListener("click", sendMessage);


var webSocket=new WebSocket("ws://localhost:8080/server");
webSocket.onopen=function(){
    webSocket.send(JSON.stringify({
        id:usId.value,
        toId:toId.value
    }));
}
webSocket.onmessage=function processMessage(message) {
    var jsonData=JSON.parse(message.data);
    console.log(jsonData.message)
    if(jsonData.message!=null) {
        let out = document.createElement("div");
        out.className = "outgoing_msg";
        let sendMsg = document.createElement("div");
        sendMsg.className = "sent_msg";
        let m = document.createElement("p");
        m.className = "m";
        m.innerHTML = jsonData.message + "\n";
        sendMsg.append(m);
        out.append(sendMsg);
        msgHistory.append(out);
    }
       // msgHistory.value+=jsonData.message+"\n";
}
function sendMessage() {
    console.log(messageText.value);
    webSocket.send(messageText.value);
    messageText.value="";
}