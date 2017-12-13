package com.xtrello.views;

import com.xtrello.models.Card;

import java.io.PrintWriter;

public class CardView {
    private HtmlSingleton HtmlSingleton;

    public CardView() {HtmlSingleton = HtmlSingleton.getInstance(); }

    /**
     * Метод який виводить на екран  html сторінку з вмістом карточки
     * @param out Змінна виводу
     * @param card Карточка
     */
    public void outcontentCard(PrintWriter out, Card card) {
        String str="<div class=\"row\">\n" +
                "  <div class=\"col-xs-12 col-sm-12 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4\">" +
                "<div class=\"page-header\">\n" +
                "        <h1>Вміст карточки !</h1>\n" +
                "      </div>\n" +
                "      <div class=\"well\">\n" +
                "        <p>"+card.getComentar()+"</p>\n" +
                "      </div>"+
                "      </div>"+
                "</div>";
        String forReplace="<div class=\"radio\" hidden>\n" +
                "                                    <label hidden >\n" +
                "                                        <input type=\"radio\" name=\"optionsRadios\" id=\"optionsRadios1\" value=\""+card.getId()+"\" checked hidden>\n" +
                "                                        "+card.getId()+"\n" +
                "                                    </label>\n" +
                "                                </div>\n";
        str=str+HtmlSingleton.getUpdateComentar();
        str=str.replace("<!--ccbn-->",forReplace);
        out.println(str);}
}
