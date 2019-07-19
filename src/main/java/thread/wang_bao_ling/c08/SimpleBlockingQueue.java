package thread.wang_bao_ling.c08;

import java.util.LinkedList;
import java.util.List;

/**
 *  简单的阻塞队列实现
 */
public class SimpleBlockingQueue {

    private List queue = new LinkedList();
    private int limit = 10;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void enqueue(Object item) throws InterruptedException {
        //此处需要使用while循环判断条件是否满足，因为wait执行后会释放锁，在其他线程notify后并不一定能得到锁
        //如果其他线程拿到锁后操作了queue可能条件就不满足了，需要重新判断
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }

    public synchronized Object dequeue()  throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            notifyAll();
        }
        return this.queue.remove(0);
    }

    public void print(){
        queue.stream().forEach(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue queue=new SimpleBlockingQueue(10);
        queue.enqueue("one");
        queue.enqueue("two");
        queue.dequeue();
        queue.print();

    }
}
