package design.c01_proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public class HelloCglibProxy implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib before");
       Object ret= methodProxy.invokeSuper(o, objects);
        return ret;
    }
}

