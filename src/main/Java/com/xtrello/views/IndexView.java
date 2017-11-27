package com.xtrello.views;

import com.xtrello.Dao.Card.CardDao;
import com.xtrello.Dao.Card.CardDaoImpl;
import com.xtrello.models.*;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        public String  outCreateBoard(ListBoard listBoard){
        String str=HtmlSingleton.getCreateBoard();
        long id=listBoard.getId();
        return str;
        }
        public void outCreateListBoard(PrintWriter out){
            out.println(HtmlSingleton.getCreateListBoard());
        }
        public String outListBoard( ListBoard listBoards){

                         String str2 =
                         "<div class=\"col-xs-12 col-sm-12\"" +
                         " <h1> " +listBoards.getName()+ "</h1>" +
                         "<a href=\"/Board/allBoard?id="+listBoards.getId()+"\" class=\"btn btn-primary  role=\"button\">Дошки</a>" +
                          "</div>"
                          ;



            return str2;

        }
        public String outBoard(List <Board> lstboard){
            String row =lstboard.stream()
                .map(e->{
                    String str=
                            "<div class=\" col-sm-3\""+
                            "<p>\n" +
                            "  <a href=\"/Board/Board?id="+e.getId()+"\" type=\"button\" class=\"btn btn-primary btn-lg\">"+e.getName()+"</a>\n" +
                            "</p>"+
                            "</div>"
                            ;
                    return str;
                })
                    .collect(Collectors.joining(""));

            return row;
        }
        public String outListCard(ListCard lstCard){
            CardDao cardDao=new CardDaoImpl();
            List<Card> card =cardDao.getCardByListCardId(lstCard.getId());
            String strcard=card.stream()
                    .map(e->"<p>"+outCard(e)+"</p>")
                    .collect(Collectors.joining(""));
             String str=
                                "<div class=\"col-sm-4\">"+
                                " <div class=\"panel panel-primary\">" +
                                "            <div class=\"panel-heading\">" +
                                "              <h3 class=\"panel-title\"> "+lstCard.getName()+"</h3>" +
                                "            </div>" +
                                "            <div class=\"panel-body\">" +
                                "            "+strcard+" "+
                                "            </div>" +
                                "          </div>" +
                                "        </div>"

                                ;


            return str;
        }

    public String outCard(Card card){
            String str="<div class=\"col-sm-12\">\n" +
                    "          <div class=\"list-group\">" +
                    "           <a href=\"/Board/Board/Card\" class=\"list-group-item\">\n" +
                    "              <h4 class=\"list-group-item-heading\">"+card.getName()+"</h4>" +
                    "           </a>\n" +
                    "          </div>\n" +
                    "        </div>";



            return str;
    }


}
