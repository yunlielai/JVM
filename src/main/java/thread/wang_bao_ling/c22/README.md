## 22. Executor与线程池：如何创建正确的线程池？

### 简易的线程池实现

  - 示例代码：`MyThreadPool`
  
### Java中的线程池的使用

- 使用示例：`Test`

    线程池各个参数的含义

   - **corePoolSize**
    
         表示线程池保有的最小线程数。有些项目很闲，但是也不能把人都撤了，至少要留corePoolSize个人坚守阵地
        
   - **maximumPoolSize**
    
         表示线程池创建的最大线程数。当项目很忙时，就需要加人，加人的前提是corePoolSize线程都在忙碌且工作队列满了（需是有界队列）
         那后面每來一个任务加一个线程，但是也不能无限制地加，最多就加到maximumPoolSize个人。
         当项目闲下来时，就要撤人了，最多能撤到corePoolSize个人
        
   - **keepAliveTime & unit**
        
         如果一个线程空闲了keepAliveTime unit(keepAliveTime的单位)这么久，而且线程池的线程数大于 corePoolSize 
         那么这个空闲的线程就要被回收了
        
   - **workQueue**
        
         工作队列，和MyThreadPool示例代码的工作队列同义
    
   - **threadFactory** 
        
         通过这个参数你可以自定义如何创建线程，例如你可以给线程指定一个有意义的名字。不传默认是pool-1-thread-* 格式
        
   - **handler** 
        
         如果线程池中所有的线程都在忙碌，并且工作队列也满了（前提是工作队列是有界队列），那么此时提交任务，线程池就会拒绝接收
        
       > ThreadPoolExecutor有以下4种策略：
       
       1. CallerRunsPolicy：提交任务的线程自己去执行该任务。
       2. AbortPolicy：默认的拒绝策略，会throws RejectedExecutionException。
       3. DiscardPolicy：直接丢弃任务，没有任何异常抛出。
       4. DiscardOldestPolicy：丢弃最老的任务，其实就是把最早进入工作队列的任务丢弃，然后把新任务加入到工作队列。
