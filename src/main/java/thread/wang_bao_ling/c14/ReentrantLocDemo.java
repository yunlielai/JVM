package thread.wang_bao_ling.c14;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 当线程T1执行到 @1 处时，已经获取到了锁 rtl ，当在 @1 处调用 get()方法时，会在 @2 再次对锁 rtl 执行加锁操作
 */
public class ReentrantLocDemo {
    private final ReentrantLock rtl = new ReentrantLock();
    int value;
    public int get() {
        // 获取锁
        rtl.lock();         // @2
        try {
            return value;
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }
    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value = 1 + get(); // @1
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLocDemo test=new ReentrantLocDemo();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                test.addOne();
            }
        });
        t1.start();
        t1.join();
        //运行能正常输出结果，表示在调用get时可以重复获取锁，不会被阻塞，即锁可重入
        System.out.println(test.get());
    }
}
