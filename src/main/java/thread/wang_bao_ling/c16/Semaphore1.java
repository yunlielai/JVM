package thread.wang_bao_ling.c16;

import java.util.Queue;

/**
 *   init()：设置计数器的初始值。
 *   down()：计数器的值减1；如果此时计数器的值小于0，则当前线程将被阻塞，否则当前线程可以继续执行。
 *   up()：计数器的值加1；如果此时计数器的值小于或者等于0，则唤醒等待队列中的一个线程，并将其从等待队列中移除。
 */
public class Semaphore1 {
    // 计数器
    int count;
    // 等待队列
    Queue queue;
    // 初始化操作
    Semaphore1(int c){
        this.count=c;
    }
    //
    void down(){
        this.count--;
        if(this.count<0){
            //将当前线程插入等待队列
            //阻塞当前线程
        }
    }
    void up(){
        this.count++;
        if(this.count>=0) {
            //移除等待队列中的某个线程T
            //唤醒线程T
        }
    }
}
