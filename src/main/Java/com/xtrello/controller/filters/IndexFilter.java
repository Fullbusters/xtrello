package com.xtrello.controller.filters;

import com.xtrello.models.User;
import com.xtrello.views.HtmlSingleton;
import com.xtrello.views.IndexView;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Головний фільтр для виводу статичних частин html сторінки
 */
@WebFilter(filterName = "IndexFilter", urlPatterns = {"/AddedUser/*","/Board/*","/ListCard/*"})
public class IndexFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HtmlSingleton pathHtmlSingleton = HtmlSingleton.getInstance();
//        System.out.println("Filter path\t" + pathHtmlSingleton.getPath());
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        IndexView indexView = new IndexView();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) {
            //out.write("<a>href="//"</a>");

        }
        else {
            indexView.outTopPage(out);
            indexView.outMenu(out, session);
            //servlet
            chain.doFilter(request, response);
            //низ html сторінки
            indexView.outBottomPage(out);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
