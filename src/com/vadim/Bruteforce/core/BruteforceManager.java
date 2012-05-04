package com.vadim.Bruteforce.core;

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

    private String sha1Hash;

    private String password = null;

    public BruteforceManager(int countIntervals, String lastPassword) {
        this.countIntervals = countIntervals;
        this.lastPassword = lastPassword;
        currentInterval = 0;
    }

    public int getInterval() {
        return ++currentInterval;
    }

    public boolean freeIntervals() {
        return currentInterval < countIntervals;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public String getSha1Hash() {
        return sha1Hash;
    }

    public void setSha1Hash(String sha1Hash) {
        if (!sha1Hash.equals(this.sha1Hash))
            bruteforceSuccess = false;
        this.sha1Hash = sha1Hash;
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
    }

    public boolean isSuccess() {
        return bruteforceSuccess;
    }

    public String getPassword() {
        return password;
    }
}
