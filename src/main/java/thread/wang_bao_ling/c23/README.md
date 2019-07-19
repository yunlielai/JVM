## 23. Future：如何用多线程实现最优的“烧水泡茶”程序 

### 如何获取任务执行结果

 [FutureTask类关系](https://www.processon.com/diagraming/5cde65f9e4b020998addcd45)  

- Future<?> submit(Runnable task)
          
      提交Runnable任务 submit(Runnable task) ：这个方法的参数是一个Runnable接口，Runnable接口的run()方法是没有返回值的，所以 submit(Runnable task) 这个方法返回的Future仅可以用来断言任务已经结束了，类似于Thread.join()。
      
     > 示例代码：`Submit1`
      
- <T> Future<T> submit(Callable<T> task)

      提交Callable任务 submit(Callable<T> task)：这个方法的参数是一个Callable接口，它只有一个call()方法，并且这个方法是有返回值的，所以这个方法返回的Future对象可以通过调用其get()方法来获取任务的执行结果。
     
     > 示例代码：`MyThreadPool`
     
- <T> Future<T> submit(Runnable task, T result)

      提交Runnable任务及结果引用 submit(Runnable task, T result)：这个方法很有意思，假设这个方法返回的Future对象是f，f.get()的返回值就是传给submit()方法的参数result。这个方法该怎么用呢？下面这段示例代码展示了它的经典用法。需要你注意的是Runnable接口的实现类Task声明了一个有参构造函数 Task(Result r) ，创建Task对象的时候传入了result对象，这样就能在类Task的run()方法中对result进行各种操作了。result相当于主线程和子线程之间的桥梁，通过它主子线程可以共享数据。

     > 示例代码：`MyThreadPool`
     
  
