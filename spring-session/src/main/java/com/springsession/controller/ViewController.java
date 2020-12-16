package com.springsession.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author sky
 * @date 2020/12/15 11:26
 */
@Controller
public class ViewController {

    /**
     *
     * session会自动返回给前端
     * @param request
     * @return
     */
    @RequestMapping("login")
    public String loginSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("user"));
        session.setAttribute("user", "测试");
        return "login";
    }

    @RequestMapping("login2")
    public String loginCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("获取的cookieName:" + cookie.getName() + "---cookieValue:" + cookie.getValue());
        }
        Cookie username = new Cookie("cookie_username", "username");
        response.addCookie(username);
        return "login";
    }

}