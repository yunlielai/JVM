package jvm.zhou_zhi_ming.c02.test2.heap_oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *  指定dump文件输出位置-XX:HeapDumpPath=E:\devPath\dumpLog
 *
 * 异常：
 * java.lang.OutOfMemoryError: Java heap space
 */
public class Test1 {
    static List<OOMObject> list = new ArrayList<OOMObject>();
    static class OOMObject {
    }

    public static void main(String[] args) throws InterruptedException {
       new Thread(()->{
           try {
               while (true) {
                   list.add(new OOMObject());
               }
           } catch (Error e) {
               //在抛出内存溢出后如果不清除list将会一直占用内存
               list=null;
               System.out.println("创建的OOMObject对象数----------"+list.size());
               e.printStackTrace();
           }
       }).start();

        while (true) {
            //1分钟后报告
            Thread.sleep(1000 );
            System.out.println(Thread.currentThread().getId() + "main thread is alive");
        }

    }
}

