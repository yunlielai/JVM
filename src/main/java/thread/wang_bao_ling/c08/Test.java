package thread.wang_bao_ling.c08;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread=new MyThread("myThread");
        myThread.start();

        new Thread(()->{
            try {
                //休眠2秒钟，myThread线程执行完要5秒钟
                Thread.sleep(2000);
                //新开线程手动触发myThread锁对象的notifyAll方法
                // 导致main线程提前从myTread锁对象的wait中唤醒
                //但由于使用了while循环判断myThread线程是否结束，所以会再次wait
                myThread.wakeUp();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        myThread.myJoin();
        System.out.println("main end");
        
    }
}
