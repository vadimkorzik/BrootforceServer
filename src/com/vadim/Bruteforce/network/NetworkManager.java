package com.vadim.Bruteforce.network;

import com.vadim.Bruteforce.Main;
import com.vadim.Bruteforce.core.BruteforceManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * User: Vadim | Date: 14.04.12 | Time: 22:43
 */
public class NetworkManager extends Thread {

    public static final int BUFFER_SIZE = 256;
    public static final int DEFAULT_PORT = 19191;

    private int port;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private ServerSocket serverSocket;
    private SocketChannel socketChannel;


    private BruteforceManager bruteforceManager;
    private CommandHandler commandHandler = null;

    private final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

    public NetworkManager(BruteforceManager bruteforceManager) {
        port = DEFAULT_PORT;
        this.bruteforceManager = bruteforceManager;
        commandHandler = new CommandHandler(this.bruteforceManager);
    }

    public NetworkManager(int port, BruteforceManager bruteforceManager) {
        this.port = port;
        this.bruteforceManager = bruteforceManager;
        commandHandler = new CommandHandler(this.bruteforceManager);
    }

    public void openServer() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            serverSocket = serverSocketChannel.socket();
            socketChannel = null;

            while (true) {
                int num = selector.select();

                if (num == 0)
                    continue;

                for (Iterator<SelectionKey> i = selector.selectedKeys().iterator(); i.hasNext(); ) {
                    SelectionKey key = i.next();
                    i.remove();

                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {

                        Socket socket = serverSocket.accept();
                        socketChannel = socket.getChannel();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                        System.out.println("Got connection from " + socket);

                    } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                        //socketChannel = (SocketChannel) key.channel();
                        try {

                            // It's incoming data on a connection, so
                            // process it
                            socketChannel = (SocketChannel) key.channel();
                            boolean ok = processInput(socketChannel);

                            // If the connection is dead, then remove it
                            // from the selector and close it
                            if (!ok) {
                                key.cancel();

                                Socket s = null;
                                try {
                                    s = socketChannel.socket();
                                    s.close();
                                } catch (IOException ie) {
                                    System.err.println("Error closing socket " + s + ": " + ie);
                                }
                            }

                        } catch (IOException ie) {

                            // On exception, remove this channel from the selector
                            key.cancel();

                            try {
                                socketChannel.close();
                            } catch (IOException ie2) {
                                System.out.println(ie2);
                            }

                            System.out.println("Closed " + socketChannel);
                        }
                    }
                }
            }
        } catch (Throwable e) {

        } finally {
            try {
                selector.close();
                serverSocketChannel.socket().close();
                serverSocketChannel.close();
            } catch (Exception e) {
                // do nothing - server failed
            }
        }
    }

    public void closeServer() {
        try {
            socketChannel.close();
        } catch (Exception e) {
            Main.logger.message("Can't close server");
        }
        try {
            serverSocket.close();
        } catch (Exception e) {
            Main.logger.message("Can't close server");
        }
        try {
            this.selector.close();
        } catch (Exception e) {
            Main.logger.message("Can't close server");
        }
        try {
            this.serverSocketChannel.close();
        } catch (Exception e) {
            Main.logger.message("Can't close server");
        }
        Main.logger.message("Close server");
    }

    /**
     * processing of the request
     *
     * @return successfully process
     */

    private boolean processInput(SocketChannel sc) throws IOException {
        buffer.clear();
        sc.read(buffer);

        // If no data, close the connection
        if (buffer.limit() == 0) {
            return false;
        }

        commandHandler.process(buffer);

        sc.write(buffer);

        return true;
    }

    @Override
    public void run() {
        this.openServer();
    }
}
