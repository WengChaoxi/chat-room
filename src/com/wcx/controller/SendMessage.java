package com.wcx.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet(name = "SendMessage", urlPatterns = "/sendMessage.do")
public class SendMessage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(int i=0; i<cookies.length; i++) {
                if(cookies[i].getName().equals("username")) {
                    username = cookies[i].getValue();
                    break;
                }
            }
        }
        // 接收json
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder stringBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            stringBuilder.append(inputStr);
        String tmp = stringBuilder.toString().replace("#", "&").replace("<", "<&");

        // 解析当前收到的
        // message( "翁朝曦:你好世界" ) 即谁说了什么话
        String msg = username + ":" + tmp.substring(1, tmp.length()-1);
        System.out.println("接收到的:" + msg);

        // 消息入队（存入上下文）
        String all_msg = (String) getServletContext().getAttribute("all_msg");
        if(all_msg != null) {
            all_msg = all_msg + msg + "#";
            getServletContext().setAttribute("all_msg", all_msg);
        }
        else {
            getServletContext().setAttribute("all_msg", "");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = "";
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null) {
//            for(int i=0; i<cookies.length; i++) {
//                if(cookies[i].getName().equals("username")) {
//                    username = cookies[i].getValue();
//                    break;
//                }
//            }
//        }
//
//        response.setContentType("text/html;charset=utf8");
//        String chat_content = "";
//        ServletContext servletContext = request.getSession().getServletContext();
//
////            servletContext.setAttribute("all_msg", "翁朝曦:123\n翁向阳:456\n");
//        String all_msg = (String)servletContext.getAttribute("all_msg");
//        if(all_msg != null) {
//            String[] msg_list = all_msg.toString().split("#");
//            for(int i=0; i< msg_list.length; i++) {
//                String username_t = msg_list[i].split(":")[0];
//                String msg = msg_list[i].replace(username_t+":", "");
//                if(username.equals(username_t)) {
//                    // 自己说的话
//                    // TODO
//                    if(!msg.isEmpty())
//                        chat_content = chat_content + "<div class=\"chat_own\"><textarea readonly=\"\">" + msg + "</textarea></div>";
//                }
//                else {
//                    // 别人说的话
//                    // TODO
//                    if(!msg.isEmpty())
//                        chat_content = chat_content + "<div class=\"chat_other\"><textarea readonly=\"\">" + msg + "</textarea></div>";
//                }
//            }
//        }
//        else
//            servletContext.setAttribute("all_msg", "");
//        PrintWriter pw = response.getWriter();
//        String html = //"<script>var chat_content=setTimeout(\"location.href='/handleMessage'\",600);</script>" +
//                "<link rel=\"stylesheet\" href=\"static/css/chatroom.css\">"+ chat_content;
//
//        pw.println(html);
    }
}
