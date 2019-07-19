package thread.wang_bao_ling.c16;

import java.util.concurrent.Semaphore;

/**
 * 信号量能
 */
public class Semaphore2 {
    static int count;
    //初始化信号量
    static final Semaphore s
            = new Semaphore(1);
    //用信号量保证互斥
    static void addOne() throws InterruptedException {
        //相当于down
        s.acquire();
        try {
            count+=1;
        } finally {
            //相当于up
            s.release();
        }
    }
}
