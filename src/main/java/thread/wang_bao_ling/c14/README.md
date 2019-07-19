## 14. Lock和Condition（上）：隐藏在并发包中的管程  

### Lock 通过”破坏不可抢占条件“解决synchronizatized无法解决的死锁的问题

- 如下三个api都能解决死锁问题

  > 注意：虽然能解决死锁问题，但还是可能发生活锁，活锁示例：`Account`
  
  1. void lockInterruptibly() //支持中断的API
  
  2. boolean tryLock(long time, TimeUnit unit) //支持超时的API
  
  3. boolean tryLock(); // 支持非阻塞获取锁的API
  
### Lock 如何保证可见性

    Lock 中会维护一个volatile 的statu 变量，在获取锁的时候将statu加1，解锁的时候减1
    后续线程只有在statu为0时才可得到锁，能保证在unlock前的操作对后续线程的lock操作时可见的
    依据happen before 1，2，3条
  
### 什么是可重入锁
  
    所谓可重入锁，顾名思义，指的是线程可以重复获取同一把锁，ReentrantLock 和 synchronizatized 都是可重入锁
  
   - synchronizatized可重入示例：`SynchronizatizedDemo`
  
   - ReentrantLoc可重入示例：`ReentrantLocDemo`
  
### 公平锁与非公平锁（synchronizatied是非公平锁）

    如果是公平锁，唤醒的策略就是谁等待的时间长，就唤醒谁，很公平
    如果是非公平锁，则不提供这个公平保证，有可能等待时间短的线程反而先被唤醒
