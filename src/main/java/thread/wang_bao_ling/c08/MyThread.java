package thread.wang_bao_ling.c08;

import java.util.stream.IntStream;

public class MyThread extends Thread {


    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        IntStream.range(0, 5).forEach(f->{
            System.out.println(Thread.currentThread().getName()+"run"+1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    public final void myJoin() throws InterruptedException {
        myJoin(0);
    }

    /**
     * Thread 中join的实现
     */
    public final synchronized void myJoin(long millis) throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (millis == 0) {
            //isAlive会判断myThread线程是否处于终止状态，没终止则返回true
            while (isAlive()) {
                System.out.println(Thread.currentThread().getName()+"调用wait");
                //wait(0)表示调用myThread.myJoin方法的线程一直等待，直到被获取myThread锁对象的线程调用notify通知（如果myTread线程终止，会自动调用myTread锁对象的notifyAll方法）
                wait(0);
            }
        } else {
            while (isAlive()) {
                long delay = millis - now;
                    if (delay <= 0) {
                    break;
                }
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }

    /**
     * 手动调用myThread锁对象的notifyAll方法
     */
    public final synchronized void wakeUp(){
        notifyAll();
    }
}
