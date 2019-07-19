package thread.wang_bao_ling.c05;

/**
 * 通过在账户中添加排序id，在加锁的时候先锁住id小的，再锁住id大的
 * 如：有a（id=1）和b(id=2)两个账号，产生死锁的场景是
 *   1.线程一对a加锁
 *   2.线程二对b加锁
 *   3.线程一再对b加锁（等待线程二释放b）
 *   4.线程二对a加锁（等待线程一释放a）
 * 如果通过id来进行控制加锁顺序则第3步就会先对a加锁，此时a已被线程一占用，则会等待线程一释放a锁，这样就不会死锁了
 */
public class Account3 {
    private int id;
    private int balance;
    // 转账
    void transfer(Account3 target, int amt){
        Account3 left = this;
        Account3 right = target;
        if (this.id > target.id) {
            left = target;
            right = this;
        }
        // 锁定序号小的账户
        synchronized(left){
            // 锁定序号大的账户
            synchronized(right){
                if (this.balance > amt){
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
