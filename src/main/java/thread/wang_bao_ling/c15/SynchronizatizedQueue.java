package thread.wang_bao_ling.c15;

import java.util.LinkedList;
import java.util.List;

/**
 * 通过Synchronized实现简单的阻塞队列实现
 * 在@1和@2处执行的wait会进入到同一个条件变量的等待队列，虽然这个示例中队列里不会同时存在两种wait线程
 *
 */
public class SynchronizatizedQueue {

    private List queue = new LinkedList();
    private int limit = 10;


    public synchronized void enq(Object x) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();//@1
        }
        notifyAll();
        this.queue.add(x);
    }

    public synchronized Object deq() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();//@2
        }
        notifyAll();
        return this.queue.remove(0);
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizatizedQueue queue=new SynchronizatizedQueue();
        queue.enq("one");
        queue.enq("two");
        queue.deq();
        queue.print();
    }

    public void print(){
        queue.stream().forEach(System.out::println);
    }
}
