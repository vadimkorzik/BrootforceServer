package com.vadim.Bruteforce.network;

import java.nio.ByteBuffer;

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

    /**
     * Copy String to ByteBuffer from 1 index <b>(not 0)</b> .
     * Automatic allocate memory
     *
     * @param str    String to send
     * @param buffer buffer to send
     */
    public static void stringToBuffer(String str, ByteBuffer buffer) {
        byte[] bytes = str.getBytes();
        int i = 1;
        begin:
        for (byte b : bytes) {
            try {
                buffer.put(i, b);
                i++;
            } catch (ArrayIndexOutOfBoundsException e) {
                buffer = ByteBuffer.allocate(bytes.length + 2);
                Commands.stringToBuffer(str, buffer);
            }
        }
    }
}
