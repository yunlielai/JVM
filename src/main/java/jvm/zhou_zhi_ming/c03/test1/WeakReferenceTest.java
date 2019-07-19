package jvm.zhou_zhi_ming.c03.test1;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 弱引用的对象在gc时就会被回收，不管内存是否充足
 */
public class WeakReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue referenceQueue=new ReferenceQueue();
        new Thread(()->{
            try {
                System.out.println("begin remove");
                WeakReference weakReference=(WeakReference)referenceQueue.remove();
                System.out.println("end remove");
                System.out.println(weakReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        WeakReference weakReference=new WeakReference(new MyObj(),referenceQueue);
        System.gc();
        Thread.sleep(10000);
    }
}
