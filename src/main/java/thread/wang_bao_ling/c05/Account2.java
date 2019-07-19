package thread.wang_bao_ling.c05;

/**
 * 将正在使用的账户放入到Allocator的list中，使用完后则从list中移除
 * 在进行转账操作的时候需要两个账户都没有正在使用（即list中不包含转入或转出的账号），从而防止死锁的发生
 */
public class Account2 {
    // actr应该为单例
    private Allocator actr;
    private int balance;
    // 转账
    void transfer(Account2 target, int amt){
        // 一次性申请转出账户和转入账户，直到成功
        //注意：如果转账操作很耗时会导致长时间的执行while去调用apply方法
        while(!actr.apply(this, target));
        try{
            // 锁定转出账户
            synchronized(this){
                // 锁定转入账户
                synchronized(target){
                    if (this.balance > amt){
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        } finally {
            actr.free(this, target);
        }
    }
}
