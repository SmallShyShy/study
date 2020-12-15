package com.study.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Channel01 {
    public static void main(String[] args) throws IOException {
        channelChansfer();
    }
    public static void writeFile() throws IOException {
        String str="hello,world";
        FileOutputStream fos=new FileOutputStream("D://hello.txt");
        FileChannel channel = fos.getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        buffer.flip();
        channel.write(buffer);
        fos.close();
    }
    public static void readFile() throws IOException {
        File file=new File("D://hello.txt");
        FileInputStream fis=new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        ByteBuffer buffer=ByteBuffer.allocate((int) file.length());
        int read = channel.read(buffer);
        String str=new String(buffer.array());
        System.out.println(str);
        fis.close();
    }
    public static void copyFile() throws IOException {
        File file=new File("D://hello.txt");
        FileInputStream fis=new FileInputStream(file);
        FileChannel channel = fis.getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(512);

        FileOutputStream fos=new FileOutputStream("D://hellocopy.txt");
        FileChannel channel1 = fos.getChannel();

        while (true){
            buffer.clear();
            int read = channel.read(buffer);
            if(read==-1){
                break;
            }
            buffer.flip();
            channel1.write(buffer);
        }

        fis.close();
        fos.close();
    }
    public static   void channelChansfer() throws IOException {
        File f=new File("D://study.rar");
        File fc=new File("D://studyCopy.rar");
        FileInputStream fis=new FileInputStream(f);
        FileOutputStream fos=new FileOutputStream(fc);

        FileChannel cfis = fis.getChannel();
        FileChannel cfos = fos.getChannel();

        cfos.transferFrom(cfis,0,cfis.size());

        cfis.close();
        cfos.close();
        cfis.close();
        cfos.close();
    }
}
