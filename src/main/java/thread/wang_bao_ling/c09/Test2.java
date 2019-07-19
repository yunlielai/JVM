package thread.wang_bao_ling.c09;

/**
 * 两个线程不停的进行加解锁，使得线程不停的被挂起，唤醒导致频繁的上下文切换
 * 通过vmstat可以看到程序在执行的时候cs值会非常的高
 */
public class Test2 {
    private int count = 1000000;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Test2 test = new Test2();
        Thread t1 = new Thread(() -> {
            try {
                test.m1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                test.m2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread.sleep(10);
        t2.start();
        t1.join();
        t2.join();
        System.out.println("耗时:" + (System.currentTimeMillis() - start));

    }

    public void m1() throws InterruptedException {
        for (int i = 0; i < count; i++) {
            synchronized (this) {
                System.out.println("m1=" + i);
                //防止最后一次执行时m2已经循环完毕了，没有notifyAll将其唤醒
                wait(1);
                notifyAll();//@1
            }

        }

    }

    public void m2() throws InterruptedException {
        for (int i = 0; i < count; i++) {
            synchronized (this) {
                System.out.println("m2=" + i);
                notifyAll();//@2
                //防止被@1的notifyAll唤醒后比m1先抢到锁，导致@2唤醒不了任何wait，从而m1中的wait和m2中的wait相互等待
                wait(1);
            }

        }
    }
}
