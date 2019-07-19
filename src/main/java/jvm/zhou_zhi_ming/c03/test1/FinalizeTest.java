package jvm.zhou_zhi_ming.c03.test1;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-Xms1g -Xmx1g -Xmn900m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * 通过上面的设置，eden区的大小为（900/(8+1=1)）*8=720，足够装下Obj1和Obj2
 *
 * 在第一次gc后gc日志为：
 *  [PSYoungGen: 336691K->205592K(829440K)]
 *  并打印出“执行Obj的finalize方法”
 *  减少了大概100m,说明Obj2已被回收，而Obj1并未回收
 *
 * 第二次gc后gc日志为：
 * [PSYoungGen: 235083K->856K(829440K)]
 * 减少了大概200m，说明Obj1也已被回收
 *
 */
public class FinalizeTest {
    public static void main(String[] args) throws InterruptedException {
        FinalizeTest test = new FinalizeTest();
        test.test1();
        test.test2();
        System.out.println("gc1 begin-------------");
        System.gc();
        System.out.println("gc1 after-------------");
        Thread.sleep(1000);
        System.out.println("gc2 begin-------------");
        System.gc();
        System.out.println("gc2 after-------------");
        Thread.sleep(10000000);
    }

    public void test1() {
        Obj1 obj1 = new Obj1(1024 * 1024*200);
    }
    public void test2() {
        Obj2 obj2 = new Obj2(1024 * 1024*100);
    }

    public class Obj1 {
        private List list = new ArrayList();

        public Obj1(int size) {
            this.list.add(new byte[size]);
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("执行Obj的finalize方法");
        }
    }

    public class Obj2 {
        private List list = new ArrayList();

        public Obj2(int size) {
            this.list.add(new byte[size]);
        }
    }
}
