package com.xtrello.controller;

import com.xtrello.Dao.Board.BoardDaoImpl;
import com.xtrello.Dao.ListBoards.ListBoardDaoImpl;
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

@WebServlet(name = "BoardsServlet", urlPatterns = {"/Board/*"})
public class BoardsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        BoardDaoImpl boardDao=new BoardDaoImpl();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        switch (request.getPathInfo()){
            case "/createBoard":

                String name = request.getParameter("name");
                out.println("name "+name+"  id"+user.getId());
                boardDao.createBoard(name, user.getId() );
                out.println("<h3>Поки що все норм</h3>");

                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ListBoardDaoImpl listBoardDao=new ListBoardDaoImpl();
        BoardDaoImpl boardDao=new BoardDaoImpl();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        out.println("<a href=\"/Board/createBoard\" class=\"btn btn-primary  role=\"button\">Створити дошку</a>");
        out.println("<a href=\"/Board/printBoard\" class=\"btn btn-primary  role=\"button\"> Всі дошки користувача</a>");


        switch (request.getPathInfo()){
            case "/":
                out.write("<H1>Hello User!</H1>");
                break;
            case "/createBoard":

                IndexView indexView = new IndexView();
                indexView.outCreateBoard(out);
                out.write("<H1>Create Board</H1>");

                break;
            case "/printBoard":
                ListBoard listBoard=listBoardDao.printListBoard(user.getId());
                out.println("<h4>"+listBoard.getName()+"</h4>");
                Board board=boardDao.printBoard(listBoard.getId());
                out.println("<h5> Тут ще норм </h5>");
                out.println("<h4>"+board.getName()+"</h4>");



                break;
            case "/updateBoard":

                out.write("<H1>Update Board</H1>");

                break;
        }



    }
}
