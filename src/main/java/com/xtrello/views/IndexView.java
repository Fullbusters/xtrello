package com.xtrello.views;

import com.xtrello.models.User;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class IndexView {


    private HtmlSingleton HtmlSingleton;

    public IndexView() {
        HtmlSingleton = HtmlSingleton.getInstance();
    }

    /**
     *  Метод який виводить верхню частину html сторінки
     * @param out Змінна виводу
     */
    public void outTopPage(PrintWriter out){
        out.println(HtmlSingleton.getTop());
    }

    /**
     *  Метод який виводить нижню частину  html сторінки
     * @param out Змінна виводу
     */
    public void outBottomPage(PrintWriter out){
        out.println(HtmlSingleton.getBottom());
    }

    /**
     *  Метод який буде перевіряти залогінений користувач чи ні і виводити 2 різні менюшки
     * @param out Змінна виводу
     * @param session параметр який буде зберігати в собі дані про користувача
     */
    public  void outMenu(PrintWriter out, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null) {
            out.println(HtmlSingleton.getMenuforguest());

        } else {

            out.println(HtmlSingleton.getMenu());

        }
    }

    /**
     *  Метод який виводить кнопку створення команди на сайті
     * @param out Змінна виводу
     */
    public void outCreateListBoard(PrintWriter out){
        out.println(HtmlSingleton.getCreateListBoard());
    }

    /**
     *  Метод який виводить сторінку реестрації на сайті
     * @param out Змінна виводу
     */
    public void outReg(PrintWriter out,String value){
        String register=HtmlSingleton.getReg();
        if (value==null){
            out.println(register);
        }else  if(value.equals("1") ){
            String eroremail="<div class=\"alert alert-warning\">\n" +
                    "  <strong>Warning!</strong> Неправильный email\n" +
                    "</div>";
            register=register.replace("<!--eroor-->",eroremail);
            out.println(register);
        }else  if(value.equals("2")){
            String erorpass="<div class=\"alert alert-warning\">\n" +
                    "  <strong>Warning!</strong> Неправильный пароль\n" +
                    "</div>";
            register=register.replace("<!--eroor-->",erorpass);
            out.println(register);

        }
       }
    /**
     *  Метод який виводить сторінку логіна на сайті
     * @param out Змінна виводу
     */
    public void outLogin(PrintWriter out,String value){
        if(value==null){
            out.println(HtmlSingleton.getLogin());
        }else{
            String login = HtmlSingleton.getLogin();
            String str = "<div class=\"alert alert-warning\">\n" +
                    "  <strong>Warning!</strong> Неправильный логин или пароль\n" +
                    "</div>";
            login = login.replace("<!--aaa-->",str);
            out.println(login);
        }
        }

}
