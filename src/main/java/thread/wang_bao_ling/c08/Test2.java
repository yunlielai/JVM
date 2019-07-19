package thread.wang_bao_ling.c08;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Test2 {
    private static int count=0;
    private static User user= new User("luobiao", 28);
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        IntStream.range(0, 10).forEach(f->{
            new Thread(()->{
                user= new User("luobiao", 28);
                System.out.println(user.getId());
                    for (int i = 0; i < 1000; i++) {
                        synchronized (user.getId()) {
                            count++;
                        }
                    }
                countDownLatch.countDown();
            }).start();
        });

        //开启一个现场循环修改user中id的值
        new Thread(()->{
            System.out.println("begin change id");
            for (int i = 0; i < 10000; i++) {
                user.setId("luobiao"+i);
            }
        }).start();
        countDownLatch.await();
        System.out.println(count);
    }
}
