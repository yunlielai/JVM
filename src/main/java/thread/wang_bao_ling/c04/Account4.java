package thread.wang_bao_ling.c04;

/**
 * 使用Account.class作为共享的锁
 */
public class Account4 {
    private int balance;
    // 转账
    void transfer(Account4 target, int amt){
        synchronized(Account4.class) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}
