package com.xtrello.controller;

import com.xtrello.Dao.Board.BoardDao;
import com.xtrello.Dao.Board.BoardDaoImpl;
import com.xtrello.Dao.SharedListBoardDaoImpl;
import com.xtrello.Dao.SharedListBoardsDao;
import com.xtrello.models.Board;
import com.xtrello.models.ListBoard;
import com.xtrello.models.User;
import com.xtrello.views.HtmlSingleton;
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

/**
 * Start servlet
 */

@WebServlet(name = "Start", urlPatterns = {"/"}, loadOnStartup = 1)
public class Start extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        IndexView indexView = new IndexView();
        SharedListBoardsDao sharedListBoardsDao=new SharedListBoardDaoImpl();
        BoardDao boardDao=new BoardDaoImpl();
        HttpSession session = request.getSession();
        indexView.outTopPage(out);
        indexView.outMenu(out, session);
        User user = (User) session.getAttribute("user");
        if(user == null) {
        out.write("<h3>Hello Start</h3>"+
        "<button type=\"button\" class=\"btn btn-default\">Підготовлена</button>");

        }else {
            List<ListBoard> lstListBoard =sharedListBoardsDao.getListBoardsByUserId(user.getId());

            String  row =lstListBoard.stream()
                    .map(e->{
                        String str2="<p>"+indexView.outListBoard(e)+" "+indexView.outBoard(boardDao.getBoardByListBoardId(e.getId()))+/*" "+indexView.outCreateBoard(e)+*/"</p>";
                        return str2;
                    })
                    .collect(Collectors.joining(""));

            out.println("<div class=\"row\">");
            out.println(row);
            out.println("</div>");
            out.println("<a href=\"/Board/createListBoard\" class=\"btn btn-primary  role=\"button\"> Створити Команду</a>");

        }
        indexView.outBottomPage(out);

    }

    @Override
    public void init() throws ServletException {
        super.init();

        HtmlSingleton pathHTML = HtmlSingleton.getInstance();
        if(pathHTML.getPath().equals("")) {
            pathHTML.setPath(getServletContext().getRealPath("/html/"));
        }
        pathHTML.setTop("top.html");
        pathHTML.setMenu("menu.html");
        pathHTML.setBottom("bottom.html");
        pathHTML.setMenuforguest("menuforguest.html");
        pathHTML.setReg("reg.html");
        pathHTML.setLogin("login.html");
        pathHTML.setCreateBoard("createBoard.html");
        pathHTML.setCreateListBoard("createListBoard.html");
        System.out.println("Path\t" + pathHTML.getPath());
    }
}
