package thread.wang_bao_ling.c01;

public class Test {
    private static volatile long count = 0;
    private void add10K() {
        int idx = 0;
        while(idx++ < 100000000) {
            count += 1;
        }
    }
    public static long calc() throws InterruptedException {
        final Test test = new Test();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(()->{
            test.add10K();
        });
        Thread th2 = new Thread(()->{
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime=System.currentTimeMillis();
        //实际输出的count值会小于2000
        System.out.println(calc());

        System.out.println("耗时"+(System.currentTimeMillis()-startTime));
    }
}
