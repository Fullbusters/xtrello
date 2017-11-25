package com.xtrello.views;

import com.xtrello.models.ListBoard;
import com.xtrello.models.User;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        public String outListBoard(PrintWriter out, ListBoard listBoards){

                         String str2 = "<div class=\"row\">"+
                         "<div class=\"col-xs-12 col-sm-12\"+" +
                         " \"<h1>\" " +listBoards.getName()+ "\"</h1>" +
                         "<a href=\"/Board/allInListBoard\" class=\"btn btn-primary  role=\"button\">Дошки</a>" +
                          "<a href=\"/Board/createBoard?id="+listBoards.getId()+"\" class=\"btn btn-primary  role=\"button\">Створити дошку</a>"+
                          "</div>"+
                          "</div>";



            return str2;

        }

}
