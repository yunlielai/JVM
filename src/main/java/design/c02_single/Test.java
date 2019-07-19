package design.c02_single;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Single1 饥饿单例
 * Single2 非饥饿单例(内部类实现)
 * Single3 非饥饿单例(双重检索实现)
 * Single4 饿单例且序列化序列化安全(枚举实现，能防序列化时因为序列化只会将name输出到序列化结果，反序列化时通过那么去查找枚举对象，而且枚举单例还能够防止通过反射创建实例)
 * Single5 通过重写readResolve方法解决序列化安全问题
 */
public class Test {
    public static void main(String[] args) throws Exception {
        //验证Single1,Single2,Single3是否为饥饿单例，如果执行静态的doSomeThing就执行了构造函数初始化，说明时饥饿单例
        test1();
        Thread.sleep(2000);
        System.out.println("---------------test1 end--------------");
        //验证哪些单例为序列化安全
        test2();
        System.out.println("---------------test2 end--------------");
        //验证防序列化单例类Single5是否防反射实例化
        test3();
        System.out.println("---------------test3 end--------------");
        //验证枚举实现的单例通过反射实例化会抛出异常
        test4();
    }

    public static void test1(){
        Single1.doSomeThing();
        Single2.doSomeThing();
        Single3.doSomeThing();
        Single4.doSomeThing();
    }

    public static void test2() throws Exception{
        System.out.println("single1为序列化安全? -- "+singleCompare(Single1.getInstance()));
        System.out.println("single2为序列化安全? -- "+singleCompare(Single2.getInstance()));
        System.out.println("single3为序列化安全? -- "+singleCompare(Single3.getInstance()));
        System.out.println("single4为序列化安全? -- "+singleCompare(Single4.getInstance()));
        System.out.println("single5为序列化安全? -- "+singleCompare(Single5.getInstance()));
    }

    public static void test3() throws Exception{
        Single5 single3=Single5.getInstance();
        Constructor<Single5> constructor=Single5.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Single5 single31=constructor.newInstance();
        System.out.println("反射创建出来的对象是否与单例方法获取的对象相等？"+(single3==single31));
    }

    public static void test4() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        MySingle single3=Single4.getInstance();
        Constructor<Single4> constructor=Single4.class.getDeclaredConstructor(String.class,int.class);
        constructor.setAccessible(true);
        //通过反射调用newInstance方法时，newInstance方法中会判断是不是enum 修饰，有enum修饰会抛出IllegalArgumentException("Cannot reflectively create enum objects");异常
        Single4 single31=constructor.newInstance("ONE",1);
        System.out.println("反射创建出来的对象是否与单例方法获取的对象相等？"+(single3==single31));
    }

    public static <T> boolean singleCompare(T obj) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\c02_single.obj"));
        oos.writeObject(obj);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\c02_single.obj"));
        T single2 = (T) ois.readObject();
        return obj == single2;
    }


}
