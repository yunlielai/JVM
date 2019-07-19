### java引用类型


#### 强引用 ( Strong Reference )
   
    强引用所引用的对象在任何时候都不会被系统回收，即使即将要内存溢出。
    
   > 示例：StrongReferenceTest

#### 软引用 ( Soft Reference )

    软引用的引用对象在无其他引用（Finalizer对象的引用不算）引用时且内存不足时会被gc回收
    
   > 示例：SoftReferenceTest

#### 弱引用 ( Weak Reference )

    弱引用的引用对象在无其他引用（Finalizer对象的引用不算）引用时会被gc回收
    
   > 示例：WeakReferenceTest
      
#### 虚引用 ( Phantom Reference )

    虚引用的引用对象在无其他引用（Finalizer对象的引用不算）引用时会被gc回收
    
   > 示例：PhantomReferenceTest


#### Reference Handler 执行原理

1. 创建Reference Handler线程
    
      在第一次加载Reference类时会执行类中的静态代码块，代码块中会创建一个高优先级的守护线程Reference Handler。
      Reference Handler线程中通过一个死循环不断的调用tryHandlePending方法
      tryHandlePending方法中会判断pending对象是否为空，为空则在一个全局锁对象lock上进行wait阻塞Reference Handler线程
       
      关于pending对象：
      
       pending为一个Reference对象，它和另外一个Reference对象discovered构成一个Reference单向链表，pending为头节点，discovered为next节点
       
2. 添加 Reference对象到Reference链表，断开Reference对象对引用对象的引用

     在发生gc时，如果Reference的引用对象符合gc回收条件（不同的引用条件不同，如软引用为内存不足时）
     会断开Reference对象对引用对象的引用并将Reference添加到Reference链表，引用对象的gc回收逻辑也同时进行（比如有重写finalize方法会执行finalize方法）

3. jvm唤醒Reference Handler线程，触发入队操作      
      
     在第2步添加完pending链表后jvm会执行lock的notify操作，此操作“虚引用”需要等待引用对象被gc回收，finalize方法执行完毕。其他引用可用不用等待引用对象被gc回收
    
     执行完notify后Reference Handler线程会被唤醒，此时Reference链表头节点pending可能已不为空，会取出该节点入队到该节点关联的queue队列中
     

#### Finalizer 执行原理

1. 创建Finalizer线程
    
      在第一次加载Finalizer类时会执行类中的静态代码块，代码块中会创建一个低优先级的守护线程Finalizer。
      Finalizer线程中通过一个死循环不断的从queue队列（Finalizer类的静态ReferenceQueue队列）中remove出Finalizer对象
      由于一开始queue队列时空的，所以会阻塞，等待queue中添加Finalizer对象
    
2. jvm调用Finalizer.register
   
    在新建对象的时候如果对象有重写finalize方法则jvm会调用Finalizer.register，该方法中会干两个事情
    
    > Finalizer.register 调用时机可用通过RegisterFinalizersAtInit jvm参数指定
      
     1. 调用父类的构造方法
      
             super(finalizee, queue);
             
          其中finalizee为新建的对象，queue为Finalizer类的静态ReferenceQueue队列，执行完此操作后finalizee会被Finalizer的属性referent（继承自父类Reference）引用。queue会被父类Reference的queue引用
      
     2. 执行add方法将当前Finalizer对象添加到Finalizer双向链表中

        Finalizer链如下
    
        ![](http://gitimg.zhaozhubao.com/gitimg/微信截图_20190612113210.png)

        > Finalizer链不会被gc回收是因为此链中有一个static 的unfinalized强引用当前Finalizer对象，所以被Finalizer中referent引用的新建对象finalizee也不会被gc回收
  
3. gc时jvm添加Finalizer对象到引用队列，执行 runFinalizer方法中的处理逻辑

      发生gc的时候，如果某个Finalizer对象的引用对象除了被Finalizer对象引用外再无其他强引用，则会被jvm添加到这个Finalizer对象的queue队列中
      由于queue队列中新增了Finalizer对象，所以Finalizer线程会被唤醒，再从queue中remove出添加的Finalizer后会调用该对象的runFinalizer方法
       
      runFinalizer方法中会干以下几件事情：
       
       1. 将Finalizer对象从Finalizer链中剥离，这样Finalizer对象会被gc回收
       2. 执行Finalizer对象引用对象的finalize方法
       3. 清除Finalizer对finalizee的引用
       
     干完上面几个事情后，该Finalizer对象的引用对象就可被后续的gc回收了，所以回收一个重写了finalize方法的对象至少要经理两次gc
     
     > 两次回收示例：FinalizeTest
    