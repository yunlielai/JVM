package jvm.zhou_zhi_ming.c03.test1;

import java.util.ArrayList;
import java.util.List;

/**
 *  VM Args:-Xmx20m -Xms20m
 *  强引用即使是在内存溢出时也不会被回收
 *
 */
public class StrongReferenceTest {
    private static List list=new ArrayList();
    public static void main(String[] args) throws InterruptedException {
        MyObj myObj=new MyObj();
        System.gc();
        list.add(new byte[1024 * 1024 * 5]); // 5m
        list.add(new byte[1024 * 1024 * 5]); // 5m
        list.add(new byte[1024 * 1024 * 5]); // 5m
        list.add(new byte[1024 * 1024 * 5]); // 5m
        Thread.sleep(10000);

    }
}
