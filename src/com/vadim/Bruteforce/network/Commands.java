package com.vadim.Bruteforce.network;

import com.vadim.Bruteforce.Main;

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
    public static final byte REQUEST_FOR_SHA1HASH = 32;

    // Codes: server -> client
    public static final byte MESSAGE_OF_STOP = 64;

    /**
     * Copy String to ByteBuffer from 2 index <b>(not 0)</b> .
     * Automatic allocate memory.
     * Length of str <= 127 !!
     *
     * @param str    String to send
     * @param buffer buffer to send
     */
    public static void stringToBuffer(String str, ByteBuffer buffer) {
        byte[] bytes = str.getBytes();
        int i = 2;
        try {
            for (byte b : bytes) {
                buffer.put(i, b);
                i++;
            }
            buffer.put(1, (byte) str.length());
        } catch (ArrayIndexOutOfBoundsException e) {
            buffer = ByteBuffer.allocate(bytes.length + 2);
            Main.logger.warning("stringToBuffer: Buffer not allocated!");
            Commands.stringToBuffer(str, buffer);
        }

    }

    /**
     * Copy String from ByteBuffer from 2 index <b>(not 0)</b> .
     *
     * @param buffer ByteBuffer from client
     * @return Decoded String from buffer
     */
    public static String stringFromBuffer(ByteBuffer buffer) {
        byte length = buffer.get(1);
        byte[] bytes = new byte[length];
        try {
            for (int i = 0; i < length; i++) {

                bytes[i] = buffer.get(i + 2);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Main.logger.error("stringFromBuffer: Buffer not allocated!");
            return null;
        }

        return new String(bytes);
    }
}
