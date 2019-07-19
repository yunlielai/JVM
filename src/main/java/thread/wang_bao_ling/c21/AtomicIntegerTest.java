package thread.wang_bao_ling.c21;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicIntegerTest {
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static final int threadNum=5;
    private static CountDownLatch  countDownLatch=new CountDownLatch(threadNum);

    public static void main(String[] args) throws InterruptedException {

        Runnable atomicRun=()->{
            atomicInteger.incrementAndGet();
        };
        calculate(atomicRun);
        System.out.println("atomicInteger="+atomicInteger.get());
    }

    private static void add(Runnable runnable) {
        int idx = 0;
        while(idx++ < 1000) {
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
