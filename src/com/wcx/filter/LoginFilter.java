package com.wcx.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
        // 销毁
        System.out.println("销毁");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 之前
        System.out.println("之前");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println(path);
        if(!path.equals("/login.jsp")) {
            if(session==null || session.getAttribute("username")==null) {
                ((HttpServletResponse) resp).sendRedirect("login.jsp");
            }
        }
        chain.doFilter(req, resp); // 传递请求

        // 之后
        System.out.println("之后");
    }

    public void init(FilterConfig config) throws ServletException {
        // 初始化
        System.out.println("初始化");
    }
}
