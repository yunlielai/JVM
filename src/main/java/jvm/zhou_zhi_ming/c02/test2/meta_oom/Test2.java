package jvm.zhou_zhi_ming.c02.test2.meta_oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * VM Args:-XX:MetaspaceSize=80m -XX:MaxMetaspaceSize=80m
 * 调小元空间大小
 * 会抛出异常
 * Caused by: java.lang.OutOfMemoryError: Metaspace
 * 而非
 * Caused by:java.lang.OutOfMemoryError:PermGen space
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(10);
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {

    }
}
