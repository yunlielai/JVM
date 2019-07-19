package network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class udpServer {
    private final static int PORT = 13;

    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket(PORT);
        while (true) {
            DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
            socket.receive(request);
            System.out.println( "请求信息：地址=" + request.getAddress()+" 端口="+request.getPort()+"长度="+request.getLength()+" 数据="+new String(request.getData()));

            byte[] data = new Date().toString().getBytes("ASCII");
            DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
            socket.send(response);
        }
    }
}
