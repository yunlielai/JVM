package thread.wang_bao_ling.c06;

import java.util.List;

/**
 * 为什么@2处使用notifyAll?
 *   假设我们有资源A、B、C、D，线程1申请到了AB，线程2申请到了CD，
 *   此时线程3申请AB，代码会进入到@1处执行wait()，此时会释放Allocator对象的锁，并进入wait队列等待notify通知
 *   接着线程4申请CD也会进入等待队列等待notify通知。
 *   我们再假设之后线程1归还了资源AB，如果使用notify()来通知等待队列中的线程，有可能被通知的是线程4
 *   但线程4申请的是CD，所以此时线程4还是会继续等待，而真正该唤醒的线程3就可能再也没有机会被唤醒了。
 *   即使后面有线程5执行了notify且刚好通知到了3，虽然3会申请到资源，但等待线程5通知的线程又得wait了
 *   如果使用得是notifyAll,则只要有一个线程执行了@2，则所wait队列中得线程都会被通知到，只要线程获取到了Allocator对象的锁，就会执行wait后的代码，就会重新进入while判断
 */
public class Allocator {
    private List<Object> als;

    // 一次性申请所有资源
    synchronized void apply(Object from, Object to) {
        // 经典写法，此处执行notifyAll()可能是其他账号归还资源，所以还得通过while重新判断条件释放满足
        while (als.contains(from) || als.contains(to)) {
            try {
                wait(); //@1
            } catch (Exception e) {
            }
        }
        als.add(from);
        als.add(to);
    }

    // 归还资源
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();//@2
    }
}
