package network.tcp.lone;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
            new Thread(new MyRun(s)).start();
        }
    }

    static class MyRun implements Runnable{
        private Socket s;

        public MyRun(Socket s) {
            this.s=s;
        }

        @Override
        public void run() {
            //读取客户端发送来的消息
            String mess = null;
            try {
                BufferedReader reader=new BufferedReader(new InputStreamReader(s.getInputStream()));
                String lineStr;
                while (!(lineStr = reader.readLine()).equals("bye")) {
                    System.out.println("客户端请求信息：" + lineStr);
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
                    pw.println(lineStr);
                    pw.flush();
                }
                System.out.println("服务器端关闭socket:"+s);
                reader.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
