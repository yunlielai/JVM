package thread.wang_bao_ling.c21;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.IntStream;

/**
 * 使用atomic和使用synchronized新能对比
 */
public class AtomicVsSyncTest {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    private static int count;
    private static Object sycObj=new Object();
    private static final int threadNum=5;
    private static CountDownLatch  countDownLatch=new CountDownLatch(threadNum);

    public static void main(String[] args) throws InterruptedException {

        Runnable atomicRun=()->{
            atomicInteger.incrementAndGet();
        };

        Runnable syncRun=()->{
            synchronized (sycObj) {
                count++;
            }
        };

        System.out.println("atomic方式耗时："+(calculate(atomicRun)));
        System.out.println("atomicInteger="+atomicInteger.get());
        System.out.println("synchronized方式耗时："+(calculate(syncRun)));
        System.out.println("count="+count);
    }

    private static void add(Runnable runnable) {
        int idx = 0;
        while(idx++ < 10000000) {
           runnable.run();
        }
    }

    public static long calculate(Runnable runnable) throws InterruptedException {
        long start=System.currentTimeMillis();
        IntStream.range(0, 5).forEach(f->{
            new Thread(()->{
                add(runnable);
                try {
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        });
        countDownLatch.await();
        countDownLatch = new CountDownLatch(threadNum);
        return System.currentTimeMillis()-start;
    }
}
