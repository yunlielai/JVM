package thread.wang_bao_ling.c19;

/**
 * 最原始的做法，串行化
 * 150*10+100*10+50*10+50*10=3500
 */
public class Reconciliation1 extends Reconciliation{

    public Reconciliation1(int count) {
        this.count = count;
    }

    public void DoReconciliation()   {
        while (count-- > 0) {
            // 查询未对账订单
            pos = DataBaseHelper.getPOrders();
            // 查询派送单
            dos = DataBaseHelper.getDOrders();
            // 执行对账操作
            Object diff = DataBaseHelper.check(pos, dos);
            // 差异写入差异库
            DataBaseHelper.save(diff);
        }
    }

}
