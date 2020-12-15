package com.study.buffer;

import java.nio.IntBuffer;

public class BufferTest {
    public static void main(String[] args) {
        IntBuffer buffer=IntBuffer.allocate(10);
        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.put((int)(Math.random()*100));
        }
        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
      //  Channel
    }
}
