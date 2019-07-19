## 21. 原子类：无锁工具类的典范


### 原子类操作的原理

  - 原子类操作是无锁的并发实现，其实现是依赖操作系统的CAS指令+自旋+volatile(保证变量的可见性) 
  - 代码化示例：`SimulatedCAS`
  - 原子类操作和synchronized性能对比示例代码：`AtomicReferenceTest`

### 原子类

  ![](http://gitimg.zhaozhubao.com/gitimg/c21_01.png)
  
  - 原子化的基本数据类型

    > 示例代码：`AtomicIntegerTest`
  
  - 原子化的对象引用类型

        AtomicStampedReference和AtomicMarkableReference这两个原子类可以解决ABA问题。
  
  - 原子化数组

        原子化数组提供的方法和原子化的基本数据类型的区别仅仅是：每个方法多了一个数组的索引参数

  - 原子化对象属性更新器

        注意要给对象属性加volatile，只有这样才能保证可见性；

  - 原子化对象属性更新器
  
        相比原子化的基本数据类型，速度更快，但是不支持compareAndSet()方法