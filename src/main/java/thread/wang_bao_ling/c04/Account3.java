package thread.wang_bao_ling.c04;

/**
 * 通过在构建账号的时候传入lock对象解决问题
 */
public class Account3 {
    private Object lock;
    private int balance;
    // 创建Account时传入同一个lock对象
    public Account3(Object lock) {
        this.lock = lock;
    }
    // 转账
    void transfer(Account3 target, int amt){
        // 此处检查所有对象共享的锁
        synchronized(lock) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}
