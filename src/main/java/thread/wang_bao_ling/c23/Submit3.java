package thread.wang_bao_ling.c23;

import java.util.concurrent.*;

public class Submit3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(4));

        User user = new User(1, "luobiao");

        //主要两个user要是同一个对象，因为run中修改的是构造器中传入的user，而future.get得到的是submit方法中传入的user,只有两个user是同一个对象的引用修改才会生效
        Future<User> future= executor.submit(new Task(user),user);
        System.out.println(future.isDone());
        System.out.println("isDone end");
        System.out.println("执行结果："+future.get().getName());
        System.out.println("get end");


        executor.shutdown();
    }

    public static class Task implements Runnable {
        User user;

        public Task(User user) {
            this.user=user;
        }

        @Override
        public void run() {
            this.user.setName("luoshenyuan");
        }
    }

}
