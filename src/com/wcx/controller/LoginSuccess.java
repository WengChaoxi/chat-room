package com.wcx.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginSuccess", urlPatterns = "/loginSuccess.do")
public class LoginSuccess extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username_t = request.getParameter("username");
        String username = new String(username_t.getBytes("iso8859-1"), "utf8"); // 编码转换
        String password = request.getParameter("password");
        String check = request.getParameter("remember");
        String context_path = request.getContextPath();
        Cookie c1, c2;
        int max_age = 0; // s
        if(check != null) {
            max_age = 3600*24*31;
            c2 = new Cookie("password", password);
        }
        else {
            c2 = new Cookie("password", "");
        }
        c1 = new Cookie("username", username);
        c1.setPath(context_path);
        c1.setMaxAge(max_age);
        c2.setPath(context_path);
        c2.setMaxAge(max_age);
        response.addCookie(c1);
        response.addCookie(c2);

        HttpSession session = request.getSession(true); // 参数是true：表明有则返回，无则创建；若为false，有则返回，无则返回null
        session.setAttribute("username", username);
        response.sendRedirect("chatroom.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
