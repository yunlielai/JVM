## 17. ReadWriteLock：如何快速实现一个完备的缓存？

### 什么是读写锁

  1. 允许多个线程同时读共享变量；
  
  2. 只允许一个线程写共享变量；
  
  3. 如果一个写线程正在执行写操作，此时禁止读线程读共享变量。

### 实现一个缓存

  - 示例代码：`Cache1`
  
### 实现缓存的按需加载

  - 示例代码：`Cache2`
  
### 读写锁的升级与降级
  
  - 锁升级， ReadWriteLock并不支持锁升级，如果进行锁升级，会导致写锁永久等待
  

  ````
 //读缓存
  r.lock();         ①
  try {
    v = m.get(key); ②
    if (v == null) {
      w.lock();
      try {
        //再次验证并更新缓存
        //省略详细代码
      } finally{
        w.unlock();
      }
    }
  } finally{
    r.unlock();     ③
  }
   ````


  - 锁降级，ReadWriteLock只支持锁降级
  
  ````
  class CachedData {
    Object data;
    volatile boolean cacheValid;
    final ReadWriteLock rwl =
      new ReentrantReadWriteLock();
    // 读锁  
    final Lock r = rwl.readLock();
    //写锁
    final Lock w = rwl.writeLock();
    
    void processCachedData() {
      // 获取读锁
      r.lock();
      if (!cacheValid) {
        // 释放读锁，因为不允许读锁的升级
        r.unlock();
        // 获取写锁
        w.lock();
        try {
          // 再次检查状态  
          if (!cacheValid) {
            data = ...
            cacheValid = true;
          }
          // 释放写锁前，降级为读锁
          // 降级是可以的
          r.lock(); ①
        } finally {
          // 释放写锁
          w.unlock(); 
        }
      }
      // 此处仍然持有读锁
      try {use(data);} 
      finally {r.unlock();}
    }
  }
  ````


