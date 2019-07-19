package jvm.zhou_zhi_ming.c03.test1;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 *  VM Args:-Xmx20m -Xms20m
 *  在未放开@1处的注释时运行不会打印回收信息，因为内存充足，如果放开则会打印
 *  软引用对象在内存不足时会被gc回收
 */
public class SoftReferenceTest {
    private static List list = new ArrayList();
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue referenceQueue=new ReferenceQueue();
        new Thread(()->{
            try {
                System.out.println("begin remove");
                SoftReference softReference=(SoftReference)referenceQueue.remove();
                System.out.println("end remove");
                Object obj=softReference.get();
                System.out.println(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        SoftReference softReference=new SoftReference(new MyObj(),referenceQueue);
        try {
            add();
        } catch (Error error) {
            System.out.println("内存溢出....");
        }
        Thread.sleep(10000);
    }

    public static void add(){
        list.add(new byte[1024 * 1024 * 5]); // 5m
        list.add(new byte[1024 * 1024 * 5]); // 5m
        list.add(new byte[1024 * 1024 * 5]); // 5m
        list.add(new byte[1024 * 1024 * 5]); // 5m
    }
}
