package thread.wang_bao_ling.c01;

/**
 * 指令重排问题演示
 *
 * 在new操作上，我们以为的new操作应该是：
 *    分配一块内存M；
 *    在内存M上初始化Singleton对象；
 *    然后M的地址赋值给instance变量。
 * 但是实际上优化后的执行路径却可能这样的：
 *    分配一块内存M；
 *    将M的地址赋值给instance变量；
 *    最后在内存M上初始化Singleton对象。
 * 这时如果线程二执行到@1处会发现instance不为空，然后直接返回，这个时候还没有执行初始化Singleton对象，会有问题
 *
 */
public class Singleton {
    static  Singleton  instance;
    static  Singleton getInstance(){
        if (instance == null) {//@1
            synchronized(Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
