package jvm.zhou_zhi_ming.c03.test1;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用在gc发生时就会被回收，不管内存是否充足，而且也无法通过虚引用来取得一个对象实例
 */
public class PhantomReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue referenceQueue=new ReferenceQueue();
        new Thread(()->{
            try {
                System.out.println("begin remove");
                PhantomReference phantomReference=(PhantomReference)referenceQueue.remove();
                System.out.println("end remove");
                System.out.println("get="+phantomReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        PhantomReference phantomReference=new PhantomReference(new MyObj(),referenceQueue);
        System.gc();
        //注意：PhantomReference需要在引用的对象被gc回收后才会添加到引用的队列，而重写了finalize至少需要两次gc才能被回收
        //不然remove的时候会一直被阻塞，等待引用的对象被回收
        while (true) {
            Thread.sleep(100);
            System.gc();
        }
    }

}
