package network.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import static network.StreamUtil.parseInputStreamToString;

/**
 * Created by Administrator on 2015/8/7.
 */
public class TcpServer {
    private final static int PORT = 9999;

    public static void main(String[] args) throws Exception {
        try {
            startHttpServer(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startHttpServer(int port) throws Exception {
        ServerSocket ss = new ServerSocket(PORT);
        System.out.println("启动服务器....");
        while (true) {
            Socket s = ss.accept();
            //读取客户端发送来的消息
            String mess = parseInputStreamToString(s.getInputStream());
            System.out.println("客户端请求信息：" + mess);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            pw.println("data="+new Date());
            pw.flush();
            s.close();
        }
    }
}
