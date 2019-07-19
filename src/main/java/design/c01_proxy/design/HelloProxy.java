package design.c01_proxy.design;

import design.c01_proxy.Hello;

public class HelloProxy implements Hello {
    private Hello hello;

    public HelloProxy(Hello hello) {
        this.hello=hello;
    }
    public void say() {
        System.out.println("design before");
        hello.say();
    }
}
