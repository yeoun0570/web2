package com.ssg.web2.todo.controller;

import com.ssg.web2.todo.dto.MemberDTO;
import com.ssg.web2.todo.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="loginController" , urlPatterns = "/login")
public class LoginController extends HttpServlet {

           // get 은 로그인 을 화면을 보여주고, POST방식으로 실제 로그인 처리하도록 구성 하도록 한다.
           //1. webservlet 해당 컨트롤러 등록 이름  /login
           //2. doGet   login.jsp 파일로 포워딩
           //3. login.jsp 파일 만들어주세요 ... text 2개   id(mid), pwd(mpw) , submit 버튼

    MemberService memberService = MemberService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/todo/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");      //파라미터 수집

        try {
            MemberDTO dto = memberService.login(mid, mpw);
            HttpSession  session = req.getSession();   //HttpSession을 이용해서 setAttribute()를 사용자 공간에 loginInfo 라는 이름으로 문자열을 보관
            session.setAttribute("loginInfo",dto);
            resp.sendRedirect("/todo/list");
        } catch (Exception e) {
            resp.sendRedirect("/login?result=error");
        }
    }
}
