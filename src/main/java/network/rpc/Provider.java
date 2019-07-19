package network.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Provider {
    private static int Port=9999;
    private static Map<String,Object> serviceMap=new HashMap<String, Object>();
    static {
        serviceMap.put("network.rpc.SayHelloService",new SayHelloServiceImpl());
    }
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket(Port);

        while (true) {
            Socket socket=serverSocket.accept();
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            String interfaceName=in.readUTF();
            String methodName=in.readUTF();
            Class[] paramsTypes=(Class[])in.readObject();
            Object[] arguments=(Object[])in.readObject();
            Class cHello=Class.forName(interfaceName);
            Method method=cHello.getMethod(methodName,paramsTypes);
            Object ret=method.invoke(getService(interfaceName),arguments);
            ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(ret);
        }
    }

    public static Object getService(String interfaceName) {
        Object obj=serviceMap.get(interfaceName);
        return obj;
    }
}
