package thread.wang_bao_ling.c08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Test3 {
    private static int count=0;
    private static User user= new User("luobiao", 28);
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);

        IntStream.range(0, 100).forEach(f->{
            new Thread(()->{
                user= new User("luobiao", 28);
                    for (int i = 0; i < 1000; i++) {
                        synchronized (user.getId()) {
                            System.out.println(Thread.currentThread().getName()+"|count="+count);
                            count++;
                        }
                    }
                countDownLatch.countDown();
            },"thread-"+f).start();
        });

        new Thread(()->{
            System.out.println("blocking get lock");
            //由于user中的id值和字面量"luobiao" 都是指向常量池中的"luobiao"内存地址，所以会互斥
            synchronized ("luobiao") {
                try {
                    //占用锁，会导致count++无法进行
                    Thread.sleep(2000);
                    System.out.println("end sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        countDownLatch.await();
        System.out.println(count);

    }
}
