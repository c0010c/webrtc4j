package com.qs;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) throws IOException {
        //InetSocketAddress bindaddr = new InetSocketAddress("192.168.50.117", 8888);
        DatagramSocket datagramSocket = new DatagramSocket(8888);

        byte[] buf = new byte[1024];
        DatagramPacket p = new DatagramPacket(buf, 1024);
        datagramSocket.receive(p);
        System.out.println(new String(buf, "UTF-8"));
    }
}