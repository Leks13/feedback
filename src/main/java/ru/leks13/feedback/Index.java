package ru.leks13.feedback;

import org.apache.click.Page;

public class Index extends Page {

    public Index() {
        String info = "Welcome to Main Page! <br>"
                + " If your user - go to <a href=\"add.htm\">THIS page</a>. <br>"
                + "If your admin - go to <a href=\"list.htm\">THIS page</a>. <br>";
        addModel("info", info);
    }
   
}
