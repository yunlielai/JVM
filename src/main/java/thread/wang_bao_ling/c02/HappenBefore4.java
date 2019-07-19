package thread.wang_bao_ling.c02;

/**
 *  这条规则是指对一个锁的解锁 Happens-Before 于后续对这个锁的加锁。
 *  即解锁操作前的操作对后面加锁后的操作是可见的
 *  如示例：线程a先获取this对象的锁，在同步代码块中执行x=12的操作后进行解锁
 *  然后线程b拿到了锁，线程b会去取x变量的值时a线程执行的x=1对b线程来说时可见的
 */
public class HappenBefore4 {
    int x = 10;

    public void writer() {
        synchronized (this) {
            if (this.x < 12) {
                System.out.println(Thread.currentThread().getName());
                this.x = 12;
            }
        }
    }

    public void demo(){
        Thread a=new Thread(()->{
            writer();
        },"HappenBefore4 thread a");
        Thread b=new Thread(()->{
            writer();
        },"HappenBefore4 thread b");

       a.start();

        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        b.start();
    }
}
