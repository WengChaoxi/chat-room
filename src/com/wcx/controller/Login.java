package com.wcx.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] user_list = new String[7];
        user_list[0] = "翁朝曦\n4029";
        user_list[1] = "哈宇豪\n4027";
        user_list[2] = "陈滔滔\n4022";
        user_list[3] = "毛进杰\n4023";
        user_list[4] = "王浩沣\n4026";
        user_list[5] = "你好世界\n000000";
        user_list[6] = "世界很好\n111111";

        String username_t = req.getParameter("username");
        String username = new String(username_t.getBytes("iso8859-1"), "utf8"); // 编码转换
        String password = req.getParameter("password");

        boolean is_success = false;
        for(int i=0; i<user_list.length; i++) {
            String userinfo = username+"\n"+password;
            if(userinfo.equals(user_list[i]))
            {
                is_success = true;
                break;
            }
        }

        if(is_success)
            req.getRequestDispatcher("loginSuccess.do").forward(req, resp);
        else
            req.getRequestDispatcher("loginFail.do").forward(req, resp);
    }
}