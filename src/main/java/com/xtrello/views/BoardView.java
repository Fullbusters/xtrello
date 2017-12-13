package com.xtrello.views;

import com.xtrello.dao.card.CardDao;
import com.xtrello.dao.card.CardDaoImpl;
import com.xtrello.models.Board;
import com.xtrello.models.Card;
import com.xtrello.models.ListBoard;
import com.xtrello.models.ListCard;

import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class BoardView {
    private HtmlSingleton HtmlSingleton;

    public BoardView() {HtmlSingleton = HtmlSingleton.getInstance();}

    /**
     *  Метод який виводить на сторінку  всі дошки які є в команді
     * @param out Змінна виводу
     * @param lstboard список дошок які будемо виводити
     */
    public void outBoard(PrintWriter out,List<Board> lstboard){

        String row =lstboard.stream()
                .map(e->{
                    out.write("<div class=\" col-sm-3\""+
                            "<p>\n" +
                            "  <a href=\"/Board/Board?id="+e.getId()+"\" type=\"button\"   class=\"btn btn-primary btn-lg board\" >"+e.getName()+"</a>\n" +
                            "</p>"+
                            "</div>")
                    ;
                    return "";
                })
                .collect(Collectors.joining(""));

        outCreateBoard(out);
    }
    /**
     *  Метод який виводить кнопку створення дошки на сайті
     * @param out Змінна виводу
     */
    public void   outCreateBoard(PrintWriter out ){
        out.write(HtmlSingleton.getCreateBoard());
    }

    /**
     *  Метод який виводить на сторінку команду яка є у користувача
     * @param out Змінна виводу
     * @param listBoards команда
     */
    public void outListBoard(PrintWriter out, ListBoard listBoards){


        out.write( "<div class=\"col-xs-12 col-sm-12\"\n" +
                " <h1> " +listBoards.getName()+ "</h1>\n" +
                "<a href=\"/Board/allBoard?id="+listBoards.getId()+"\" class=\"btn btn-primary  role=\"button\">Всі дошки команди</a>\n" +
                "</div>\n");

    }
    /**
     *  Виводить на екран форму додавання користувача до дошки
     * @param out Змінна виводу
     * @param idboard id дошки до якої ми будемо додавати користувача
     */
    public void outAddUserToBoard(PrintWriter out,long idboard){

        out.write("<form action=\"/Board/addUsertoboard\" method=\"POST\" class=\"form-horizontal\">"+
                "<div class=\"col-lg-6\">\n" +
                "    <div class=\"input-group\">\n" +
                "      <input type=\"text\" class=\"form-control\" name=\"emailuser\" placeholder=\"email користувача якого добавляють\">\n" +
                "      <span class=\"input-group-btn\">\n" +
                "        <button type=\"submit\" class=\"btn btn-default\" type=\"button\">Додати користувача до дошки !</button>\n" +
                "           <div class=\"radio\" hidden > +\n" +
                "                <label>\n" +
                "                    <input type=\"radio\" name=\"optionsRadios\" id=\"optionsRadios1\" value=\""+idboard+"\" checked hidden>n\"" +
                "               </label>\n" +
                "           </div>"+
                "      </span>\n" +
                "    </div><!-- /input-group -->\n" +
                "  </div>\n"+
                "</form>");
    }

    /**
     *  Виводить на екран форму додавання користувача до команди
     * @param out Змінна виводу
     * @param idlistboard id команди до якої ми будемо додавати користувача
     */
    public void outAddUserToListBoard(PrintWriter out,long idlistboard){
        out.write("<form action=\"/Board/addUserToListBoard\" method=\"POST\" class=\"form-horizontal\">"+
                "<div class=\"col-lg-6\">\n" +
                "    <div class=\"input-group\">\n" +
                "      <input type=\"text\" class=\"form-control\" name=\"emailuser\" placeholder=\"email користувача якого добавляють\">\n" +
                "      <span class=\"input-group-btn\">\n" +
                "        <button type=\"submit\" class=\"btn btn-default\" type=\"button\">Додати користувача до команди !</button>\n" +
                "           <div class=\"radio\" hidden > +\n" +
                "                <label>\n" +
                "                    <input type=\"radio\" name=\"optionsRadios\" id=\"optionsRadios1\" value=\""+idlistboard+"\" checked hidden>n\"" +
                "               </label>\n" +
                "           </div>"+
                "      </span>\n" +
                "    </div><!-- /input-group -->\n" +
                "  </div>\n"+
                "</form>");

    }
    /**
     *  Виводить на екран список карточок та карточки які розташоані в цьому списку
     * @param out Змінна виводу
     * @param lstCard список карточок
     */
    public void outListCard(PrintWriter out,ListCard lstCard){
        CardDao cardDao=new CardDaoImpl();
        List<Card> card =cardDao.getCardByListCardId(lstCard.getId());
        String strcard=card.stream()
                .map(e->"<p>"+outCard(e)+"</p>")
                .collect(Collectors.joining(""));
        String str=
                "<div class=\"col-sm-4 listcard\">\n"+
                        " <div class=\"panel panel-primary\">\n" +
                        "            <div class=\"panel-heading\">\n" +
                        "              <h3 class=\"panel-title\"> "+lstCard.getName()+"</h3>\n" +
                        "            </div>\n" +
                        "            <div class=\"panel-body\">\n" +
                        "            "+strcard+" "+
                        outCreateCard(lstCard)+
                        " <a href=\"/ListCard/Delete?idlistcard="+lstCard.getId()+"&idboard="+lstCard.getBoard_id()+"\" class=\"btn btn-warning  role=\"button\"> Видалити список карточок</a>;"+
                        "            </div>\n" +
                        "          </div>\n" +
                        "        </div>\n"
                ;
        out.write(str);
    }

    /**
     *  Виводить на сторінку форму створення списку карточок
     * @param out Змінна виводу
     * @param idboard id дошки в якій ми створюємо список карточок
     */
    public void outCreateListCard(PrintWriter out, long idboard){

        out.write("<div class=\"col-sm-4 listcard\" >"+
                " <form action=\"/ListCard/createListCard?id="+idboard+"\" method=\"POST\">"+
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
                "        </div>");

    }

    /**
     *
     * @param card карточка яка буде записана в код
     * @return частину html коду який буде використовуватись в виводі списку карточок
     */
    private String outCard(Card card){
        String str="<div class=\"col-sm-12\">\n" +
                "          <div class=\"list-group\">\n" +
                "           <a href=\"/Card/content?id="+card.getId()+"\" class=\"list-group-item\">\n" +
                "              <h4 class=\"list-group-item-heading\">"+card.getName()+"</h4>\n" +
                "           </a>\n" +
                "          </div>\n" +
                "        </div>";



        return str;
    }

    /**
     *   метод який створює качтину html коду і передає його дальше
     * @param listcard список карточок  в якому ми створюємо карточку
     * @return частину коду який ми будемо використовувати в списку карточок
     */
    private String outCreateCard(ListCard listcard){

        String str="<div class=\"col-sm-12\">\n"+
                " <form action=\"/Card/createCard?id="+listcard.getId()+"&idboard="+listcard.getBoard_id()+"\" method=\"POST\">\n"+
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

    /**
     *  метод який буде добавляти в нижню частину сторінки інформацію про список команд в користувача (використовується тільки для створення дошки )
     * @param out Змінна виводу
     * @param lstlistboard список команд користувача
     */
    public void outCreateBoardBottom(PrintWriter out,List<ListBoard> lstlistboard){
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

        out.write(str);
    }
}
