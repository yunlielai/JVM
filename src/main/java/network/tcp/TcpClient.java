package network.tcp;

import network.StreamUtil;

import java.io.*;
import java.net.Socket;

public class TcpClient {
    private final static int PORT = 9999;
    private static final String HOSTNAME = "192.168.1.10";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOSTNAME ,PORT);
        int count=1;
        PrintWriter pw=new PrintWriter(socket.getOutputStream());
        pw.println("i am client dfd");
        //需要提供结束标识供服务器读取数据时判断是否数据已经传输完毕,跳出循环
        pw.println("eof");
        //注意写入数据后要进行flush 不然服务器获取不到流数据会一直阻塞
        pw.flush();
        String ret=StreamUtil.parseInputStreamToString(socket.getInputStream());
        System.out.println("服务器端响应的数据为："+ret);
    }
}
