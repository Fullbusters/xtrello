package com.xtrello.controller;

import com.xtrello.dao.card.CardDao;
import com.xtrello.dao.card.CardDaoImpl;
import com.xtrello.dao.listcard.ListCardDao;
import com.xtrello.dao.listcard.ListCardDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет який відповідає за створення та видалення списку карточок
 */
@WebServlet(name = "ListCardServlet",urlPatterns = {"/ListCard/*"})
public class ListCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        switch (request.getPathInfo()){
            // створення списка карточок
            case "/createListCard":

                ListCardDao listCardDao=new ListCardDaoImpl();
                // назва списку
                String namelistcard=request.getParameter("namelistcard");
                // id дошки в якій створюємо
                long idboard=Long.parseLong(request.getParameter("id"));
                // створення
                listCardDao.createListCard(namelistcard,idboard);
                response.sendRedirect("/Board/Board?id="+idboard+"");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        switch (request.getPathInfo()){
            // Видалення списку карточок та всіх карточок в ньому
            case "/Delete":
                ListCardDao listCardDao=new ListCardDaoImpl();
                CardDao  cardDao=new CardDaoImpl();
                // id списку карточок  для видалення
                long idlistcardfordelete = Long.parseLong(request.getParameter("idlistcard"));
                // id дошки в якій ми видаляємо (необхідно для sendRedirect )
                long idboard = Long.parseLong(request.getParameter("idboard"));
                // видалення всіх карточок в списку за id списку
                cardDao.deleteCardByListCardId(idlistcardfordelete);
                // видалення списку
                listCardDao.deleteListCardByListCard_Id(idlistcardfordelete);
                response.sendRedirect("/Board/Board?id="+idboard+"");

                break;



        }
    }
}
