package com.xtrello.controller;

import com.xtrello.Dao.Card.CardDao;
import com.xtrello.Dao.Card.CardDaoImpl;
import com.xtrello.Dao.listcard.ListCardDao;
import com.xtrello.Dao.listcard.ListCardDaoImpl;
import com.xtrello.views.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ListCardServlet",urlPatterns = {"/ListCard/*"})
public class ListCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        HttpSession session = request.getSession();
//        IndexView indexView = new IndexView();
        switch (request.getPathInfo()){
            case "/Delete":
                ListCardDao listCardDao=new ListCardDaoImpl();
                CardDao  cardDao=new CardDaoImpl();
                long idlistcardfordelete = Long.parseLong(request.getParameter("idlistcard"));
                long idboard = Long.parseLong(request.getParameter("idboard"));
                cardDao.deleteCardByListCardId(idlistcardfordelete);
                listCardDao.deleteListCardByListCard_Id(idlistcardfordelete);
                response.sendRedirect("/Board/Board?id="+idboard+"");

                break;


        }
    }
}
