package jvm.zhou_zhi_ming.c03.test2;

/**
 * 栈帧中局部变量引用的对象不会被gc
 */
public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        test();
        Thread.sleep(2000);
        System.out.println("开始第二次gc");
        System.gc();
        Thread.sleep(1000000);
    }

    public static class MyObj {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("执行finalize方法...");
        }
    }

    public static void test() {
        MyObj myObj = new MyObj();
        //由于第一次gc时myObj引用还在方法的作用域里面，可以作为栈gc root继续引用new出来的MyObj对象，如果将myObj指向另外一个对象，则new出来的那个MyObj对象就会被回收
        System.gc();
        System.out.println("第一次gc结束");
    }
}
