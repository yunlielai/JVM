## Java并发编程实战
 
 
### 1. 可见性、原子性和有序性问题：并发编程Bug的源头
  
  - 线程不安全示例：`test.c03_mianshi.Test3`
  
  - 单例问题示例：`Singleton`

### 2. Java内存模型：看Java如何解决可见性和有序性问题

  - happen before 1,2,3,4条示例：`test.c03_mianshi.Test3`

### 3. 互斥锁（上）：解决原子性问题

  - synchronized
   
### 4. 互斥锁（下）：如何用一把锁保护多个资源？

  - 锁住不相干多个资源示例：`Account1`

  - 锁住相干多个资源错误示例：`Account2`
  
  - 锁住相干多个资源正确示例：`Account3`（通过传入锁对象）
  
  - 锁住相干多个资源正确示例：`Account4`（使用Account.class作为锁对象）
  
### 5. 一不小心就死锁了怎么办？
   
   - 通过同时锁住转入和转出账号控制锁粒度解决锁住多个资源时的性能问题示例：`Account1`（会引入死锁问题）
   
   - 通过破坏“破坏占用且等待条件”解决死锁问题（使用Allocator来同时申请转入和转出账号实现）示例：`Account2`
   
   - 通过“破坏循环等待条件”解决死锁问题（引入排序id实现）示例：`Account3`
   
   
### 6. 用“等待-通知”机制优化循环等待

  - 将5中Account2解决死锁的方式换成wait，notify实现，避免使用while一直去发送申请
    
### 7. 安全性、活跃性以及性能问题

### 8. 管程：并发编程的万能钥匙

  - wait被唤醒后获取不到锁示例：`DemoWait` 
  
  - 为什么对象中的String和Integer字段不适合做锁对象
  
  - notify()何时可以使用
   
  - join 实现原理示例：`MyThread`，`test.c03_mianshi.Test3`
   
  - 通过wait，notify，synchronizatized实现简单的阻塞队列
   
### 9. Java线程（上）：Java线程的生命周期

  - java 中线程的状态，以及状态间的转换
   
  - 线程中断示例：`DemoInterrupted`
  
  - 上下文切换
   
### 10. Java线程（中）：创建多少线程才是合适的？

### 11. Java线程（下）：为什么局部变量是线程安全的？-- 跳过    

### 12. 如何用面向对象思想写好并发程序？-- 跳过

### 13. 理论基础模块热点问题答疑-- 跳过

### 14. Lock和Condition（上）：隐藏在并发包中的管程
   
  - synchronizatized是可重入锁示例：`SynchronizatizedDemo`
   
  - ReentrantLock是可重入锁示例：`ReentrantLocDemo`
   
  - 使用 ReentrantLock 解决死锁问题示例：`Account`（可能会产生活锁）
   
### 15. Lock和Condition（下）：Dubbo如何用管程实现异步转同步？
  
  - ReentrantLock和Condition实现管乘支持多条件变量（Synchronizatized只支持单条件变量）
  
  -  ReentrantLock和Condition实现缓冲区

### 16. Semaphore：如何快速实现一个限流器？
  
  - 代码化的信号量模型，示例：`Semaphore1`
   
  - 信号量实现互斥，示例：`Semaphore2`
   
  - 信号量实现限流器，示例：`ObjPool`,`Connect`,`Test3`

### 17. ReadWriteLock：如何快速实现一个完备的缓存？

  - 什么是读写锁
  
  - 实现一个缓存
  
  - 实现缓存的按需加载 
  
  - 读写锁的升级与降级

### 18. StampedLock：有没有比读写锁更快的锁？
  
  - StampedLock支持的三种锁模式

  - 乐观读的正确使用方式及基本实现原理
  
  - StampedLock使用注意事项

### 19. CountDownLatch和CyclicBarrier：如何让多线程步调一致？
  
  - CountDownLatch代码示例：`Reconciliation3`
  
  - CyclicBarrier代码示例：`Reconciliation5`

### 20. 并发容器：都有哪些“坑”需要我们填？

  - 基于synchronized的同步容器
  
  - 同步容器使用的注意事项
  
  - 并发容器

### 21. 原子类：无锁工具类的典范

### 22. Executor与线程池：如何创建正确的线程池？

### 23. Future：如何用多线程实现最优的“烧水泡茶”程序？

### 24. CompletableFuture：异步编程没那么难

### 25. CompletionService：如何批量执行异步任务？

### 26. Fork Join：单机版的MapReduce




