## 20. 并发容器：都有哪些“坑”需要我们填？

### 基于synchronized的同步容器

  - **获取途径**
  
    1. 通过Collections工具类获取的通过容器（其实就是将之前线程不安的容器的操作方法加上synchronized）
    2. Vector
    3. Stack
    4. Hashtable
    
  - **注意事项**
    
    > 多线程场景下对同步容器的遍历需要锁住遍历对象
    
    示例代码：
      ````
      List list = Collections.
        synchronizedList(new ArrayList());
      synchronized (list) {  
        Iterator i = list.iterator(); 
        while (i.hasNext())
          foo(i.next());
      }    
     ````
### 并发容器

  ![](http://gitimg.zhaozhubao.com/gitimg/c20_01.png)
  
  - **List**
  
    - CopyOnWriteArrayList
    
            顾名思义就是写的时候会将共享变量新复制一份出来，这样做的好处是读操作完全无锁
            其实现原理决定它适用于写操作非常少的场景，而且能够容忍读写的短暂不一致，而且迭代的时候不支持增删改
    
        > 迭代时删除元素示例代码：`test.c03_mianshi.Test3`
  
  - **Map**
  
    - ConcurrentHashMap
        
            key是无序的，且key和value都不能为空
  
    - ConcurrentSkipListMap
        
            key是无序的，且key和value都不能为空，由于基于跳表，所以适用于并发度非常高的场景

  - **Set**
    
    - CopyOnWriteArraySet
    
            参考CopyOnWriteArrayList
  
    - ConcurrentSkipListMap
        
            参考ConcurrentSkipListMap
  
  - **Queue（单端以Queue为后缀，双端以Deque为后缀）**
  
    - 单端阻塞队列
    
      ArrayBlockingQueue
            
            内部队列基于数组实现，支持有界的
            
      LinkedBlockingQueue
      
            内部队列基于链表实现，支持有界的
            
      SynchronousQueue
      
            内部不持有队列，此时生产者线程的入队操作必须等待消费者线程的出队操作
            
       LinkedTransferQueue
       
            融合LinkedBlockingQueue和SynchronousQueue的功能，性能比LinkedBlockingQueue更好
            
       PriorityBlockingQueu
       
            支持按照优先级出队
            
       DelayQueue
       
            支持延时出队
    
    - 双端阻塞队列
    
      LinkedBlockingDeque
  
    - 单端非阻塞队列
      
      ConcurrentLinkedQueue
  
    - 双端非阻塞队列
    
      ConcurrentLinkedDeque
   

    
    