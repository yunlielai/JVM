## 16. Semaphore：如何快速实现一个限流器？

### 信号量模型

    信号量能实现线程间互斥，无法实现线程间同步如wait，notify或Condition中的signal和await功能，但能多个线程访问一个临界区，能用于实现限流器的功能

  - 代码化模型：`Semaphore1`
 
### 信号量实现互斥

  - 示例代码：`Semaphore2`
  
### 信号量实现限流器

  - 示例代码：`ObjPool,Connect` , `test.c03_mianshi.Test3`


