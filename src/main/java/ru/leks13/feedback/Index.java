package ru.leks13.feedback;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.click.Page;

public class Index extends Page {

    public Index() throws IOException, ClassNotFoundException, SQLException {
       Sql sql = new Sql();
       sql.greateBase();
        String info = "Welcome to Main Page! <br>"
                + " If your user - go to <a href=\"add.htm\">THIS page</a>. <br>"
                + "If your admin - go to <a href=\"list.htm\">THIS page</a>. <br>";
        addModel("info", info);
    }
   
}
