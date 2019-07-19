package thread.wang_bao_ling.c05;

/**
 * 线程死锁演示
 */
public class DemoDealLock {
    private MyObj obj1=new MyObj();
    private MyObj obj2=new MyObj();

    public static void main(String[] args) throws Exception {
       new DemoDealLock().demoDealLock1();
    }

    public void demoDealLock1() throws Exception{
        new Thread("t1"){
            @Override
            public void run() {
                synchronized (obj1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    obj2.print();
                }
            }
        }.start();

        Thread.sleep(500);
        new Thread("t2"){
            @Override
            public void run() {
                synchronized (obj2) {
                    obj1.print();
                }
            }
        }.start();
    }

    public class MyObj{
        public synchronized void print(){
            System.out.println(Thread.currentThread().getName());
        }
    }



}
