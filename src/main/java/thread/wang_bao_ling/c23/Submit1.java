package thread.wang_bao_ling.c23;

import java.util.concurrent.*;

public class Submit1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(4));
        Future future= executor.submit(()->{
            System.out.println("begin run");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end run");
        });
        System.out.println(future.isDone());
        System.out.println("isDone end");
        //此处会阻塞main线程，等待执行结果，而runable没有返回值，为null
        System.out.println("执行结果："+future.get());
        System.out.println("get end");

        executor.shutdown();
    }
}
