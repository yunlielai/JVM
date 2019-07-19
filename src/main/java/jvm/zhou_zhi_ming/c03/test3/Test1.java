package jvm.zhou_zhi_ming.c03.test3;
/**
 * VM Args：-Xms200M -Xmx200M -Xmn100M -XX:SurvivorRatio=8 -XX:+UseSerialGC  -XX:+PrintGCDetails
 * 程序结束后堆使用情况
 *  def new generation   total 92160K, used 27038K
 *     eden space 81920K,  33% used
 *     from space 10240K,   0% used
 *     to   space 10240K,   0% used
 *  tenured generation   total 102400K, used 0K
 *     the space 102400K,   0% used
 *
 * 可以看到对象直接被分配到Eden区了
 */
public class Test1 {
    private static final int M=1024*1024;

    public static void main(String[] args){
        byte[] allocation1;
        allocation1=new byte[20*M];
    }
}
