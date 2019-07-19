package network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

    private final static int PORT = 13;
    private static final String HOSTNAME = "192.168.1.10";


    public static void main(String[] args) throws Exception {
        //传入0表示让操作系统分配一个端口号
        DatagramSocket socket = new DatagramSocket(0);
        String reqStr = "iamclient";
        DatagramPacket request = new DatagramPacket(reqStr.getBytes(), reqStr.length(), InetAddress.getByName(HOSTNAME), PORT);
        socket.send(request);

        //为接受的数据包创建空间
        DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
        socket.receive(response);
        String result = new String(response.getData(), 0, response.getLength(), "ASCII");
        System.out.println("服务器端返回数据为：" + result);
    }
}
