package com.xtrello.controller;

import com.xtrello.views.HtmlSingleton;
import com.xtrello.views.IndexView;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Головний фільтр для виводу статичних частин html сторінки
 */
@WebFilter(filterName = "IndexForGuestFilter", urlPatterns = {"/","/home" })
public class IndexForGuestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HtmlSingleton pathHtmlSingleton = HtmlSingleton.getInstance();

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        IndexView indexView = new IndexView();

        indexView.outTopPage(out);
        indexView.outMenuforguest(out);
        //servlet
        chain.doFilter(request, response);
        //низ html сторінки
        indexView.outBottomPage(out);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
