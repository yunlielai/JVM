### 堆OOM异常

- 线程抛出堆内存溢出时，其他线程仍能正常运行
  
   > 演示类：Test1
     
- linux OOM-killer机制杀死进程

      Linux系统内存不足时会触发 Linux Kernel OOM(Out of memory killer)保护机制，将占用内存大的进程杀死，以保证系统正常运行，
      最终OOM killer是通过/proc/<pid>/oom_score这个值来决定哪个进程被干掉的。
      这个值是系统综合进程的内存消耗量、CPU时间(utime + stime)、存活时间(uptime - start time)和oom_adj计算出的，消耗内存越多分越高，存活时间越长分越低
    
   > 演示类：Test2
   
    演示环境：Centos7 64位系统,jdk1.8 
   
   内存：
   
   ![](http://gitimg.zhaozhubao.com/gitimg/微信截图_20190605111719.png)
    
   效果：（注意启动参数-Xmx设置要设置大于8g(swap也要算进去)，不然程jvm自己就抛出堆内存溢出异常了）
   
   ![](http://gitimg.zhaozhubao.com/gitimg/微信截图_20190605111641.png)