package thread.wang_bao_ling.c15;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过ReentrantLock实现简单的阻塞队列
 * 对比Synchronized通过wait，notifyAll实现阻塞队列，使用ReentrantLock和Condition实现的管程支持多条件变量，而Synchronized实现的管程只支持一个条件变量
 * 比如使用Synchronizatized实现阻塞队列的时候入队和出队的wait都会存在同一个条件等待队列中，因为Synchronizatized实现的管程只支持一个条件变量，执行notify或notifyAll时不能精确通知入队或出队的wait
 * 而使用Conditiond时定义的notFull和notEmpty是两个条件变量，会有两个等待队列，调用对应条件变量会进入对应的等待队列中
 * @1和@2处await的线程分别会进入notFull条件变量的等待队列和notEmpty条件变量的等待队列
 */
public class ReentrantLockQueue {
    final ReentrantLock lock = new ReentrantLock();
    // 条件变量：队列不满
    final Condition notFull =  lock.newCondition();
    // 条件变量：队列不空
    final Condition notEmpty =  lock.newCondition();

    private List queue = new LinkedList();

    private int limit = 10;

    // 入队
    public void enq(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (this.queue.size() == this.limit) {
                // 等待队列不满
                notFull.await();//@1
            }
            notEmpty.signal();
            this.queue.add(x);
        }finally {
            lock.unlock();
        }
    }

    // 出队
   public Object deq() throws InterruptedException {
        lock.lock();
        try {
            while (this.queue.size() == 0) {
                // 等待队列不空
                notEmpty.await();//@2
            }
            notFull.signal();
            return this.queue.remove(0);
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockQueue queue=new ReentrantLockQueue();
        queue.enq("one");
        queue.enq("two");
        queue.deq();
        queue.print();
    }

    public void print(){
        queue.stream().forEach(System.out::println);
    }
}
