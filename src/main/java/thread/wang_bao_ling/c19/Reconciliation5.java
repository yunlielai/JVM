package thread.wang_bao_ling.c19;

import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过CyclicBarrier对 Reconciliation3进行优化,将查询操作和对账保存操作也并行
 * 注意：必须只有一个线程执行循环查询订单库@1处，和一个线程执行循环查询运单库@2处，不然可能会导致两个对象中的元素不能一一对应，//@3处输出pos和dos相同则表明是一一对应的
 * 如果设置为多个，有可能会两个线程 A 和 B 同时查询，A 的订单先返回，B 的派送单先返回，造成队列中的数据不匹配；所以1个线程实现生产数据串行执行
 * 150*10=1500
 */
public class Reconciliation5 extends Reconciliation {
    private Vector posQueue = new Vector();
    private Vector dosQueue = new Vector();
    Executor executor = Executors.newFixedThreadPool(1);

    final CyclicBarrier barrier =
            new CyclicBarrier(2, () -> {
                executor.execute(() -> check());
            });

    public Reconciliation5(int count) {
        this.count = count;
    }

    public void DoReconciliation() {
        // 循环查询订单库
        Thread t1 = new Thread(() -> {//@1
            int count2 = count;
            while (count2-- > 0) {
                // 查询订单库
                posQueue.add(DataBaseHelper.getPOrders());
                // 等待
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 循环查询运单库
        Thread t2 = new Thread(() -> {
            int count2 = count;
            while (count2-- > 0) {
                // 查询运单库
                dosQueue.add(DataBaseHelper.getDOrders());
                // 等待
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
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
        ((ExecutorService) executor).shutdown();
    }

    public void check() {
        // 执行对账操作
        Integer pos = (Integer) posQueue.remove(0);
        Integer dos = (Integer) dosQueue.remove(0);
        System.out.println("pos="+pos+"-----dos="+dos);//@3
        Object diff = DataBaseHelper.check(pos, dos);
        // 差异写入差异库
        DataBaseHelper.save(diff);
    }

}
