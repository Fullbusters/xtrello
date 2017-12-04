package com.xtrello.views;

import com.xtrello.Dao.Card.CardDao;
import com.xtrello.Dao.Card.CardDaoImpl;
import com.xtrello.models.*;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

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
        public String  outCreateBoard( ){
        String str=HtmlSingleton.getCreateBoard();

        return str;

        }
        public String outCreateBoardBottom(List<ListBoard> lstlistboard){
            String str=HtmlSingleton.getCreateBoardBottom();
            String forReplase=lstlistboard.stream()
                    .map(e->{
                        String str2="<div class=\"radio\">\n" +
                                "                                    <label>\n" +
                                "                                        <input type=\"radio\" name=\"optionsRadios\" id=\"optionsRadios1\" value=\""+e.getId()+"\" checked>\n" +
                                "                                        "+e.getName()+"\n" +
                                "                                    </label>\n" +
                                "                                </div>";
                        return str2;
                    })
                    .collect(Collectors.joining(""));
            str=str.replace("<!--input-->",forReplase);

            return  str;
        }

        public void outCreateListBoard(PrintWriter out){
            out.println(HtmlSingleton.getCreateListBoard());
        }
        public String outListBoard( ListBoard listBoards){

                         String str2 =
                         "<div class=\"col-xs-12 col-sm-12\"\n" +
                         " <h1> " +listBoards.getName()+ "</h1>\n" +
                         "<a href=\"/Board/allBoard?id="+listBoards.getId()+"\" class=\"btn btn-primary  role=\"button\">Дошки</a>\n" +
                          "</div>\n"
                          ;



            return str2;

        }
        public String outBoard(List <Board> lstboard){

            String row =lstboard.stream()
                .map(e->{
                    String str=
                            "<div class=\" col-sm-3\""+
                            "<p>\n" +
                            "  <a href=\"/Board/Board?id="+e.getId()+"&name="+e.getName()+"\" type=\"button\"   class=\"btn btn-primary btn-lg board\" >"+e.getName()+"</a>\n" +
                            "</p>"+
                            "</div>"
                            ;
                    return str;
                })
                    .collect(Collectors.joining(""));

            return row+outCreateBoard();
        }
        public String outListCard(ListCard lstCard){
            CardDao cardDao=new CardDaoImpl();
            List<Card> card =cardDao.getCardByListCardId(lstCard.getId());
            String strcard=card.stream()
                    .map(e->"<p>"+outCard(e)+"</p>")
                    .collect(Collectors.joining(""));
             String str=
                                "<div class=\"col-sm-4\">\n"+
                                " <div class=\"panel panel-primary\">\n" +
                                "            <div class=\"panel-heading\">\n" +
                                "              <h3 class=\"panel-title\"> "+lstCard.getName()+"</h3>\n" +
                                "            </div>\n" +
                                "            <div class=\"panel-body\">\n" +
                                "            "+strcard+" "+
                                        outCreateCard(lstCard.getId())+
                                " <a href=\"/ListCard/Delete?idlistcard="+lstCard.getId()+"&idboard="+lstCard.getBoard_id()+"\" class=\"btn btn-warning  role=\"button\"> Видалити список карточок</a>;"+
                                "            </div>\n" +
                                "          </div>\n" +
                                "        </div>\n"
                                ;
            return str;
        }
        public String outCreateListCard( long idboard){

            String str="<div class=\"col-sm-4\">"+
                    " <form action=\"/Board/createListCard?id="+idboard+"\" method=\"POST\">"+
                    "<div class=\"list-group\">\n" +
                    "                            <a class=\"list-group-item active\"  >\n" +
                    "                                <h5 class=\"list-group-item-heading\">Створити список </h5>\n" +
                    "                            </a>"+
                    "                       <a class=\"list-group-item\">\n" +
                    "                                <div class=\"form-group\">\n" +
                    "                                    <label for=\"namelistcard\">Назва</label>\n" +
                    "                                    <input type=\"name\" class=\"form-control\" id=\"namelistcard\" name=\"namelistcard\" placeholder=\"Назва списку \">\n" +
                    "</a>" +
                    "                            <div>\n" +
                    "                                <button type=\"submit\" class=\"btn btn-default btn-block\">Створити список</button>\n" +
                    "                            </div>\n" +
                    "                        </div>"+
                    "                                </div>"+
                    "</form>"+
                    "        </div>"
                    ;
             return str;

        }
        public String outCard(Card card){
            String str="<div class=\"col-sm-12\">\n" +
                    "          <div class=\"list-group\">\n" +
                    "           <a href=\"/Board/Board/Card?id="+card.getId()+"\" class=\"list-group-item\">\n" +
                    "              <h4 class=\"list-group-item-heading\">"+card.getName()+"</h4>\n" +
                    "           </a>\n" +
                    "          </div>\n" +
                    "        </div>";



            return str;
    }
        public String outCreateCard(long idlistcard){
            String str="<div class=\"col-sm-12\">\n"+
                " <form action=\"/Board/createCard?id="+idlistcard+"\" method=\"POST\">\n"+
                "<div class=\"list-group\">\n" +
                "                            <a class=\"list-group-item active\"  >\n" +
                "                                <h5 class=\"list-group-item-heading\">Створити карточку </h5>\n" +
                "                            </a>"+
                "                       <a class=\"list-group-item\">\n" +
                "                                <div class=\"form-group\">\n" +
                "                                    <label for=\"namecard\">Назва</label>\n" +
                "                                    <input type=\"name\" class=\"form-control\" id=\"namecard\" name=\"namecard\" placeholder=\"Назва карточки \">\n" +
                "</div>\n"+
                "</a>\n" +
                "                            <div>\n" +
                "                                <button type=\"submit\" class=\"btn btn-default btn-block\">Створити карточку</button>\n" +
                "                            </div>\n" +
                "                        </div>\n"+
                "                                </div>\n"+
                "</form>\n"
        ;

          return str;

    }

    //TODO form card inside
//    public String outCardinside(long idcard){
//        String str="";
//        return str;
//    }

        public String outAddUserToBoard(String nameboard,long idboard){
        String str="<form action=\"/Board/addUsertoboard\" method=\"POST\" class=\"form-horizontal\">"+
                "<div class=\"col-lg-6\">\n" +
                "    <div class=\"input-group\">\n" +
                "      <input type=\"text\" class=\"form-control\" name=\"emailuser\" placeholder=\"email користувача якого добавляють\">\n" +
                "      <span class=\"input-group-btn\">\n" +
                "        <button type=\"submit\" class=\"btn btn-default\" type=\"button\">Go!</button>\n" +
                "           <div class=\"radio\" > +\n" +
                "                <label>\n" +
                "                    <input type=\"radio\" name=\"optionsRadios\" id=\"optionsRadios1\" value=\""+idboard+"\" checked>n\"" +
                "                    "+nameboard+"\n" +
                "               </label>\n" +
                "           </div>"+
                "      </span>\n" +
                "    </div><!-- /input-group -->\n" +
                "  </div>\n"+
                "</form>";

        return str;
        }

}
