package com.vadim.Bruteforce.network;

import com.vadim.Bruteforce.core.BruteforceManager;

import java.nio.ByteBuffer;

/**
 * User: Vadim | Date: 04.05.12 | Time: 21:23
 */
public class CommandHandler {
    BruteforceManager bruteforceManager = null;

    public void setBruteforceManager(BruteforceManager bruteforceManager) {
        this.bruteforceManager = bruteforceManager;
    }

    public boolean process(ByteBuffer byteBuffer) {
        try {
            byte command = 0;

            command = byteBuffer.get(0);

            switch (command) {
                case Commands.REQUEST_FOR_AUTHORIZATION:

                    break;
                case Commands.REQUEST_FOR_COUNTINTERVALS:

                    break;

                case Commands.REQUEST_FOR_LASTPASSWORD:

                    break;

                case Commands.REQUEST_FOR_INTERVAL:

                    break;

                case Commands.MESSAGE_OF_SUCCESSFUL_BRUTE:

                    break;

                case Commands.REQUEST_FOR_SHA1HASH:

                    break;
            }

        } catch (Exception ignored) {
        }
        return true;
    }
}
