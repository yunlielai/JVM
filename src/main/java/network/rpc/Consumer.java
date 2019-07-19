package network.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class Consumer {
    private final static int PORT = 9999;
    private static final String HOSTNAME = "127.0.0.1";

    public static void main(String[] args) throws Exception {
        Class cHello=SayHelloService.class;
        String interfaceName=cHello.getName();
        Method method=cHello.getMethod("sayHello", String.class);
        Object[] arguments={"hello"};
        Socket socket=new Socket(HOSTNAME,PORT);
        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
        out.writeUTF(interfaceName);
        out.writeUTF(method.getName());
        out.writeObject(method.getParameterTypes());
        out.writeObject(arguments);

        ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
        Object result=in.readObject();
        System.out.println(result);
    }
}
