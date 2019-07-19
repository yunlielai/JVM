package jvm.zhou_zhi_ming.c03.test3;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args：-Xms200M -Xmx200M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=0 -XX:+PrintGCDetails
 * 通过将 MaxTenuringThreshold设置为0表示对象年龄大于0的直接进入老年代 ，那么每次gc的时候年轻代大小会为0
 * 发生gc时日志信息为：
 * [GC (Allocation Failure) [DefNew: 7993K->0K(9216K), 0.0116707 secs] 7993K->6423K(203776K), 0.0117609 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 如果将MaxTenuringThreshold=1
 * 运行结gc日志为：
 * [GC (Allocation Failure) [DefNew: 9119K->768K(9216K), 0.0111065 secs] 14519K->14360K(203776K), 0.0111603 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 */
public class Test3 {
    private static final int M=1024*1024;

    public static void main(String[] args) throws InterruptedException {
        List list=new ArrayList();
        while (true){
            Thread.sleep(100);
           list.add(new byte[M/4]);
        }
        //System.gc();
        //Thread.sleep(20000);
    }
}
