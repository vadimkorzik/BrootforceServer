package com.vadim.Bruteforce.network;

import com.vadim.Bruteforce.Logger;
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

    public static final int BUFFER_SIZE = 1024;
    public static final int DEFAULT_PORT = 19191;

    private int port;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private Logger log = new Logger();
    private BruteforceManager bruteforceManager;
    private CommandHandler commandHandler = null;

    // todo add shutdown server

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

            ServerSocket serverSocket = serverSocketChannel.socket();
            SocketChannel socketChannel = null;

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
        // TODO closeServer
    }

    /**
     * processing of the request
     *
     * @return successfully process
     */
    private boolean processInput(SocketChannel sc) throws IOException {
        buffer.clear();
        sc.read(buffer);
        buffer.flip();
        sc.getRemoteAddress();
        // If no data, close the connection
        if (buffer.limit() == 0) {
            return false;
        }

        System.out.println(" >read: " + buffer.get(0) + " " + buffer.get(1) + " " + buffer.get(2));

        buffer.put(0, (byte) 1);
        buffer.put(1, (byte) 2);
        buffer.put(2, (byte) 3);

        sc.write(buffer);

        System.out.println("Processed " + buffer.limit() + " from " + sc);

        return true;
    }

    @Override
    public void run() {
        this.openServer();
    }
}
