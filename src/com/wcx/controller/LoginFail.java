package com.wcx.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginFail", urlPatterns = "/loginFail.do")
public class LoginFail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf8");
        PrintWriter pw = response.getWriter();
        String html = "<html>\n" +
                "    <head>\n" +
                "        <title>聊天室</title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <div align=\"center\">\n" +
                "            <h1>账号或密码错误！</h1>\n" +
                "            <br><br>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";

        pw.println(html);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
