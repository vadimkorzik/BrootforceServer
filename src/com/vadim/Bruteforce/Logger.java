package com.vadim.Bruteforce;

import javax.swing.*;

/**
 * User: Vadim | Date: 16.04.12 | Time: 20:27
 */
public class Logger {

    DefaultListModel<String> model;

    public static void log(String msg) {
        System.out.println("> " + msg);
    }

    public void setListModel(DefaultListModel<String> model) {
        this.model = model;
    }

    private String getCurrentTime() {
        return new java.text.SimpleDateFormat("dd.MM.yyyy '-' HH:mm:ss").format(java.util.Calendar.getInstance().getTime());
    }

    private String simpleMessage(String type, String msg) {
        return ("[" + this.getCurrentTime() + "] " + type + msg);
    }

    public void message(String msg) {
        model.addElement(simpleMessage("", msg));
    }

    public void warning(String msg) {
        model.addElement(simpleMessage("[WARNING] ", msg));
    }

    public void error(String msg) {
        model.addElement(simpleMessage("[ERROR] ", msg));
    }
}
