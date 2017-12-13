package com.xtrello.controller;

import com.xtrello.dao.user.UserDaoImpl;
import com.xtrello.models.User;
import com.xtrello.views.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Серврет який працює з користувачами (реестрація, логін, вихід )
 */
@WebServlet(name = "UserServlet",urlPatterns = {"/user/*"})
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        UserDaoImpl userDao = new UserDaoImpl();
        HttpSession session = request.getSession();

        switch (request.getPathInfo()) {
            /**
             * Перевірка та реєстрація користувача який заповнив форму реєстрації
             */
            case "/register":
                String emailreg = request.getParameter("emailLogin");
                String passwordreg = request.getParameter("loginPassword");
                String Username=request.getParameter("Username");
                if(emailreg.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
                    if(passwordreg.length()>6 && passwordreg.length()<20){
                        userDao.addUser(emailreg,passwordreg,Username);
                    }else{
                        response.sendRedirect("/Reg?value=2");
                    }
                } else {
                    response.sendRedirect("/Reg?value=1");
                }


                out.println("<h1>Congratulation</h1>");

                break;
            /**
             * Перевірка користувача при вході на сайт якщо щось не правильно повідомлення про помилку
             */
            case "/login":
                // перевіряє логін форму, якщо неправильно введені дані повертає форму для перезаповнення
                String email = request.getParameter("emailLogin");
                String password = request.getParameter("loginPassword");
                User user = userDao.findUserByEmail(email);

                //якщо емейл є в БД, змінна user буде посилатись на об'єкт класу User, інакше дорівнюватиме null
                if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    //записав об'єкт користувача в сесію, щоб перевіряти в інших сервлетах чи зареєстрований користувач
                    session.setAttribute("user", user);
                    response.sendRedirect("../");
                } else {

                    response.sendRedirect("/Login?value=1");
                }
                break;

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        IndexView indexView = new IndexView();
        indexView.outTopPage(out);
        switch (request.getPathInfo()){
            /**
             * вихід з сайту
             */
            case "/logout":
                session.removeAttribute("user");
                session.invalidate();
                response.sendRedirect("/");
                break;
            /**
             * вивід форми логіну на екран
             */
            case "/login":
                //змінна яка вказує на те яка помилка при вроді в систему (1-неправильние email)
                String valuelogin = (request.getParameter("value"));

                indexView.outLogin(out,valuelogin);
                break;
            /**
            * вивід форми реєстрації на екран
            */
            case "/reg":
                // Змінна яка вказує на помилку при реєстрації 1-неправильний email 2-неправильний пароль
                String valuereg =request.getParameter("value");
                indexView.outReg(out,valuereg);
                break;


        }
        indexView.outBottomPage(out);

    }
}
