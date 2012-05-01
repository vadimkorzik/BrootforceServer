package com.vadim.Bruteforce.core;

/**
 * User: Vadim | Date: 14.04.12 | Time: 21:52
 */
public class BruteforceManager {

    private int countIntervals;
    private int currentInterval;

    public BruteforceManager(int countIntervals) {
        this.countIntervals = countIntervals;
        currentInterval = 0;
    }

    public int getCurrentInterval() {
        return currentInterval;
    }

    public void incCurrentInterval() {
        currentInterval++;
    }

    public boolean freeIntervals() {
        return currentInterval < countIntervals;
    }
}
