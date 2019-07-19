package thread.wang_bao_ling.c18;

import java.util.concurrent.locks.StampedLock;

/**
 * 乐观读实现原理：在获取乐观读的时候会返回stamp，
 * 1.先通过乐观读得到当前的stamp(可以理解为是版本号，这个版本号在获取写锁的时候会累加)
 * 2.定义局部变量保存需要读的全局变量
 * 3.通过传入stamp给validate方法判断是否又写操作，没有则直接使用局部变量中的变量处理业务逻辑
 * 4.如果validate返回false表示又写操作在进行，就将乐观读升级为悲观读锁去读取全局变量，然后赋值给局部变量
 */
public class Point {
    private int x, y;
    final StampedLock sl = new StampedLock();
    //计算到原点的距离
    double distanceFromOrigin() {
        // 乐观读
        long stamp =
                sl.tryOptimisticRead();
        // 读入局部变量，
        // 读的过程数据可能被修改
        int curX = x, curY = y;

        //判断执行读操作期间，
        //是否存在写操作，如果存在，
        //则sl.validate返回false
        if (!sl.validate(stamp)){
            // 升级为悲观读锁
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                //释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(curX * curX + curY * curY);
    }
}
