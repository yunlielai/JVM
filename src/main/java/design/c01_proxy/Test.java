package design.c01_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import design.c01_proxy.cglib.HelloCglibProxy;
import design.c01_proxy.design.HelloProxy;
import design.c01_proxy.java.HelloJavaProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//代理演示
public class Test {
    public static void main(String[] args) {
        //testDesign();
        //testJava();
        testCglib();
        //testOutOfMemoryError();
    }
    public static void testDesign(){
        Hello hello=new HelloImpl();
        HelloProxy proxy=new HelloProxy(hello);
        proxy.say();
    }
    public static void testJava(){
        Hello hello=new HelloImpl();
        HelloJavaProxy javaProxy=new HelloJavaProxy(hello);
        Hello proxyHello=(Hello)Proxy.newProxyInstance(hello.getClass().getClassLoader(),hello.getClass().getInterfaces(),javaProxy);
        proxyHello.say();
    }
    public static void testCglib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloImpl.class);
        enhancer.setUseCache(false);
        enhancer.setCallback(new HelloCglibProxy());
        Hello hello=(Hello)enhancer.create();
        hello.say();
    }

    /**
     *测试方法区溢出
     * -XX:PermSize=1m -XX:MaxPermSize=1m
     */

    public static void testOutOfMemoryError(){
        boolean flag=true;
        while (flag) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(HelloImpl.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,objects);
                }
            });
            enhancer.create();
        }
    }
}

