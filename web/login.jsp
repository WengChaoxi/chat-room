<%--
  Created by IntelliJ IDEA.
  User: 翁朝曦
  Date: 2020/11/17
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="head.jsp"%>
<body>
<%
    String username = "", password = "";
    Cookie[] cookies = request.getCookies();
    if(cookies != null)
    {
        for(int i=0; i<cookies.length; i++)
        {
            if(cookies[i].getName().equals("username"))
                username = cookies[i].getValue();
            if(cookies[i].getName().equals("password"))
                password = cookies[i].getValue();
        }
    }
%>
<div align="center">
    <br><br>
    <img src="https://id.scuec.edu.cn/dzlogo.png">
    <form id="login" method="post" action="login">
        <br>
        <input type="text" name="username" id="username" placeholder="用户名" value=<%=username%>>
        <br>
        <input type="password" name="password" id="password" placeholder="密码" value="<%=password%>">
        <br>
        <input type="checkbox" name="remember" id="remember" checked="checked" value="0"><span>记住我</span>
        <input type="submit" value="登录">
        <input type="reset" value="重置">
    </form>
    <a href="https://www.scuec.edu.cn/jky/" target="_blank">计算机科学学院</a>
</div>
</body>
</html>