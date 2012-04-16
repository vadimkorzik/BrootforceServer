package com.vadim.Brootforce.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
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

    private SocketAddress address;
    private ServerSocketChannel serverChannel;
    private SocketChannel socketChannel;
    Selector selector;

    private ByteBuffer readBuffer = ByteBuffer.allocate(100);

    public NetworkManager() {
    }

    public void open() throws IOException {
        selector = Selector.open();
        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(PORT));
        log(serverChannel.toString());
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);


        while (true) {
            selector.select();

            for (SelectionKey sk : selector.selectedKeys()) {
                if (!sk.isValid()) {
                    continue;
                }
                if (sk.isAcceptable()) {
                    accept(sk);
                } else if (sk.isWritable()) {
                    write(sk);
                } else if (sk.isReadable()) {
                    read(sk);
                }
                selector.selectedKeys().remove(sk);
            }
        }
    }

    private void accept(SelectionKey selectionKey) throws IOException {
        if (selectionKey == null) {
            log("accept: selectionKey is NULL");
            return;
        }

        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

        SocketChannel socketChannel = serverSocketChannel.accept();

        if (socketChannel == null) {
            log("accept: socketChannel is NULL");
            return;
        }

        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey selectionKey) throws IOException {
        if (selectionKey == null) {
            log("read: selectionKey is NULL");
            return;
        }

        readBuffer.clear();

        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        int numberRead;
        try {
            numberRead = socketChannel.read(readBuffer);
        } catch (IOException ioex) {
            socketChannel.close();
            selectionKey.cancel();
            log("Remote connection was closed.");
            return;
        }

        if (numberRead == -1) {
            socketChannel.close();
            selectionKey.cancel();
            log("The channel has reached end-of-stream.");
            return;
        }

        if (numberRead > 0) {
            readBuffer.flip();
            StringBuilder sb = new StringBuilder();
            while (readBuffer.hasRemaining())
                sb.append(readBuffer.getInt()).append(" ");
            log("# Request: " + sb);
        }

        socketChannel.register(selector, SelectionKey.OP_WRITE);
        socketChannel.close();
        selectionKey.cancel();
    }

    private void write(SelectionKey selectionKey) throws IOException {
        if (selectionKey == null) {
            log("write: selectionKey is NULL");
            return;
        }

        ByteBuffer writeBuffer = ByteBuffer.allocate(100);
        writeBuffer.clear();
        writeBuffer.putInt(120);
        writeBuffer.putInt(7792);
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        writeBuffer.flip();
        socketChannel.write(writeBuffer);


        //socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // socketChannel.close();
        selectionKey.cancel();
    }
}
