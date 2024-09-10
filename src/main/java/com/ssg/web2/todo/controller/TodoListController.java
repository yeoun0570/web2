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
import java.util.List;

@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        log.info("todo...list 야.....잘 왔어.");

        //code ver 1.0
        //List<TodoDTO> dtoList = TodoService.INSTANCE.getList();
        //req.setAttribute("dtolist",dtoList);

        //req.getRequestDispatcher("/todo/list.jsp").forward(req,resp);

          try {
              List<TodoDTO> dtoList = todoService.listAll();
              req.setAttribute("dtoList",dtoList);
              req.getRequestDispatcher("/todo/list.jsp").forward(req,resp);
          }catch (Exception e) {
             log.error(e.getMessage());
             throw new ServletException("list error");
          }


    }


}
