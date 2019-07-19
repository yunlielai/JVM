package thread.wang_bao_ling.c09;

public class DemoInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            Thread ch=Thread.currentThread();

            while (true) {
                if (ch.isInterrupted()) {
                    break;
                }
                // 省略业务代码无数
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //触发异常后会重置中断标志，所以这个地方输出为false
                    System.out.println("异常代码中的中断标志："+ch.isInterrupted());
                    //此时线程状态为RUNNABLE，重新调用interrupt方法可在循环中捕获线程的中断状态
                    Thread.currentThread().interrupt();
                    System.out.println("重新触发后的中断标志："+ch.isInterrupted());
                }
            }
            System.out.println("跳出循环");
        });

        t1.start();
        Thread.sleep(100);
        t1.interrupt();
    }
}
