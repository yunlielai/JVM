package thread.wang_bao_ling.c19;

/**
 * 将 查询未对账订单和查询派送单同时进行
 * 150*10+50*10+50*10=2500
 */
public class Reconciliation2 extends Reconciliation {

    public Reconciliation2(int count) {
        this.count = count;
    }

    public void DoReconciliation() {
        while (count-- > 0) {
            // 查询未对账订单
            Thread t1 = new Thread(() -> {
                pos = DataBaseHelper.getPOrders();
            });

            // 查询派送单
            Thread t2 = new Thread(() -> {
                dos = DataBaseHelper.getDOrders();
            });
            t1.start();
            t2.start();
            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 执行对账操作
            Object diff = DataBaseHelper.check(pos, dos);
            // 差异写入差异库
            DataBaseHelper.save(diff);
        }
    }

}
