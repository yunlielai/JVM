## 18. StampedLock：有没有比读写锁更快的锁？

### StampedLock支持的三种锁模式

  1. 写锁，相当于ReadWriteLock的写锁
  
  2. 悲观读锁，相当于ReadWriteLock的读锁
  
  3. 乐观读，这个操作是无锁的，所以相比较ReadWriteLock的读锁，乐观读的性能更好一些
  
### 乐观读的正确使用方式及基本实现原理

  - 示例代码：`Point`
  
### StampedLock使用注意事项

  1. StampedLock是不可重入锁
  
  2. StampedLock的悲观读锁、写锁都不支持条件变量
  
  3. 如果线程阻塞在StampedLock的readLock()或者writeLock()上时，此时调用该阻塞线程的interrupt()方法，会导致CPU飙升。
  如果需要支持中断功能，一定使用可中断的悲观读锁readLockInterruptibly()和写锁writeLockInterruptibly()
  

