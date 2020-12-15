package com.study.nio;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandle implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public TimeClientHandle(String host, int port) {
        this.port = port;
        this.host = host;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public void run() {
        try {
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    iterator.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect()) {
                    try {
                        sc.register(selector, SelectionKey.OP_READ);
                        doWriter(sc);
                    } catch (ClosedChannelException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("连接失败");
                    System.exit(1);
                }
            }
            if (key.isReadable()) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int read = sc.read(buffer);
                if(read>0){
                    //position=0
                    //limit=之前的postion
                    buffer.flip();
                    //limit-position
                    byte[] bytes=new byte[buffer.remaining()];
                    buffer.get(bytes);
                    String body=new String(bytes,"UTF-8");
                    System.out.println("now is :"+body);
                    this.stop=true;
                }else if(read<0){
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doWriter(SocketChannel sc) throws IOException {
        byte[] bytes = "query time order".getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        sc.write(buffer);
        if(!buffer.hasRemaining()){
            System.out.println("send order 2 server succeed");
        }
    }

    private void doConnect() throws IOException {
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            socketChannel.register(selector,SelectionKey.OP_READ);
            doWriter(socketChannel);
        }else{
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }
}
