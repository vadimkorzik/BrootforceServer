package com.vadim.Bruteforce;

import com.vadim.Bruteforce.core.BruteforceManager;
import com.vadim.Bruteforce.network.NetworkManager;

import java.io.IOException;

/**
 * User: Vadim | Date: 14.04.12 | Time: 20:51
 */

public class Main {
    public static final Logger logger = new Logger();

    public static void main(String[] args) throws IOException {
        BruteforceManager bm = new BruteforceManager(150, "ZZZZZZ");
        NetworkManager nm = new NetworkManager(bm);
        nm.start();
    }
}
