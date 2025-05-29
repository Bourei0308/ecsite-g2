package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.user.SiteUser;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/no-permission");
            return false;
        }

        SiteUser user = (SiteUser) session.getAttribute("su");

        if (user == null || !user.getAdminFlag()) {
            response.sendRedirect("/no-permission");
            return false;
        }

        return true;
    }
}
