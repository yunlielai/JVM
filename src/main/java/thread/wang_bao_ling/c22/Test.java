package thread.wang_bao_ling.c22;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 */
public class Test {
    private static int tCount=1;
    public static void main(String[] args){

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(4), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r,"thread"+tCount);
                tCount++;
                return thread;
            }
        });

        //corPoolSize和队列都不够用来才会启动新的线程一直到maximumPoolSize,如果超过了maximumPoolSize则会执行丢弃策略,
        IntStream.range(0, 6).forEach(f->{
            executor.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"-------hello");
            });
        });


        executor.shutdown();


    }

}
