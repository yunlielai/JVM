package thread.wang_bao_ling.c16;

import java.util.Vector;
import java.util.stream.IntStream;

/**
 * 实现限流器
 * 效果：有5个线程能同时获取到Connect对象，由于同时能有5个线程访问临界区，所以需要保证资源池list线程安全
 */
public class Test {
    public static void main(String[] args){

        //需要使用线程安全的list
        Vector<Connect> vectorList = new Vector<>();

        //创建一个5个对象的池子
        IntStream.range(0, 5).forEach(f->{
            vectorList.add(new Connect(f, "connet" + f));
        });
        // 创建对象池
        ObjPool<Connect, String> pool = new ObjPool<>(vectorList);

        //10个线程去获取池子中的5个对象
        IntStream.range(0, 10).forEach(f->{
           new Thread(()->{
               // 通过对象池获取t，之后执行
               try {
                   poolExec(pool);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           },"Thread-"+f).start();
        });

    }

    public static void poolExec(ObjPool<Connect,String> pool) throws InterruptedException {
        pool.exec(t -> {
            System.out.println(Thread.currentThread().getName()+"----"+t.getName());
            //得到对象后休眠1s再返回，延迟资源释放时间，可以看到在资源未释放前有5个线程获取到了对象
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return t.toString();
        });
    }
}
