package thread.wang_bao_ling.c14;

public class SynchronizatizedDemo {
    private int value;

    public static void main(String[] args) throws InterruptedException {
        SynchronizatizedDemo test = new SynchronizatizedDemo();
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

    public synchronized void addOne() {
        value = get() + 1;
    }

    public synchronized int get() {
        return value;
    }
}
