package com.vadim.Bruteforce;

import com.vadim.Bruteforce.GUI.MainGUI;
import com.vadim.Bruteforce.core.BruteforceManager;
import com.vadim.Bruteforce.network.NetworkManager;

import javax.swing.*;
import java.io.IOException;

/**
 * User: Vadim | Date: 14.04.12 | Time: 20:51
 */

public class Main {
    public static final Logger logger = new Logger();

    public static void main(String[] args) throws IOException {
        //BruteforceManager bm = new BruteforceManager(100, "ZZZZ", "8fc6c915676049e2dddd2626b48725191d4064e2");
        //NetworkManager nm = new NetworkManager(bm);
        //nm.start();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGUI mainGUI = new MainGUI();
            }
        });
    }
}
