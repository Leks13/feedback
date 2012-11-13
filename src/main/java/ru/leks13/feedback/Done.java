package ru.leks13.feedback;

import org.apache.click.Page;


public class Done extends Page{
    public Done() {
        String text = "Your message recorded.";
        addModel("text", text);
    }
   
}
