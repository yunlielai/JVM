package jvm.zhou_zhi_ming.c02.test1;

import util.MySizeOf;

/**
 * 运行前添加 -javaagent:target/demo.jar 参数
 *
 */
public class Test2 {
    public static void main(String[] args) {

        System.out.println("MyObject1="+MySizeOf.sizeOf(new MyObject1()));
        System.out.println("MyObject2="+MySizeOf.sizeOf(new MyObject2()));
        System.out.println("MyObject3="+MySizeOf.sizeOf(new MyObject3()));
        System.out.println("数组="+MySizeOf.sizeOf(new byte[0]));
        System.out.println("数组="+MySizeOf.sizeOf(new byte[1]));
        System.out.println("Boolean="+MySizeOf.sizeOf(new Boolean(true)));
        System.out.println("Integer="+MySizeOf.sizeOf(new Integer(1)));
        System.out.println("Long="+MySizeOf.sizeOf(new Long(1)));
    }


}
