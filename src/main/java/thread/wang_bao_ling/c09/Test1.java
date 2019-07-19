package thread.wang_bao_ling.c09;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 在多核cpu中每一核上如果只有一个线程执行不会引起上下文切换，因为每一核上只有一个线程，不会有当前线程时间片用完而触发上下文切换（此处忽略其他进程）
 * 如果分配的线程超过cpu核数，那每一核上分配的线程就增加了，线程越多，因为分配时间片引起的上下文切换就会越频繁
 * 在4核cpu上测试，当线程数不大于4通过vmstat可以看到每秒上下文切换次数（cs）基本能控制在200以内，如果超过cpu核数每秒上下文切换次数才会持续上涨
 */
public class Test1 {
    private static int tCount=4;
    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    public static void test1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        IntStream.range(0,tCount ).forEach(f -> {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"begin");
                int[] arr=getArr(1000000);
                maoPao(arr);
                System.out.println(Thread.currentThread().getName()+"end");
            }, "mythread-" + f).start();
        });
        countDownLatch.await();
    }
    public static void maoPao(int[] arr){
        for (int i =1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
    public static int[] getArr(int size){
        int[] arr=new int[size];
        Random random = new Random();
        for (int i=0;i<arr.length;i++){
            arr[i]=random.nextInt(size)+1;
        }
        return arr;
    }

}
