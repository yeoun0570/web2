package com.ssg.web2.todo.controller;

import com.ssg.web2.todo.dto.TodoDTO;
import com.ssg.web2.todo.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet(name="todoModifyController" , value="/todo/modify")
@Log4j2
public class TodoModifyController extends HttpServlet {
       private TodoService todoService = TodoService.INSTANCE;
       private final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long tno = Long.parseLong(req.getParameter("tno"));

        try {
            TodoDTO dto = todoService.get(tno);
            //데이터 담아서 해당 페이지로 forward
            req.setAttribute("dto",dto);
            req.getRequestDispatcher("/todo/modify.jsp").forward(req,resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String finishedStr = req.getParameter("finished");

        TodoDTO todoDTO = TodoDTO.builder().tno(Long.parseLong(req.getParameter("tno"))).title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATETIMEFORMATTER))
                .finished(finishedStr != null  && finishedStr.equals("on")).build();

        log.info("todo Modify Controller ....POST");
        log.info(todoDTO);

        try {
            todoService.modify(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

       resp.sendRedirect("/todo/list");
    }

}
