package thread.wang_bao_ling.c19;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池和CountDownLatch 优化Reconciliation2
 * 150*10+50*10+50*10=2500
 */
public class Reconciliation3 extends Reconciliation {

    public Reconciliation3(int count) {
        this.count = count;
    }

    public void DoReconciliation() {
        // 创建2个线程的线程池
        Executor executor = Executors.newFixedThreadPool(2);
        while (count-- > 0) {
            CountDownLatch latch = new CountDownLatch(2);
            // 查询未对账订单
            executor.execute(() -> {
                pos = DataBaseHelper.getPOrders();
                latch.countDown();
            });
            // 查询派送单
            executor.execute(() -> {
                dos = DataBaseHelper.getDOrders();
                latch.countDown();
            });

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 执行对账操作
            Object diff = DataBaseHelper.check(pos, dos);
            // 差异写入差异库
            DataBaseHelper.save(diff);
        }

        //停止线程池，不然main方法不会退出，因为只有所有的非守护线程全部退出，守护线程main才会退出
        ((ExecutorService) executor).shutdown();
    }

}
