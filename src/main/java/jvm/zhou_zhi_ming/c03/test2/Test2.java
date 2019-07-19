package jvm.zhou_zhi_ming.c03.test2;

/**
 * 被静态变量引用的对象不会被gc
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        test();
        System.gc();
        Thread.sleep(1000000);
    }

    public static class MyObj {
        public static Object object;

        public MyObj() {
            object = this;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("执行finalize方法...");
        }
    }

    public static void test() {
        MyObj myObj = new MyObj();
    }
}
