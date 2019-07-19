package design.c01_proxy.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloJavaProxy implements InvocationHandler {
    private Object target;

    public HelloJavaProxy(Object target) {
        this.target=target;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("java before");
        return method.invoke(target,args);
    }
}
