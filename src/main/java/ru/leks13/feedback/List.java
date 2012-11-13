package ru.leks13.feedback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.click.Page;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;

public class List extends Page {

    public Form form = new Form();

    public List() throws FileNotFoundException, IOException {
        Sql bd = new Sql();
        String res = "<table border =1> <tr><td>id</td><td>e-mail</td><td>phone</td><td>message</td><td>status</td></tr>";
        try {
            res += bd.listOfMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(List.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(List.class.getName()).log(Level.SEVERE, null, ex);
        }
        res += "</table>";
        addModel("res", res);
        form.add(new TextField("ID:", true));
        Select statusSelect = new Select("Status:");
        statusSelect.setRequired(true);
        statusSelect.add(new Option(""));
        statusSelect.add(new Option("Processed"));
        statusSelect.add(new Option("Not processed"));
        form.add(statusSelect);
        form.add(new Submit("ok", "  OK  ", this, "onOkClick"));

    }

    public boolean onOkClick() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        if (form.isValid()) {
            Sql bd = new Sql();
            bd.changeStatus(form.getField("ID:").getValue(), form.getField("Status:").getValue());
            setRedirect(List.class);
        }
        return true;
    }
}

