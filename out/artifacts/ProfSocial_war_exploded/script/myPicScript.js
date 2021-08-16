$(document).ready(
    function(){
        $('input:file').change(
            function(){
                if ($(this).val()) {
                    $('input:submit').attr('disabled',false);
                }
            }
        );
    });
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




/*let x=document.querySelectorAll(".x");
let im=document.querySelectorAll(".im");
console.log(x);
console.log(im);
for(var i=0;i<x.length;i++){
    x[i].addEventListener("click",DELETE);
}

function DELETE() {
    let val=this.value;
    console.log(val);
    for(var j=0;j<im.length;j++){
        if(im[j].id==val){
            im[j].id.remove();
            break
        }
    }
}*/

