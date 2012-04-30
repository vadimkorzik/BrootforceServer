package com.vadim.Brootforce.network;

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

import com.vadim.Brootforce.Logger;

import static com.vadim.Brootforce.Logger.log;

/**
 * User: Vadim | Date: 14.04.12 | Time: 22:43
 */
public class NetworkManager {

    public static final int PORT = 19191;
    private ServerSocketChannel serverSocketChannel;
    Selector selector;

    private final ByteBuffer buffer = ByteBuffer.allocate(16385);

    public NetworkManager() {
    }

    public void open() throws IOException {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
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

    private boolean processInput(SocketChannel sc) throws IOException {
        buffer.clear();
        sc.read(buffer);
        buffer.flip();

        // If no data, close the connection
        if (buffer.limit() == 0) {
            return false;
        }

        // Simple rot-13 encryption
       /* for (int i = 0; i < buffer.limit(); ++i) {
            byte b = buffer.get(i);

            if ((b >= 'a' && b <= 'm') || (b >= 'A' && b <= 'M')) {
                b += 13;
            } else if ((b >= 'n' && b <= 'z') || (b >= 'N' && b <= 'Z')) {
                b -= 13;
            }

            buffer.put(i, b);
        }     */



        sc.write(buffer);

        System.out.println("Processed " + buffer.limit() + " from " + sc);

        return true;
    }

}
