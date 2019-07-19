package thread.wang_bao_ling.c19;

public class Test {
    private static final int count = 10;

    public static void main(String[] args) {
        System.out.println("r1执行时间：" + executeTime(new Reconciliation1(count)));
        System.out.println("r2执行时间：" + executeTime(new Reconciliation2(count)));
        System.out.println("r3执行时间：" + executeTime(new Reconciliation3(count)));
        System.out.println("r4执行时间：" + executeTime(new Reconciliation4(count)));
        System.out.println("r5执行时间：" + executeTime(new Reconciliation5(count)));
    }

    public static long executeTime(Reconciliation reconciliation) {
        long startTime = System.currentTimeMillis();
        long endTime;
        reconciliation.DoReconciliation();
        endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

}
