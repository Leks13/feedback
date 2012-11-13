package ru.leks13.feedback;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.click.Page;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;

public class Add extends Page {

    public Form form = new Form();

    public Add() {
        form.add(new TextField("E-mail:", true));
        form.add(new TextField("Phone:", true));
        form.add(new TextField("Message:", true));
        form.add(new Submit("ok", "  OK  ", this, "onOkClick"));
        form.add(new Submit("cancel", this, "onCancelClick"));
    }

    public boolean onOkClick() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        if (form.isValid()) {
            Sql bd = new Sql();
            bd.addMessage(form.getField("E-mail:").getValue(), form.getField("Phone:").getValue(), form.getField("Message:").getValue());
            setRedirect(Done.class);
        }
        return true;
    }

    public boolean onCancelClick() {
        setRedirect(Index.class);
        return false;
    }
}

