package com.xtrello.controller;

import com.xtrello.views.IndexView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VxidServlet", urlPatterns = {"/Vxid" })
public class VxidServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        IndexView indexView = new IndexView();

        indexView.outTopPage(out);
        indexView.outVxid(out);
        indexView.outBottomPage(out);
    }
}
