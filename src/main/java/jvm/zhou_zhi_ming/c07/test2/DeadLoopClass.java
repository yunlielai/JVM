package jvm.zhou_zhi_ming.c07.test2;

public class DeadLoopClass {
    static {
        if(true){
            System.out.println(Thread.currentThread().getName()+"---执行DeadLoopClass clinit");
            while (true) {
            }
        }
    }
}
