package jvm.zhou_zhi_ming.c03.test3;
/**
 * VM Args：-Xms200M -Xmx200M -Xmn100M -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:PretenureSizeThreshold=3m -XX:+PrintGCDetails
 * 通过 PretenureSizeThreshold设置超过3m 的为大对象，注意此设置对Parallel Scavenge收集器无效
 * 程序结束后堆使用情况
 * def new generation   total 92160K
 *   eden space 81920K,   8% used
 *   from space 10240K,   0% used
 *   to   space 10240K,   0% used
 *  tenured generation   total 102400K, used 20480K
 *    the space 102400K,  20% used
 * 可以看到老年代新增了20%=20m，说明大对象被直接分配到了老年代
 */
public class Test2 {
    private static final int M=1024*1024;

    public static void main(String[] args){
        byte[] allocation1;
        allocation1=new byte[20*M];
    }
}
