package jvm.zhou_zhi_ming.c08.test1;

/**
 * 局部变量表Slot复用对垃圾收集的影响之一
 */
public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        test();
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

        //虽然栈局部变量myObj已经超出了局部作用域，但由于后面没有新的局部变量使用myObj的slot,所以依旧会强引用new出来的MyObj，导致第一次gc不会回收new出来的MyObj对象
       // 如果在后面通过定义一个新的变量使用掉这个myObj占用的slot,如：int i=1;则回收
       Object o;
        System.gc();
        System.out.println("第一次gc结束");
    }
}
