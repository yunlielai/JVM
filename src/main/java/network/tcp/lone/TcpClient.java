package network.tcp.lone;

import java.io.*;
import java.net.Socket;

public class TcpClient {
    private final static int PORT = 9999;
    private static final String HOSTNAME = "192.168.1.10";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOSTNAME, PORT);
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        new Thread(new MyRun(socket.getInputStream())).start();
        String inStr;
        while (!(inStr = reader.readLine()).equals("bye")) {
            pw.println(inStr);
            pw.flush();
        }
        pw.println("bye");
        pw.flush();
        System.out.println("客户端关闭socket："+socket);
        pw.close();
        reader.close();
        socket.close();
    }


    static class MyRun implements Runnable{
        private InputStream in;

        public MyRun(InputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            String retStr;
            try {
                while ((retStr = reader.readLine())!=null) {
                    System.out.println("服务器返回消息："+retStr);
                }
                System.out.println("客户端关闭输入流！");
                in.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }
}
