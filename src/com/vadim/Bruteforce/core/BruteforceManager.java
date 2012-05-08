package com.vadim.Bruteforce.core;

import com.vadim.Bruteforce.GUI.MainGUI;
import com.vadim.Bruteforce.Main;

/**
 * User: Vadim | Date: 14.04.12 | Time: 21:52
 */
public class BruteforceManager {

    /**
     * Count of intervals
     */
    private int countIntervals;

    /**
     * Current interval of passwords to send to clients for bruteforce
     */
    private int currentInterval;

    private boolean bruteforceSuccess = false;

    /**
     * last password to bruteforce
     */
    private String lastPassword = "ZZZZZZ";

    private String sha1Hash = "9865d483bc5a94f2e30056fc256ed3066af54d04";// "8fc6c915676049e2dddd2626b48725191d4064e2";

    private String password = null;

    MainGUI mainGUI;

    public BruteforceManager(int countIntervals, String lastPassword, String sha1Hash) {
        this.countIntervals = countIntervals;
        this.lastPassword = lastPassword;
        this.sha1Hash = sha1Hash;
        currentInterval = 0;
    }

    public int getInterval() {
        return currentInterval++;
    }

    public boolean freeIntervals() {
        return currentInterval < countIntervals;
    }

    public String getSha1Hash() {
        return sha1Hash;
    }

    public int getCountIntervals() {
        return countIntervals;
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public void success(String password) {
        bruteforceSuccess = true;
        this.password = password;
        Main.logger.message("Soccess brute: " + password);
        mainGUI.success(password);
    }

    public boolean isSuccess() {
        return bruteforceSuccess;
    }

    public String getPassword() {
        return password;
    }

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }
}
