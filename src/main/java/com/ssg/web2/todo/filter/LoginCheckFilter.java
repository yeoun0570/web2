package com.ssg.web2.todo.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/todo/*"})
@Log4j2
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //Filter 인터페이스의 doFilter() 추상메서드는 필터링이 필요한 로직을 구현하는 메서드이다.(정해져있음)
        //필터를 적용하기 위해서는 @WebFilter 처리 필수
        //@WebFilter(urlPatterns = {"/todo/*"}) ==> 지정한 /todo/* 경로를 지정해서 해당 경로의 요청(request)에 대해서 doFilter를 실행하겠다.
        //즉, LoginCheckFilter는 /todo/* 경로에 대해서 필터링 시도하겠다.

        log.info("LoginCheck filter이다.");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        if(session.getAttribute("loginInfo") == null) {
            resp.sendRedirect("/login");
            return;
        }

        filterChain.doFilter(request,response); //다음 필터나 목적지(servlet, jsp)로 갈 수 있도록 FilterChain의 doFilter()를 실행한다.

        //즉, loginInfo가 null이 아니라면 너가 원하는 곳으로 가라는 뜻
    }
}
