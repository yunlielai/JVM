package thread.wang_bao_ling.c06;



public class Account {
    // actr应该为单例
    private Allocator actr;
    private int balance;
    // 转账
    void transfer(Account target, int amt){
        actr.apply(this,target);
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
