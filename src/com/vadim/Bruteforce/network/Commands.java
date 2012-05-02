package com.vadim.Bruteforce.network;

/**
 * User: Vadim | Date: 02.05.12 | Time: 22:20
 */
public class Commands {

    // All codes -> [0]

    // Codes: client -> server
    public static final byte REQUEST_FOR_AUTHORIZATION = 1;
    public static final byte REQUEST_FOR_COUNTINTERVALS = 2;
    public static final byte REQUEST_FOR_LASTPASSWORD = 4;
    public static final byte REQUEST_FOR_INTERVAL = 8;
    public static final byte MESSAGE_OF_SUCCESSFUL_BRUTE = 16;

    // Codes: server -> client
    public static final byte MESSAGE_OF_STOP = 32;

}
