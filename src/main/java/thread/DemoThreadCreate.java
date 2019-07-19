package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建线程的两种方式
 */
public class DemoThreadCreate {
    static DemoThreadCreate test=new DemoThreadCreate();
    public static int k=0;
    static AtomicInteger i = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //c01_factory();
        two();
        //three();
    }

    //通过实现Runable接口，推荐此方式，可以在Runable对象中共享数据，而且Runable对象还可以去实现其他接口
    public static void one() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("this is c01_factory run");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


    /**
     * 通过CallAble的方式
     *  与RunAble方式的区别：能将异常往上抛，能有返回值
     *  实现原理：FutureTask实现了RunableFuture接口，而RunableFuture接口继承了Runable接口和Future接口
     *  在FutureTask中run方法中会调用call方法并将call返回的结果存储在FutureTask对象中
     */
    public  static void two() throws ExecutionException, InterruptedException {
        Callable callable=new Callable() {
            @Override
            //此处可以抛出异常，而runable方式的run方法中不能抛出异常
            public Object call() throws Exception {
                System.out.println("this is callable");
                //此处可以有返回值
                return "test";
            }
        };
        FutureTask task=new FutureTask(callable);
        new Thread(task).start();
        System.out.println(task.get());
    }


    //通过覆盖Thread类中的run方法，不推荐此方式
    public static void three() {
        new Thread(){
            @Override
            public void run() {
                System.out.println("this is c02_builder run");
            }
        }.start();
    }
}





