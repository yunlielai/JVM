package network.rpc.lf;

public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        //此处只是获取目标服务的代理对象，并不会发送Socket调用目标服务
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        System.out.println("获取代理对象完毕");
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            //此处才真正发送Socket 请求
            String hello = service.hello("World" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
