package thread.wang_bao_ling.c10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * tCount=1,count=5 耗时10
 * tCount=2,count=5 耗时6
 * tCount=3,count=5 耗时6
 */
public class Test1 {
    private static Object cpuLock = new Object();
    private static Object ioLock = new Object();
    //线程池线程数量
    private static int tCount = 2;
    //执行次数
    private static int count = 5;
    private static CountDownLatch countDownLatch = new CountDownLatch(count);

    public static void main(String[] args) throws InterruptedException {
       long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(tCount);
        //模拟10个请求
        while (count-- > 0) {
            executor.execute(new Work());
        }
        countDownLatch.await();
        System.out.println("总耗时" + (System.currentTimeMillis() - start));
        executor.shutdown();
    }

    public static class Work implements Runnable{
        @Override
        public void run() {
            cup();
            io();
            countDownLatch.countDown();
        }
    }

    //模拟cup操作
    public static void cup() {
        //同一时刻只能有一个线程执行cup操作
        synchronized (cpuLock) {
            System.out.println(Thread.currentThread().getName() + "-执行cup操作："+System.currentTimeMillis());
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //模拟io操作
    public static void io() {
        //假设io操作不存在瓶颈
        //synchronized (ioLock) {
            System.out.println(Thread.currentThread().getName() + "-执行 io操作："+System.currentTimeMillis());
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}
    }
}
