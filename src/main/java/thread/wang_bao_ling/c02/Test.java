package thread.wang_bao_ling.c02;

public class Test {
    public static void main(String[] args) {
        HappenBefore123 happenBefore123=new HappenBefore123();
        //演示happen before 1,2,3规则
        happenBefore123.demo();
        HappenBefore4 happenBefore4=new HappenBefore4();
        //演示happen before 4规则
        happenBefore4.demo();
    }
}
