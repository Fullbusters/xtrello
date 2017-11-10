package com.xtrello.controller;

import com.xtrello.views.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ListCardServlet", urlPatterns = {"/ListCard/*"})
public class ListCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<a href=\"/ListCard/createListCard\" class=\"btn btn-primary  role=\"button\">Створити список карточок</a>");
        out.println("<a href=\"/ListCard/removeListCard\" class=\"btn btn-primary  role=\"button\">Видалити список карточок</a>");
        out.println("<a href=\"/ListCard/updateListCard\" class=\"btn btn-primary  role=\"button\">Редагувати  список карточок</a>");

        switch (request.getPathInfo()){
            case "/":
                out.write("<H1>Hello User!</H1>");
                break;
            case "/createListCard":

                out.write("<H1>Create ListCard</H1>");

                break;
            case "/removeListCard":

                out.write("<H1>Remove ListCard</H1>");

                break;
            case "/updateListCard":

                out.write("<H1>Update ListCard</H1>");

                break;
        }





    }
}
