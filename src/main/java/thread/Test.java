package thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 测试wait,sleep是否会分配时间片
 */

public class Test {
    private List list = new ArrayList();
    public static void main(String[] args) {
        Test test=new Test();
        test.testSleep();

    }

    public void testSleep(){
        IntStream.range(0,10000).forEach(c->{
            IntStream.range(0, 10000).forEach(f->{
              /*  new Thread(()->{
                    try {
                        System.out.println(Thread.currentThread().getName());
                        Thread.currentThread().sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();*/
                list.add(new HashMap<>());
            });
        });

    }

}
