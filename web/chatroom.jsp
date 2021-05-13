<%--
  Created by IntelliJ IDEA.
  User: 翁朝曦
  Date: 2020/11/20
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<%@ include file="head.jsp" %>

<%--<body bgcolor=#F7F2E0>--%>
<body>
<h2 align="center" onselectstart="return false;" style="margin: 39px">一个聊天室</h2>
<div class="chat_box" id="chat_box_id">
    <%--    <div class="chat_content" id="chat_content_id">--%>
    <%--        <div class="chat_other"><textarea readonly="">你是谁？</textarea></div>--%>
    <%--        <div class="chat_own"><textarea readonly="">我是谁？</textarea></div>--%>
    <%--    </div>--%>
    <div class="chat_content" id="chat_content_id">
    </div>
    <div>
        <p class="online_info">在线人数：<span id="chat_online_id">0</span></p>
    </div>
    <div class="chat_input" id="chat_input_id">
        <input type="text" class="chat_input_content" id="chat_input_content_id" onkeypress="keyPress()">
        <input type="button" value="发送" class="chat_btn_send" id="chat_btn_send_id" onclick="sendMsg()">
    </div>
</div>
</body>
<script type="text/javascript" src="static/js/chatroom.js"></script>
</html>
