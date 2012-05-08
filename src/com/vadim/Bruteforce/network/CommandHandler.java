package com.vadim.Bruteforce.network;

import com.vadim.Bruteforce.Main;
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
                    Main.logger.message("REQUEST_FOR_AUTHORIZATION from client");
                    break;
                case Commands.REQUEST_FOR_COUNTINTERVALS:

                    byteBuffer.clear();
                    byteBuffer.putInt(1, bruteforceManager.getCountIntervals());
                    Main.logger.message("REQUEST_FOR_COUNTINTERVALS from client");
                    break;

                case Commands.REQUEST_FOR_LASTPASSWORD:

                    byteBuffer.clear();
                    Commands.stringToBuffer(bruteforceManager.getLastPassword(), byteBuffer);
                    Main.logger.message("REQUEST_FOR_LASTPASSWORD from client");
                    break;

                case Commands.REQUEST_FOR_INTERVAL:

                    int interval;
                    byteBuffer.clear();
                    if (bruteforceManager.isSuccess()) {
                        interval = -1;
                        byteBuffer.put(0, (byte) 0);
                        byteBuffer.putInt(1, -1);
                    } else {
                        interval = bruteforceManager.getInterval();
                        byteBuffer.put(0, (byte) 0);
                        byteBuffer.putInt(1, interval);
                    }
                    Main.logger.message("REQUEST_FOR_INTERVAL from client: " + interval);
                    break;

                case Commands.MESSAGE_OF_SUCCESSFUL_BRUTE:

                    bruteforceManager.success(Commands.stringFromBuffer(byteBuffer));
                    Main.logger.message("MESSAGE_OF_SUCCESSFUL_BRUTE from client");
                    break;

                case Commands.REQUEST_FOR_SHA1HASH:

                    byteBuffer.clear();
                    Commands.stringToBuffer(bruteforceManager.getSha1Hash(), byteBuffer);
                    Main.logger.message("REQUEST_FOR_SHA1HASH from client");
                    break;
            }

        } catch (Exception ignored) {
            // todo add catch
        }
        return true;
    }
}
