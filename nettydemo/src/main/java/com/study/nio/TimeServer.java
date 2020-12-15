package com.study.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);
        new Thread(multiplexerTimeServer,"NIO-001").start();
    }
}
