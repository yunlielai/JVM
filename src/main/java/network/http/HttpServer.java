package network.http;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class HttpServer {

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

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bw.write(getResponeInfo());

            bw.flush();
            s.close();
        }
    }

    public static String parseInputStreamToString(InputStream in) throws IOException, InterruptedException {
        //StringBuffer sb = new StringBuffer();
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        String ret = new String(bytes);
        //sb.append(bytes);
        return ret;
    }

    public static String getResponeInfo() {
        StringBuffer rep = new StringBuffer();
        //状态行
        rep.append("HTTP/1.1 200 ok\r\n");
        //首部行
        rep.append("Data:" + new Date() + "\r\n");
        rep.append("Set-cookie:" + "luobiao\r\n");
        //空行，表示后面的内容为返回内容
        rep.append("\r\n");
        //返回内容
        rep.append("{'name':'luobiao'}");
        return rep.toString();
    }
}