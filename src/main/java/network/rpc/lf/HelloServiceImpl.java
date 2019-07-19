package network.rpc.lf;

public class HelloServiceImpl implements HelloService{
    public String hello(String name) {
        return "Hello " + name;
    }
}
