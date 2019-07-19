package jvm.zhou_zhi_ming.c03.test1;

public class MyObj {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("开始执行 finalize");
        long count=10000000000L;
        while (count-->0) {
        }
        System.out.println("结束执行finalize");

    }
}
