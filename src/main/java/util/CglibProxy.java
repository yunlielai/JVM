package util;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 通过cglib代理的方式计算方法的执行时间
 */
@SuppressWarnings("unchecked")
public class CglibProxy implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object ret = methodProxy.invokeSuper(o, objects);
        Long endTime = System.currentTimeMillis();
        System.out.println("方法 " + method.getName() + "耗时：" + (endTime - startTime) + "； 返回值为：" + ret);
        return ret;
    }

    public static <T> T getProxyObj(Class<T> t) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t);
        enhancer.setCallback(new CglibProxy());
        T retObj = (T) enhancer.create();
        return retObj;
    }

    public static <T> T getProxyObjForArgs(Class<T> t, Class[] args, Object[] argsValue) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t);
        enhancer.setCallback(new CglibProxy());
        T retObj = (T) enhancer.create(args,argsValue);
        return retObj;
    }
}

