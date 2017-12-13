package com.xtrello.controller;

import com.xtrello.dao.card.CardDao;
import com.xtrello.dao.card.CardDaoImpl;
import com.xtrello.dao.listcard.ListCardDao;
import com.xtrello.dao.listcard.ListCardDaoImpl;
import com.xtrello.models.Card;
import com.xtrello.models.ListCard;
import com.xtrello.views.CardView;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет відповідає за роботу над карточками (створення, редагування, видалення )
 */
@WebServlet(name = "CardServlet",urlPatterns = {"/Card/*"})
public class CardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        CardDao cardDao=new CardDaoImpl();
        switch (request.getPathInfo()){
            // Створення карточки
            case "/createCard":
                // назва карточки
                String namecard=request.getParameter("namecard");
                // id списку карточок в якому ми створюємо цю карточку
                long idlistcard=Long.parseLong(request.getParameter("id"));
                // id дошкт в якій ми створюємо цю карточку (потрібен для sendRedirect)
                long idboard=Long.parseLong(request.getParameter("idboard"));

                cardDao.createCard(namecard,idlistcard);
                response.sendRedirect("/Board/Board?id="+idboard);
                break;

            // Редагування коментаря до карточки
            case "/createComentar":
                // коментар який буде додано до карточки
                String comentar=request.getParameter("comentar");
                // id карточки до якої він буде доданий
                long idcard= Long.parseLong(request.getParameter("optionsRadios"));
                cardDao.updateComentar(comentar,idcard);
                response.sendRedirect("/Board/Board/Card?id="+idcard+"");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        CardDao cardDao=new CardDaoImpl();
        CardView cardView = new CardView();
        switch (request.getPathInfo()){
            // вивід всіх даних що є в карточці
            case "/content":
                // id карточки інформацію про яку ми виводимо
                long idcard= Long.parseLong(request.getParameter("id"));
                // карточка знайдена по id
                Card card=cardDao.getCardByCardId(idcard);
                // вивід інформації(коментар та форма створення коментаря)
                cardView.outcontentCard(out,card);
                // кнопка видалення карточки
                out.println("<a href=\"/Card/Delete?id="+idcard+"\" class=\"btn btn-warning  role=\"button\"> Видалити карточку</a>");
                break;

            // Видалення карточки
            case"/Delete":
                // id карточки яку видаляєм
                long idcardfordelete = Long.parseLong(request.getParameter("id"));
                // знаходження її з бази даних
                Card cardfordelete=cardDao.getCardByCardId(idcardfordelete);
                // видалення карточки за її id
                cardDao.deleteCardByCardId(cardfordelete.getId());
                // Знаходження списку карточок (необходно для sendRedirect )
                ListCardDao listCardDao=new ListCardDaoImpl();
                ListCard lstcard=listCardDao.getListCardByListCardId(cardfordelete.getListCard_id());
                response.sendRedirect("/Board/Board?id="+lstcard.getBoard_id()+"");
                break;
        }


    }
}
