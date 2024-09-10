package com.ssg.web2.todo.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
@Log4j2     //세션을 이용한 로그아웃
public class LogOutController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         log.info("logOut ....");

         HttpSession session = req.getSession();
         session.removeAttribute("loginInfo");
         session.invalidate();   //세션종료 메소드

         resp.sendRedirect("/");


    }
}
