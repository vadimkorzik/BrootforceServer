package com.vadim.Brootforce;

import com.vadim.Brootforce.network.NetworkManager;

import java.io.IOException;

/**
 * User: Vadim | Date: 14.04.12 | Time: 20:51
 */
public class Main {
    public static void main(String[] args) throws IOException {
        NetworkManager nm = new NetworkManager();
        nm.open();
    }
}
