package jvm.zhou_zhi_ming.c03.test3;

/**
 * VM Args： -Xms20M -Xmx20M -Xmn10M  -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=15 -XX:+PrintGCDetails
 * 第一次gc日志：
 * [GC (Allocation Failure) [DefNew: 6969K->1023K(9216K), 0.0028934 secs] 6969K->5398K(19456K), 0.0029217 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 第二次gc日志：
 * [GC (Allocation Failure) [DefNew: 5120K->0K(9216K), 0.0072984 secs] 9495K->9494K(19456K), 0.0073494 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 会发现第二次gc后年轻代变成0了，说明survivor中的对象直接进入老年代了，因为第二次gc时发现survivor中allocation1和allocation2加起来就达到了512k，已经达到了survivor中的一半
 */
public class Test4 {
    private static final int _1MB=1024*1024;

    public static void main(String[] args) throws InterruptedException {
        byte[]allocation1, allocation2, allocation3, allocation4;
        allocation1=new byte[_1MB/4];
        //allocation1+allocation2大于survivo空间一半
        allocation2=new byte[_1MB/4];
        allocation3=new byte[4*_1MB];
        //在给allocation4赋值时发现Eden区不够分配了（只有8m）所以会小进行一次minor gc
        allocation4=new byte[4*_1MB];
        Thread.sleep(1000);
        //在重新分配一个4M的对象时发现Eden区又不够用了，又会触发minorgc
        allocation4=new byte[4*_1MB];
        Thread.sleep(10000);
    }
}
