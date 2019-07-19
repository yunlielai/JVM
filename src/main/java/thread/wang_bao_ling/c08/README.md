## 08. 管程：并发编程的万能钥匙

### wait()的正确姿势

  - 需要在while循环里面调用wait，因为wait的线程被notify通知后会进入锁等待队列里面重新获取锁，并不会被立即执行
  
  - wait不被立即执行示例：`DemoWait`
  
  - wait正确使用实现简单队列的示例：`SimpleBlockingQueue`

### notify()何时可以使用(c6中不满足条件1，所已要使用notifyAll)

  1. 所有等待线程拥有相同的等待条件；
  
  2. 所有等待线程被唤醒后，执行相同的操作；
  
  3. 只需要唤醒一个线程。
  
### join 实现原理

  - 示例代码：`test.c03_mianshi.Test3` , `MyThread`

### 为什么对象中的String和Integer字段不适合做锁对象

    首先对象中的属性是可能会被改变，一旦被改变就无法保证互斥性了，而且String,Integer,Boolean对象在JVM里面是可能被重用的。如果其他代码 synchronized(你的锁)，而且不释放，那你的程序就永远拿不到锁，这是隐藏的风险

   > 属性被改示例代码：`Test2`
  
   > 被重用示例代码：`Test3`

