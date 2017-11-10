package com.xtrello.views;

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

        public  void outMenu(PrintWriter out){
            out.println(HtmlSingleton.getMenu());
        }
        public void outMenuforguest(PrintWriter out){
            out.println(HtmlSingleton.getMenuforguest());

        }

}
