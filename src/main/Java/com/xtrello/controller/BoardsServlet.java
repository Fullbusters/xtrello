package com.xtrello.controller;

import com.xtrello.Dao.Board.BoardDao;
import com.xtrello.Dao.Board.BoardDaoImpl;
import com.xtrello.Dao.Card.CardDao;
import com.xtrello.Dao.Card.CardDaoImpl;
import com.xtrello.Dao.ListBoards.ListBoardDao;
import com.xtrello.Dao.ListBoards.ListBoardDaoImpl;
import com.xtrello.Dao.SharedBoard.SharedBoardDao;
import com.xtrello.Dao.SharedBoard.SharedBoardDaoImpl;
import com.xtrello.Dao.SharedListBoardDaoImpl;
import com.xtrello.Dao.SharedListBoardsDao;
import com.xtrello.Dao.User.UserDao;
import com.xtrello.Dao.User.UserDaoImpl;
import com.xtrello.Dao.listcard.ListCardDao;
import com.xtrello.Dao.listcard.ListCardDaoImpl;
import com.xtrello.models.Board;
import com.xtrello.models.ListBoard;
import com.xtrello.models.ListCard;
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
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "BoardsServlet", urlPatterns = {"/Board/*"})
public class BoardsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        BoardDaoImpl boardDao=new BoardDaoImpl();
        ListBoardDao listBoardDao=new ListBoardDaoImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDao userDao=new UserDaoImpl();
        CardDao cardDao=new CardDaoImpl();
        SharedListBoardsDao sharedListBoardsDao=new SharedListBoardDaoImpl();
        SharedBoardDao sharedBoardDao=new SharedBoardDaoImpl();

        switch (request.getPathInfo()){
            case "/createBoard":

                String name = new String(request.getParameter("name").getBytes("iso-8859-1"),
                        "UTF-8");
                long radio=Long.parseLong(request.getParameter("optionsRadios"));
                out.println("str  =  "+name+"  id ="+radio);
                if (radio==1){
                    boardDao.createBoardNotListBoard(name, user.getId() );
                }else{
                    boardDao.createBoard(name,radio,user.getId());
                };
                //response.sendRedirect("/");
                out.println("<h3>Поки що все норм</h3>");

                break;
            case "/createListBoard":
                String namelistboard = request.getParameter("name");
                String text = request.getParameter("text");
                System.out.println("Вивід даних в  консоль "+namelistboard+user.getId()+text);
                out.println("name "+namelistboard+"  id"+user.getId());
                listBoardDao.createListBoard(namelistboard,user.getId(),text);
                response.sendRedirect("/");
                break;
            case "/createListCard":
                ListCardDao listCardDao=new ListCardDaoImpl();
                String namelistcard=request.getParameter("namelistcard");
                long idboard=Long.parseLong(request.getParameter("id"));
                out.println("назва списку"+namelistcard+"  id дошки  "+idboard);
                listCardDao.createListCard(namelistcard,idboard);
                response.sendRedirect("/Board/Board?id="+idboard+"");
                break;
            case "/createCard":

                String namecard=request.getParameter("namecard");
                long idlistcard=Long.parseLong(request.getParameter("id"));
                out.println("назва карточки "+namecard+"  id списку  "+idlistcard);
                cardDao.createCard(namecard,idlistcard);
                break;

            case "/addUsertoboard":
                String emailuser=request.getParameter("emailuser");
                long idboardtoadduser=Long.parseLong(request.getParameter("optionsRadios"));
                out.write("0"+idboardtoadduser+"0");
                Board boardadd=boardDao.getBoardByBoardId(idboardtoadduser);
                User adduser=userDao.findUserByEmail(emailuser);
                out.println(adduser.getId()+"       "+boardadd.getListBoard_id());
                sharedBoardDao.addUserInSharedBoards(adduser.getId(),idboardtoadduser);

                if(boardadd.getListBoard_id()!=1) {
                    sharedListBoardsDao.addUserInSharedListBoards(adduser.getId(), boardadd.getListBoard_id());
                }
                break;
            case "/createComentar":
                String comentar=request.getParameter("comentar");
                long idcard= Long.parseLong(request.getParameter("optionsRadios"));
                cardDao.updateComentar(comentar,idcard);
                response.sendRedirect("/");
                break;

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        IndexView indexView = new IndexView();
        SharedListBoardsDao sharedListBoardsDao=new SharedListBoardDaoImpl();
        BoardDao boardDao=new BoardDaoImpl();
        CardDao cardDao = new CardDaoImpl();
        User user = (User) session.getAttribute("user");
        List<ListBoard> lstListBoard =sharedListBoardsDao.getListBoardsByUserId(user.getId());

        switch (request.getPathInfo()){
            case "/createListBoard":
                indexView.outCreateListBoard(out);

                break;
            case "/createBoard":
                String radio=request.getParameter("optionsRadios");
                String id=request.getParameter("id");
                out.println("str"+radio+"  id ="+id);


                //indexView.outCreateBoard(t);
                out.write("<H1>Create Board</H1>");

                break;

            case "/updateBoard":

                out.write("<H1>Update Board</H1>");

                break;
            case "/Board":
                long idboard=Long.parseLong(request.getParameter("id"));
                String nameboard=request.getParameter("name");

                ListCardDao listCard=new ListCardDaoImpl();

                List<ListCard> lstCard=listCard.getListCardByBoardId(idboard);

                String strcard=lstCard.stream()
                        .map(e->{
                            String str=indexView.outListCard(e);
                            return str;
                        })
                        .collect(Collectors.joining(""));
                out.println("<div class=\"row\">");
                out.println(strcard);
                out.println(indexView.outCreateListCard(idboard));
                out.println("</div>");
                out.println("<a href=\"/Board/Board/Delete?id="+idboard+"\" class=\"btn btn-warning  role=\"button\"> Видалити Дошку</a>");
                out.println(indexView.outAddUserToBoard(nameboard,idboard));
                break;
            case "/allBoard":
                out.println("listboard id= ");
                long idlistboard= Long.parseLong(request.getParameter("id"));

                out.println("<div class=\"row\">");
                out.println(indexView.outBoard(boardDao.getBoardByListBoardId(idlistboard)));
                out.println("</div>");
                out.println("<a href=\"/Board/allBoard/ListBoard/Delete?id="+idlistboard+"\" class=\"btn btn-warning  role=\"button\"> Видалити список дошок </a>");
                out.println(indexView.outCreateBoardBottom(lstListBoard));
                break;
            case "/allBoard/ListBoard/Delete":

                ListBoardDao listBoardDao=new ListBoardDaoImpl();
                long idlistboardfordelete = Long.parseLong(request.getParameter("id"));

                listBoardDao.deleteListBoard(idlistboardfordelete);
                break;

            case "/Board/Card":
                long idcard= Long.parseLong(request.getParameter("id"));
                out.println(idcard);
                out.println("<a href=\"/Board/Board/Card/Delete?id="+idcard+"\" class=\"btn btn-warning  role=\"button\"> Видалити карточку</a>");
                indexView.outupdateComentar(out,idcard);
                break;
            case"/Board/Card/Delete":
                long idcardfordelete = Long.parseLong(request.getParameter("id"));
                cardDao.deleteCardByCardId(idcardfordelete);
                break;

            case"/Board/Delete":
                ListCardDao listCardDao=new ListCardDaoImpl();
                long idboardfordelete = Long.parseLong(request.getParameter("id"));
                //TODO Fullbuster видалення всіх карточок
                String deletecard=listCardDao.getListCardByBoardId(idboardfordelete).stream()
                        .map(e->{ cardDao.deleteCardByListCardId(e.getId());
                            return "";
                        })
                        .collect(Collectors.joining(""));

                listCardDao.deleteListCardByBoard_Id(idboardfordelete);
                boardDao.deleteBoard(idboardfordelete);
                break;
            case "addUsertoboard":
                String emailuser=request.getParameter("emailuser");
                long idboardtoadduser=Long.parseLong(request.getParameter("optionsRadios"));
                out.println("doget "+emailuser+"   "+idboardtoadduser);
                break;



        }



    }
}
