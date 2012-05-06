package com.vadim.Bruteforce.network;

import com.vadim.Bruteforce.core.BruteforceManager;

import java.nio.ByteBuffer;

/**
 * User: Vadim | Date: 04.05.12 | Time: 21:23
 */
public class CommandHandler {
    BruteforceManager bruteforceManager = null;

    public CommandHandler(BruteforceManager bruteforceManager) {
        this.bruteforceManager = bruteforceManager;
    }

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

                    byteBuffer.clear();
                    byteBuffer.putInt(1, bruteforceManager.getCountIntervals());

                    break;

                case Commands.REQUEST_FOR_LASTPASSWORD:

                    byteBuffer.clear();
                    Commands.stringToBuffer(bruteforceManager.getLastPassword(), byteBuffer);

                    break;

                case Commands.REQUEST_FOR_INTERVAL:

                    byteBuffer.clear();
                    if (bruteforceManager.isSuccess())
                        byteBuffer.put(0, (byte) 1);
                    else {
                        byteBuffer.put(0, (byte) 0);
                        byteBuffer.putInt(1, bruteforceManager.getInterval());
                    }

                    break;

                case Commands.MESSAGE_OF_SUCCESSFUL_BRUTE:

                    bruteforceManager.success(Commands.stringFromBuffer(byteBuffer));

                    break;

                case Commands.REQUEST_FOR_SHA1HASH:

                    byteBuffer.clear();
                    Commands.stringToBuffer(bruteforceManager.getSha1Hash(), byteBuffer);

                    break;
            }

        } catch (Exception ignored) {
            // todo add catch
        }
        return true;
    }
}
