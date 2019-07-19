package jvm.zhou_zhi_ming.c02.test2.stack_oom;

import util.SystemUtil;

import java.util.concurrent.atomic.AtomicLong;

/**
 * num : 每个栈中递归的次数，即栈深度
 * count : 创建的最大线程
 * use : 从程序开始运行到栈内存溢出时所消耗的常驻内存
 *
 * 异常：
 * java.lang.OutOfMemoryError: unable to create new native thread
 */
public class Test1 {
    private static int num=10;
    public static void main(String[] args) {
        createThread();
    }

    public static void  createThread(){

        AtomicLong count= new AtomicLong();
        long use=SystemUtil.getUseMemory(()->{
            try{
                while(true){
                    new Thread(()->{
                        work(1);
                    }).start();
                    count.getAndIncrement();
                }
            }catch(Error e){
                e.printStackTrace();
            }
        });
        System.out.println("递归数："+num+"  线程数："+count+"  耗内存："+use);
        System.exit(1);
    }

    public static void work(int i) {
        if (i > num) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        work(++i);
    }
}
