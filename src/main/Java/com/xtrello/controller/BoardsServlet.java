package com.xtrello.controller;

import com.xtrello.views.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BoardsServlet", urlPatterns = {"/Board/*"})
public class BoardsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<a href=\"/Board/createBoard\" class=\"btn btn-primary  role=\"button\">Створити дошку</a>");
        out.println("<a href=\"/Board/removeBoard\" class=\"btn btn-primary  role=\"button\">Видалити дошку</a>");
        out.println("<a href=\"/Board/updateBoard\" class=\"btn btn-primary  role=\"button\">Редагувати  дошку</a>");

        switch (request.getPathInfo()){
            case "/":
                out.write("<H1>Hello User!</H1>");
                break;
            case "/createBoard":

                out.write("<H1>Create Board</H1>");

                break;
            case "/removeBoard":

                out.write("<H1>Remove Board</H1>");

                break;
            case "/updateBoard":

                out.write("<H1>Update Board</H1>");

                break;
        }



    }
}
