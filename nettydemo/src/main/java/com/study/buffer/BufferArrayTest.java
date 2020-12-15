package com.study.buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class BufferArrayTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(7000));

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel socket = serverChannel.accept();
        int message = 8;
        while (true) {
            System.out.println("------started-----------");
            int readByte = 0;
            while (readByte < message) {
                System.out.println("-------receive--------");
                long l = (int) socket.read(byteBuffers);
                readByte += l;
                System.out.println("readByte=" + readByte);
                Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                    System.out.println("position=" + byteBuffer.position() + ",limit=" + byteBuffer.limit());
                });
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            long writeByte = 0;
            while (writeByte < message) {
                System.out.println("--------write-------");
                long l = socket.write(byteBuffers);
                writeByte += l;
            }
            Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                byteBuffer.clear();
            });
            System.out.println("read="+readByte+",write="+writeByte+",message="+message);
        }

    }
}
