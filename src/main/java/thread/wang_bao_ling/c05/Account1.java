package thread.wang_bao_ling.c05;

public class Account1 {
    private int balance;
    // 转账
    void transfer(Account1 target, int amt){
        // 锁定转出账户
        synchronized(this){
            // 锁定转入账户
            synchronized(target){
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
