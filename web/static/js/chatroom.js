
var chat_content = document.getElementById("chat_content_id"); // 聊天框
var chat_input_content = document.getElementById("chat_input_content_id"); // 输入框
var chat_online = document.getElementById("chat_online_id"); // 在线人数
var is_sending = false;
var is_bottom = false;
var xhr_send_msg = getXmlHttpObject();
var xhr_get_msg = getXmlHttpObject();

function sendMsg() {
    if(chat_input_content.value === "") {
        // alert("消息不能为空嗷~");
        retrun ;
    }
    // var str = '<div class="chat_own"><textarea readonly="">' + chat_input_content.value +'</textarea></div>';
    // chat_content.innerHTML = chat_content.innerHTML + str;
    var url = "sendMessage.do";
    // xhr.onreadystatechange = handleMessage;
    xhr_send_msg.open("post", url, true);
    xhr_send_msg.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr_send_msg.send(JSON.stringify(chat_input_content.value)); // 发送msg

    chat_input_content.value = ""; // 清空消息
    // chat_content.scrollTop = chat_content.scrollHeight; // 发送后滑动到最底部
    // chat_input_content.focus(); // 聚焦输入框

    is_sending = true;
    refresh();
}

function refresh() {
    var url = "getMessage.do";
    xhr_get_msg.onreadystatechange = getMessage;
    xhr_get_msg.open("post", url, true);
    // xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr_get_msg.send(""); // 发送msg

    chat_input_content.focus(); // 聚焦输入框
}

function getMessage() {
    if(xhr_get_msg.readyState === 4) {
        if(xhr_get_msg.status === 200) {
            try {
                // 在线人数
                chat_online.innerText = JSON.parse(xhr_get_msg.response).online;

                var all_msg = JSON.parse(xhr_get_msg.response).all_msg; //eval('('+res+')');
                if(all_msg == null)
                    return ;
                var msg_list = all_msg.split("#");
                var chat_content_tmp = "";
                var username = getCookie("username");
                for(let i=0; i< msg_list.length-1; i++) { // 只取0到n-1
                    var username_t = msg_list[i].split(":")[0];
                    var msg = msg_list[i].replace(username_t+":", "");
                    if(username === username_t) {
                        // 自己说的话
                        // TODO
                        chat_content_tmp = chat_content_tmp
                            + "<div class=\"chat_own\">"
                            + "<span>" + username + "</span>"
                            + "<textarea readonly=\"\">"+ msg + "</textarea></div>";
                    } else {
                        // 别人说的话
                        // TODO
                        chat_content_tmp = chat_content_tmp
                            + "<div class=\"chat_other\">"
                            + "<span>" + username_t + "</span>"
                            + "<textarea readonly=\"\">"+ msg + "</textarea></div>";
                    }
                    chat_content.innerHTML = chat_content_tmp;
                    if(is_sending || is_bottom) {
                        chat_content.scrollTop = chat_content.scrollHeight; // 发送后滑动到最底部
                        is_sending = false;
                    }
                }

            } catch (e) {
            }
        }else
            console.log("跨域");
    }
}

function keyPress() {
    if(event.keyCode === 13) {
        sendMsg();
    }
}

function getXmlHttpObject() {
    var xmlHttp = null;
    try {
        // Firefox, Opera 8.0+, Safari
        console.log("XMLHttpRequest");
        xmlHttp=new XMLHttpRequest();
    } catch (e) {
        // Internet Explorer
        try {
            console.log("Msxml2");
            xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            console.log("Microsoft");
            xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return xmlHttp;
}

function getCookie(name) {
    var arr,reg = new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else return null;
}

chat_content.onscroll = function onScroll() {
    let st = chat_content.scrollTop; // 滚动的高度
    let ch = chat_content.clientHeight; // 可视区域的高度
    let sh = chat_content.scrollHeight //可视区域+溢出的高度
    // if(st>0 && st+ch==sh)
    if(st+ch >= sh-39) // 39为误差
        is_bottom = true;
    else is_bottom = false;
}

setInterval(refresh, 1000);