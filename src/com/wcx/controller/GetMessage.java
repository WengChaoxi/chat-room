package com.wcx.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetMessage", urlPatterns = "/getMessage.do")
public class GetMessage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String all_msg = (String) getServletContext().getAttribute("all_msg");
        if(all_msg == null) {
            getServletContext().setAttribute("all_msg", "");
            all_msg = "";
        }

        String online = (String)getServletContext().getAttribute("online");
        if(online == null)
            online = "0";

        // 返回json: 所有的消息
        String data = "{ \"all_msg\":\""+all_msg+"\", \"online\":\""+online+"\" }";
        System.out.println("响应给用户:" + data);
        response.setContentType("application/json;charset=utf8");
        response.getWriter().print(data);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
