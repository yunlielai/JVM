package jvm.zhou_zhi_ming.c07.test2;

public class Test2 {
    public static void main(String[] args) {
        Runnable script=()->{
            System.out.println(Thread.currentThread().getName()+"准备执行new DeadLoopClass");
            DeadLoopClass deadLoopClass=new DeadLoopClass();
            System.out.println(Thread.currentThread().getName()+"完成执行new DeadLoopClass");
        };
        Thread t1 = new Thread(script,"线程1");
        Thread t2 = new Thread(script,"线程2");
        t1.start();
        t2.start();
    }
}
