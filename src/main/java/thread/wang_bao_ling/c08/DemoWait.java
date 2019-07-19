package thread.wang_bao_ling.c08;

public class DemoWait {
    public Object obj1 = new Object();

    public static void main(String[] args) throws Exception {
        new DemoWait().demo();
    }

    public void demo() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 请求锁");
            synchronized (obj1) {
                System.out.println("t1 得到锁");
                try {
                    Thread.sleep(1000);
                    System.out.println("t1 开始wait");
                    //在执行wait的时候会释放obj1对象的锁
                    obj1.wait();
                    System.out.println("t1 wait结束");
                    Thread.sleep(1000);
                    System.out.println("t1 代码执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("t2 请求锁");
            synchronized (obj1) {
                System.out.println("t2 得到锁");
                try {
                    Thread.sleep(1000);
                    System.out.println("t2 开始notify");
                    obj1.notify();
                    System.out.println("t2 notify结束");
                    Thread.sleep(1000);
                    //在t2 代码执行完毕后才会释放obj1对象的锁，此时并不一定会执行t1 wait后面的代码，它需要重新获取锁，如果有其他线程也在等待obj1对象的锁可能会被其他线程得到锁（如本示例中的t3线程会得到锁）
                    System.out.println("t2 代码执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3=new Thread(()->{
            System.out.println("t3 请求锁");
            synchronized (obj1) {
                System.out.println("t3 得到锁");
            }
        });

        t1.start();
        Thread.sleep(100);
        t3.start();
        Thread.sleep(100);
        t2.start();
    }
}
