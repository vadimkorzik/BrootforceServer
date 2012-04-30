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
    private ServerSocketChannel  serverChannel;
    private SocketChannel socketChannel;
    Selector selector;

    private ByteBuffer readBuffer = ByteBuffer.allocate(100);

    public NetworkManager() {
    }

    public void open() throws IOException {

    }

}
