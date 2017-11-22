package com.xtrello.controller;

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
        HttpSession session = request.getSession();
        indexView.outTopPage(out);
        indexView.outMenu(out, session);
        out.write("<h3>Hello Start</h3>");
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
        System.out.println("Path\t" + pathHTML.getPath());
    }
}
