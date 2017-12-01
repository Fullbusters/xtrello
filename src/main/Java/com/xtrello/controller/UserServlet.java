package com.xtrello.controller;

import com.xtrello.Dao.User.UserDaoImpl;
import com.xtrello.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet",urlPatterns = {"/user/*"})
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        UserDaoImpl userDao = new UserDaoImpl();
        HttpSession session = request.getSession();

        switch (request.getPathInfo()) {
            case "/register":
                String emailreg = request.getParameter("emailLogin");
                String passwordreg = request.getParameter("loginPassword");
                String Username=request.getParameter("Username");
                out.println(emailreg+"\t"+passwordreg+"\t"+Username);
                userDao.addUser(emailreg,passwordreg,Username);
                out.println("<h1>Congratulation</h1>");

                break;
            case "/login":
                // перевіряє логін форму, якщо неправильно введені дані повертає форму для перезаповнення
                String email = request.getParameter("emailLogin");
                String password = request.getParameter("loginPassword");
                User user = userDao.findUserByEmail(email);
                //якщо емейл є в БД, змінна user буде посилатись на об'єкт класу User, інакше дорівнюватиме null
                if(user != null) {
                    //записав об'єкт користувача в сесію, щоб перевіряти в інших сервлетах чи зареєстрований користувач
                    session.setAttribute("user", user);
                    response.sendRedirect("/");
                } else {
                    response.sendRedirect("/Login");

                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
