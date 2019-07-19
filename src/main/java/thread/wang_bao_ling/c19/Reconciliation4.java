package thread.wang_bao_ling.c19;

import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自己在不使用CyclicBarrier的情况下通过ReentrantLock和Condition优化Reconciliation3,将查询操作和对账保存操作也并行
 * 将查询操作和对账保存操作也并行
 * 150*10=1500
 */
public class Reconciliation4 extends Reconciliation {
    //由于执行chec的线程和往队列里面添加元素的线程间没有同步，所以需要使用线程安全的list，不然可能会出现两个线程check同一组元素的情况
    private Vector posQueue = new Vector();
    private Vector dosQueue = new Vector();

    private int flag = 2;
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition finish = reentrantLock.newCondition();

    public Reconciliation4(int count) {
        this.count = count;
    }

    public void DoReconciliation() {
        Thread t1 = new Thread(() -> {
            //注意此处不能直接使用count,因为有两个循环都使用了count
            int count2 = count;
            while (count2-- > 0) {
                posQueue.add(DataBaseHelper.getPOrders());
                try {
                    reentrantLock.lock();
                    flag--;
                    if (flag == 1) {
                        finish.await();
                    } else {
                        check();
                        flag = 2;
                        finish.signal();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }

        });

        Thread t2 = new Thread(() -> {
            //注意此处不能直接使用count2,因为有两个循环都使用了count
            int count2 = count;
            while (count2-- > 0) {
                try {
                    dosQueue.add(DataBaseHelper.getDOrders());
                    reentrantLock.lock();
                    flag--;
                    if (flag == 1) {
                        finish.await();
                    } else {
                        check();
                        flag = 2;
                        finish.signalAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }

        });

        t1.start();
        t2.start();
        try {
            //将t1,t2join到主线程去计算时间并不精确，会少统计时间，因为t1和t2执行完后可能check还在执行，差别时间不会大于一个check的执行时间50ms
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void check() {
        new Thread(() -> {
            // 执行对账操作
            Integer pos = (Integer) posQueue.remove(0);
            Integer dos = (Integer) dosQueue.remove(0);
            System.out.println("pos="+pos+"-----dos="+dos);
            Object diff = DataBaseHelper.check(pos,dos);
            // 差异写入差异库
            DataBaseHelper.save(diff);
        }).start();
    }

}
