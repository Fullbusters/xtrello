package com.xtrello.controller;

import com.xtrello.Dao.Board.BoardDao;
import com.xtrello.Dao.Board.BoardDaoImpl;
import com.xtrello.Dao.ListBoards.ListBoardDao;
import com.xtrello.Dao.ListBoards.ListBoardDaoImpl;
import com.xtrello.Dao.SharedListBoardDaoImpl;
import com.xtrello.Dao.SharedListBoardsDao;
import com.xtrello.models.Board;
import com.xtrello.models.ListBoard;
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
import java.util.stream.Collector;
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
        switch (request.getPathInfo()){
            case "/createBoard":
                String id=request.getParameter("id");
                String name = request.getParameter("name");

                out.println("name "+name+"  id"+id);
                //boardDao.createBoard(name, id );
                out.println("<h3>Поки що все норм</h3>");

                break;
            case "/createListBoard":
                String namelistboard = request.getParameter("name");
                String text = request.getParameter("text");
                System.out.println("Вивід даних в  консоль "+namelistboard+user.getId()+text);
                out.println("name "+namelistboard+"  id"+user.getId());
               // listBoardDao.createListBoard(namelistboard,user.getId(),text);
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
        User user = (User) session.getAttribute("user");
        out.println("<a href=\"/Board/printBoard\" class=\"btn btn-primary  role=\"button\"> Всі дошки користувача</a>");



        switch (request.getPathInfo()){
            case "/":
                out.write("<H1>Hello User!</H1>");
                break;
            case "/createListBoard":
                indexView.outCreateListBoard(out);

                break;
            case "/createBoard":
                String id=request.getParameter("id");
                out.println("  id ="+id);

                indexView.outCreateBoard(out);
                out.write("<H1>Create Board</H1>");

                break;
            case "/printBoard":

                List<ListBoard> lstListBoard =sharedListBoardsDao.getListBoardsByUserId(user.getId());

                String  row =lstListBoard.stream()
                        .map(e->{
                        String str2="<p>"+indexView.outListBoard(out,e)+" "+boardDao.printBoard(e.getId())+"</p>";
                        return str2;
                        })
                        .collect(Collectors.joining(""));


                out.println(row);
                out.println("<a href=\"/Board/createListBoard\" class=\"btn btn-primary  role=\"button\"> Створити Команду</a>");
                break;
            case "/updateBoard":

                out.write("<H1>Update Board</H1>");

                break;
        }



    }
}
