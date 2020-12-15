package com.study.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file=new RandomAccessFile("D://hello.txt","rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(1, (byte) 'X');
        file.close();
    }
}
