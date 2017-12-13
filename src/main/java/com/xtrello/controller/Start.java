package com.xtrello.controller;

import com.xtrello.dao.board.BoardDao;
import com.xtrello.dao.board.BoardDaoImpl;
import com.xtrello.dao.sharedlistboards.SharedListBoardDaoImpl;
import com.xtrello.dao.sharedlistboards.SharedListBoardsDao;
import com.xtrello.models.ListBoard;
import com.xtrello.models.User;
import com.xtrello.views.BoardView;
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
import java.util.logging.*;
import java.util.stream.Collectors;

/**
 * Серлет головної сторінки
 * Виводить 2 різні меню для залогіненого користувача і ні
 * також серлет стартує при запуску програми і ініціалізує шлях до наших html файлій та конфігурація логуання
 */

@WebServlet(name = "Start", urlPatterns = {"/"}, loadOnStartup = 1)
public class Start extends HttpServlet {
    private static Logger log = Logger.getLogger(Start.class.getName());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        IndexView indexView = new IndexView();
        BoardView boardView=new BoardView();
        SharedListBoardsDao sharedListBoardsDao=new SharedListBoardDaoImpl();
        BoardDao boardDao=new BoardDaoImpl();
        HttpSession session = request.getSession();
        indexView.outTopPage(out);
        indexView.outMenu(out, session);
        User user = (User) session.getAttribute("user");
        /**
         * перевірка чи залогінений користувач
         */
        if(user == null) {
            // тут вивід інформації для не зареєстрованих користувачів
            out.write("<h3>Trello Вітає вас! Зареєструйтесь щоб почати роботу з ним.</h3>");


        }else {
            // тут вивід інформації для зареєстрованих користувачів

            // колекція команд
            List<ListBoard> lstListBoard =sharedListBoardsDao.getListBoardsByUserId(user.getId());
            out.write("<h4> Персональні дошки <h4>");
            out.println("<div class=\"row\">");
            // вивід на сторінку всіх приватних дошок
            boardView.outBoard(out,boardDao.getBoardByUserId(user.getId()));
            // вивід на сторінку всіх команд та дошок які знаходяться в них
            String  row =lstListBoard.stream()
                    .map(e->{
                        boardView.outListBoard(out,e);boardView.outBoard(out,boardDao.getBoardByListBoardId(e.getId()));
                        return "";
                    })
                    .collect(Collectors.joining(""));
            out.println("</div>");
            // виводить кнопку створення команди
            indexView.outCreateListBoard(out);
            // виводить нижню частину html сторінки в де описано кількість команд в користувача(Потрібно для створення дошки)
            boardView.outCreateBoardBottom(out,lstListBoard);
            // логування
            log.fine("Output index page for user"+user.getEmail());

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
        pathHTML.setCreateBoardBottom("createBoardBottom.html");
        pathHTML.setUpdateComentar("cardComentar.html");
        System.out.println("Path\t" + pathHTML.getPath());

        try {

            Handler fh = new FileHandler(getServletContext()
                    .getRealPath("/logs/app.log"));
            Logger.getLogger("").addHandler(fh);
            Logger.getLogger("").addHandler(new ConsoleHandler());
            Logger.getLogger("").setLevel(Level.ALL);

        } catch (IOException e) {
            e.printStackTrace();
        }

        log.config("Created log file " + getServletContext()
                .getRealPath("/logs/app.log"));
    }
}
