package thread.wang_bao_ling.c04;

/**
 * 只能保证当前对象的balance安全，服务保证target 账号的balance安全，因为target account的锁target对象和当前账号不是同一把锁
 * 如有a b c 三个账号余额分别为200，如果a给b转100，b给c转100，那么不管是a给b先转还是b给c先转预期结果都应该是a=100 b=200 c=300
 * 但是如果a给b和b给c同时进行（两个操作同时读到b为200），则最终的结果b会为100 或 300 ，不会出现预期的200
 */
public class Account2 {
    private int balance;
    // 转账
    synchronized void transfer(
            Account2 target, int amt){
        if (this.balance > amt) {
            this.balance -= amt;
            target.balance += amt;
        }
    }
}
