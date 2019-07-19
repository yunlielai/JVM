package thread.wang_bao_ling.c03;

public class SafeCalc {
    long value = 0L;

    /**
     * get方法是线程不安全的，无法保证获取value的可见性
     * 可以通过给get方法添加synchronized实现线程安全，因为添加synchronized后会给方法添加this对象的锁，能保证执行get的时候addOne已解锁，使得对value的更改同步到主存
     */
    long get() {
        return value;
    }

    /**
     * addOne 方法是线程安全的，因为synchronized能保证 value += 1 的原子性
     * 再根据happen before 4（管程中锁的规则）能保证当前线程对value的修改对后续获取锁（this对象的锁）的线程可见
     */
    synchronized void addOne() {
        value += 1;
    }
}
