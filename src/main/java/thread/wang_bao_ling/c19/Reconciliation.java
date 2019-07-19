package thread.wang_bao_ling.c19;

public abstract class Reconciliation {
    //执行异常单处理的次数
    protected int count;

    Object pos = null;
    Object dos = null;

    abstract void DoReconciliation();
}
