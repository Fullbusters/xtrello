package com.xtrello.controller;

import com.xtrello.dao.board.BoardDao;
import com.xtrello.dao.board.BoardDaoImpl;
import com.xtrello.dao.card.CardDao;
import com.xtrello.dao.card.CardDaoImpl;
import com.xtrello.dao.listBoards.ListBoardDao;
import com.xtrello.dao.listBoards.ListBoardDaoImpl;
import com.xtrello.dao.sharedBoard.SharedBoardDao;
import com.xtrello.dao.sharedBoard.SharedBoardDaoImpl;
import com.xtrello.dao.sharedlistboards.SharedListBoardDaoImpl;
import com.xtrello.dao.sharedlistboards.SharedListBoardsDao;
import com.xtrello.dao.user.UserDao;
import com.xtrello.dao.user.UserDaoImpl;
import com.xtrello.dao.listcard.ListCardDao;
import com.xtrello.dao.listcard.ListCardDaoImpl;
import com.xtrello.models.*;
import com.xtrello.views.BoardView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Сервлет відповідає за роботою над дошками (створення, видалення, вивід на екран, додавання прав користувачам до дошки )
 */
@WebServlet(name = "BoardsServlet", urlPatterns = {"/Board/*"})
public class BoardsServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(BoardsServlet.class.getName());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        BoardDaoImpl boardDao=new BoardDaoImpl();
        ListBoardDao listBoardDao=new ListBoardDaoImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDao userDao=new UserDaoImpl();

        SharedListBoardsDao sharedListBoardsDao=new SharedListBoardDaoImpl();
        SharedBoardDao sharedBoardDao=new SharedBoardDaoImpl();

        switch (request.getPathInfo()){
            // створення дошки
            case "/createBoard":
                // назва дошки яка буде створена
                String name = new String(request.getParameter("name").getBytes("iso-8859-1"),
                        "UTF-8");
                // id команди в якій буде створено
                long radio=Long.parseLong(request.getParameter("optionsRadios"));
                    boardDao.createBoard(name,radio,user.getId());

                response.sendRedirect("/");

                break;
                // створення команди
            case "/createListBoard":
                // назва команди
                String namelistboard = request.getParameter("name");
                // опис команди
                String text = request.getParameter("text");
                listBoardDao.createListBoard(namelistboard,user.getId(),text);
                response.sendRedirect("/");
                break;
            /**
             * Додавання користувача до дошки
             */
            case "/addUsertoboard":
                // email за яким ми додаємо користувача
                String emailuser=request.getParameter("emailuser");
                // id дошки до якої ми додаємо
                long idboardtoadduser=Long.parseLong(request.getParameter("optionsRadios"));
                Board boardadd=boardDao.getBoardByBoardId(idboardtoadduser);
                User adduser=userDao.findUserByEmail(emailuser);
                // додання користувача до дошки
                sharedBoardDao.addUserInSharedBoards(adduser.getId(),idboardtoadduser);
                // перевірка чи дошка є приватною  якщо не приватна то також надаємо доступ до команди в якій знаходиться дошка
                if(boardadd.getListBoard_id()!=1) {
                    sharedListBoardsDao.addUserInSharedListBoards(adduser.getId(), boardadd.getListBoard_id());
                }
                response.sendRedirect("/");
                break;
            /**
             * додання користувача до команди
             */
            case "/addUserToListBoard":
                String emailuserforaddtolistcard=request.getParameter("emailuser");
                long idlistboardtoadduser=Long.parseLong(request.getParameter("optionsRadios"));
                User addusertolistcard=userDao.findUserByEmail(emailuserforaddtolistcard);
                sharedListBoardsDao.addUserInSharedListBoards(addusertolistcard.getId(),idlistboardtoadduser);
                response.sendRedirect("/");
                break;

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        BoardView boardView=new BoardView();
        SharedListBoardsDao sharedListBoardsDao=new SharedListBoardDaoImpl();
        BoardDao boardDao=new BoardDaoImpl();
        CardDao cardDao = new CardDaoImpl();
        ListCardDao listCardDao=new ListCardDaoImpl();
        User user = (User) session.getAttribute("user");
        List<ListBoard> lstListBoard =sharedListBoardsDao.getListBoardsByUserId(user.getId());

        switch (request.getPathInfo()){
            /**
             * Виведення вмісту дошки за заданим id
             */
            case "/Board":
                // id за яким ми визначаємо з якої дошки які дані будуть виведені
                long idboard=Long.parseLong(request.getParameter("id"));
                // колекція списків карточок знайдена за id дошки
                List<ListCard> lstCard=listCardDao.getListCardByBoardId(idboard);
                // вивід всіх списків карточок які знаходяться в дошці
                String strcard=lstCard.stream()
                        .map(e->{
                             boardView.outListCard(out,e);
                            return "";
                        })
                        .collect(Collectors.joining(""));
                out.println("<div class=\"row\">");
                // виведення форми створення списку карточок
                boardView.outCreateListCard(out,idboard);
                out.println("</div>");
                // виведення форми додання користувача до дошки
                boardView.outAddUserToBoard(out,idboard);
                // кнопка видалення дошки
                out.println("<a href=\"/Board/Board/Delete?id="+idboard+"\" class=\"btn btn-warning  role=\"button\"> Видалити Дошку</a>");
                break;
            /**
             * Вивід всіх дошок які знаходяться в команді
             */
            case "/allBoard":
                // id команди в яку ми перейшли
                long idlistboard= Long.parseLong(request.getParameter("id"));

                out.println("<div class=\"row\">");
                // вивід всіх дошок які є в команді
                boardView.outBoard(out,boardDao.getBoardByListBoardId(idlistboard));
                out.println("</div>");
                // вивід форми додання користувача до команди
                boardView.outAddUserToListBoard(out,idlistboard);
                out.println("<a href=\"/Board/allBoard/ListBoard/Delete?id="+idlistboard+"\" class=\"btn btn-warning  role=\"button\"> Видалити список дошок </a>");

                boardView.outCreateBoardBottom(out,lstListBoard);
                break;

                // Видалення команди за даним id команди
            case "/allBoard/ListBoard/Delete":

                ListBoardDao listBoardDao=new ListBoardDaoImpl();
                //  id команди якої ми водаляємо
                long idlistboardfordelete = Long.parseLong(request.getParameter("id"));
                listBoardDao.deleteListBoard(idlistboardfordelete);
                response.sendRedirect("/");
                break;

                // Видалення дошки та всіх даних що знаходяться в нії за її id
            case"/Board/Delete":
                // id дошки яку ми видаляємо
                long idboardfordelete = Long.parseLong(request.getParameter("id"));
                // Видалення всіх карточок які знаходяться в дошці
                String deletecard=listCardDao.getListCardByBoardId(idboardfordelete).stream()
                        .map(e->{ cardDao.deleteCardByListCardId(e.getId());
                            return "";
                        })
                        .collect(Collectors.joining(""));

                // Видалення всіх списків карточок які знаходяться в дошці
                listCardDao.deleteListCardByBoard_Id(idboardfordelete);
                // Видалення самоъ дошки
                boardDao.deleteBoard(idboardfordelete);
                response.sendRedirect("/");
                break;

        }



    }
}
