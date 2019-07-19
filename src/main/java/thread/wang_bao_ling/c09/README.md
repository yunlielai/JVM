## 09. Java线程（上）：Java线程的生命周期

### Java中线程的生命周期

  > 对于操作系统来讲BLOCKED，WAITING，TIMED_WAITING都为sleep（使用top -H -p 查看Threads状态会显示在sleep中,RUNNABLE显示在running中，处于sleep中的线程不会分配时间片）

  - NEW（初始化状态）
  
  - RUNNABLE（可运行/运行状态，io阻塞时也为该状态）
  
  - BLOCKED（阻塞状态）
  
  - WAITING（无时限等待）
  
  - TIMED_WAITING（有时限等待）
  
  - TERMINATED（终止状态）
  
### 状态间的转换
  
  - **RUNNABLE与BLOCKED的状态转换**
  
        有一种场景会触发这种转换，就是线程等待synchronized的隐式锁
  
  - **RUNNABLE与WAITING的状态转换**
  
     1. 获得synchronized隐式锁的线程，调用无参数的Object.wait()方法
     
     2. 第二种场景，调用无参数的Thread.join()方法
     
     3. 第三种场景，调用LockSupport.park()方法
     
  - **RUNNABLE与TIMED_WAITING的状态转换**

        RUNNABLE与WAITING的状态转换中的场景带上超时时间+Thread.sleep(long millis)
     
  - **从NEW到RUNNABLE状态**
  
        调用线程的start()方法

  - **从RUNNABLE到TERMINATED状态**
  
      1. run方法执行完
     
      2. run方法执出现异常
     
      3. 调用stop方法（已标记为@Deprecated()，调用会立即杀死线程，建议使用interrupt()）
     
### interrupt方法的使用

  1. 当线程A处于WAITING、TIMED_WAITING状态时
  
         线程A返回到RUNNABLE状态，同时线程A的代码会触发InterruptedException异常，并在异常代码执行前会重置中断状态为false
  
  2. 当线程A处于WRUNNABLE状态且阻塞在java.nio.channels.InterruptibleChannel上时
  
         线程A会触发java.nio.channels.ClosedByInterruptException这个异常
  
  3. 当线程A处于WRUNNABLE状态且阻塞在java.nio.channels.Selector上时       

         线程A会触发java.nio.channels.ClosedByInterruptException这个异常
     
  4. 当线程A处于RUNNABLE状态并且没有阻塞在某个I/O操作上时
  
         线程A可以通过isInterrupted()方法，检测是不是自己被中断了      

### 上下文切换

  - 引起上下文切换的原因
  
    1. 时间片用完，CPU正常调度下一个任务
        
       > 示例：Test1
       
       > 在使用cas操作的时候不停的进行自旋，不会挂起线程（会导致上下文切换），直到线程的时间片用完才会进行上下文切换，
       
    2. 被其他优先级更高的任务抢占
    3. 执行任务碰到IO阻塞，调度器挂起当前任务，切换执行下一个任务
    4. 用户代码主动挂起当前任务让出CPU时间
       
       > 示例：Test2
       
       > BLOCKED，WAITING，TIMED_WAITING对于cpu来说都是sleep，都会被挂起
       
    5. 多任务抢占资源，由于没有抢到被挂起
    6. 硬件中断