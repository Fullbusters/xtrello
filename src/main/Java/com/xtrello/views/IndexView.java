package com.xtrello.views;

import com.xtrello.models.User;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class IndexView {


        private HtmlSingleton HtmlSingleton;

        public IndexView() {
            HtmlSingleton = HtmlSingleton.getInstance();
        }

        public void outTopPage(PrintWriter out){
            out.println(HtmlSingleton.getTop());
        }

        public void outBottomPage(PrintWriter out){
            out.println(HtmlSingleton.getBottom());
        }

        public  void outMenu(PrintWriter out, HttpSession session){
            User user = (User) session.getAttribute("user");
            if(user == null) {
                out.println(HtmlSingleton.getMenuforguest());

            } else {
                out.println(HtmlSingleton.getMenu());

            }
            //out.println(HtmlSingleton.getMenu());
        }
        public void outReg(PrintWriter out){out.println(HtmlSingleton.getReg());}
        public void outLogin(PrintWriter out){out.println(HtmlSingleton.getLogin());}
        public void outCreateBoard(PrintWriter out){
            out.println(HtmlSingleton.getCreateBoard());
        }
        public void outCreateListBoard(PrintWriter out){
            out.println(HtmlSingleton.getCreateListBoard());
        }

}
