package com.qs.webrtc.ice;

import com.qs.webrtc.ice.constants.ProtocolEnum;
import com.qs.webrtc.stun.StunMsgHandler;
import com.qs.webrtc.stun.constants.Stun;
import com.qs.webrtc.stun.model.StunMsg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public class IceAgent {

    private StunMsgHandler stunMsgHandler = new StunMsgHandler();

    //private Map<String, DatagramSocket> socketMap = new HashMap<>();
    private DatagramSocket selectedConn;

    private ArrayBlockingQueue<DatagramPacket> msgQueue = new ArrayBlockingQueue<>(1024);


    public IceAgent() throws IOException {
        new Thread(()->{
            System.out.println("Network thread initialled.");
            while (true) {
                try {
                    selectedConn = new DatagramSocket(8888);
                    while (true) {
                        byte buf[] = new byte[2048];
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);
                        selectedConn.receive(packet);
                        msgQueue.put(packet);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println("work thread initialled.");
            while (true) {
                try {
                    DatagramPacket take = msgQueue.take();
                    IceAgent.this.onReceived(take);
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }).start();
    }

    public void onReceived(DatagramPacket packet) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(packet.getLength());
        byteBuffer.put(packet.getData(), 0, packet.getLength());
        byteBuffer.flip();
        ProtocolEnum protocol = this.getProtocol(byteBuffer);
        byteBuffer.position(0);
        if (protocol.equals(ProtocolEnum.STUN)) {
            //判断是类型。
            byteBuffer.position(0);
            byte[] respBytes = stunMsgHandler.handle(byteBuffer);
            packet.setData(respBytes);
            packet.setLength(respBytes.length);
            selectedConn.send(packet);
        }
        //allocate.put(packet.getData())
        //getProtocol(packet.getData())
        System.out.println("received");

    }


    private ProtocolEnum getProtocol(ByteBuffer buf) {
        int int1 = buf.getInt();
        int int2 = buf.getInt();
        if (int1 >> 30 == 0 && int2 == Stun.MAGIC_COOKIE) {

            return ProtocolEnum.STUN;
        }
        return null;
    }

}
