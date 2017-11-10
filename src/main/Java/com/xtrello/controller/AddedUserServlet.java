package com.xtrello.controller;

import com.xtrello.views.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddedUserServlet", urlPatterns = {"/AddedUser/*"})
public class AddedUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<a href=\"/AddedUser/createAddedUser\" class=\"btn btn-primary  role=\"button\">Створити доданого користувача</a>");
        out.println("<a href=\"/AddedUser/removeAddedUser\" class=\"btn btn-primary  role=\"button\">Видалити доданого користувача</a>");
        out.println("<a href=\"/AddedUser/updateAddedUser\" class=\"btn btn-primary  role=\"button\">Редагувати доданого користувача</a>");

        switch (request.getPathInfo()){
            case "/":
                out.write("<H1>Hello User!</H1>");
                break;
            case "/createAddedUser":

                out.write("<H1>Create AddedUser</H1>");

                break;
            case "/removeAddedUser":

                out.write("<H1>Remove AddedUser</H1>");

                break;
            case "/updateAddedUser":

                out.write("<H1>Update AddedUser</H1>");

                break;
        }


    }
}
