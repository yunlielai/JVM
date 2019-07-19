package thread.wang_bao_ling.c19;

import java.util.concurrent.TimeUnit;

/**
 * 通过休眠线程模拟数据库操作
 */
public class DataBaseHelper {
    public static int porderCount;
    public static int dorderCount;
    //查询未对账订单
    public static Object getPOrders()  {
        try {
            TimeUnit.MILLISECONDS.sleep(150);
        } catch  (InterruptedException e) {
            e.printStackTrace();
        }
        return new Integer(++porderCount);
    }

    //查询派送单
    public static Object getDOrders()  {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch  (InterruptedException e) {
            e.printStackTrace();
        }
        return new Integer(++dorderCount);
    }

    //执行对账操作
    public static Object check(Object pos, Object dos) {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch  (InterruptedException e) {
            e.printStackTrace();
        }
        return new Object();
    }

    //差异写入差异库
    public static void save(Object diff){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch  (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
