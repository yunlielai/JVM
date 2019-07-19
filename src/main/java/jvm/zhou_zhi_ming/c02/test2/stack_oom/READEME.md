### 栈OOM异常

#### 相关因素

- threads-max

      表示系统全局的总线程数限制，默认值为 29999

     - 查看方式
     
        方式一：
        
           cat /proc/sys/kernel/threads-max
        
        方式二：
           
           sysctl -a | grep threads-max
      
     - 设置方式
      
        方式一：运行时限制,临时生效
        
           echo 999999 > /proc/sys/kernel/threads-max
        
        方式二： 修改/etc/sysctl.conf，永久生效 
        
            sys.kernel.threads-max = 999999
- pid_max

      表示系统全局的PID号数值的限制，默认值为 131072

     - 查看方式
     
        方式一：
        
           cat /proc/sys/kernel/pid_max
        
        方式二：
           
           sysctl -a | grep pid_max
      
     - 设置方式
      
        方式一：运行时限制,临时生效
        
           echo 999999 > /proc/sys/kernel/pid_max
        
        方式二： 修改/etc/sysctl.conf，永久生效 
        
            sys.kernel.pid_max = 999999
   
- max_map_count   

      表示单个程序所能使用内存映射空间的数量限制
      在其他资源可用的情况下，单个vm能开启的最大线程数是这个值的一半
     
     - 查看方式
     
        方式一：
        
           cat /proc/sys/vm/max_map_count
        
        方式二：
           
           sysctl -a | grep max_map_count
      
     - 设置方式
      
        方式一：运行时限制,临时生效
        
           echo 999999 > /proc/sys/vm/max_map_count
        
        方式二： 修改/etc/sysctl.conf，永久生效 
        
           sys.vm.max_map_count = 999999
               
- max user process

      每个用户总的最大进程数(包括线程) 
      
     > 注意：系统默认时给root用户添加了/etc/security/limits.d/20-nproc.conf

     - 查看方式
      
           ulimit -u （-a查看所有）
           
     - 设置方式
      
        方式一：当前shell生效
        
           ulimit -u 999999
        
        方式二：针对用户配置永久生效
           
           在/etc/security/limits.conf文件中配置
           
                      
        
- virtual memory

      虚拟内存限制
      
     > 注意：系统默认时给root用户添加了/etc/security/limits.d/20-nproc.conf

     - 查看方式
      
           ulimit -u （-a查看所有）
           
     - 设置方式
      
        方式一：当前shell生效
        
           ulimit -u 999999
        
        方式二：针对用户配置永久生效
           
           在/etc/security/limits.conf文件中配置
           
          
#### 演示

  - 演示类：Test1
  
  - 演示环境：Centos7 64位系统,jdk1.8 
    
    ![](http://gitimg.zhaozhubao.com/gitimg/Image.png)
    
    为展示堆大小对最大线程数的影响，设置了进程可使用的最大虚拟内存（64位系统可使用的虚拟内存非常大，相当于没有限制）
   
        ulimit -v 16777216(16g)
        
    测试前各影响因素的值为：
    
        pid_max = 131072
        threads-max = 29999
        max_map_count = 65530
        ulimit -u = 14999
        
  - 测试结果：
    
    java -Xss1024k -Xmx512m -Xmx512m Test --1-- 递归数：10  线程数：12298  耗内存：676
    
    java -Xss1024k -Xmx256m -Xmx256m Test --2-- 递归数：10  线程数：12562  耗内存：687
    
    java -Xss512k -Xmx512m -Xmx512m Test  --3-- 递归数：10  线程数：14304  耗内存：733

    java -Xss256k -Xmx256m -Xmx256m Test  --4-- 递归数：10  线程数：14304  耗内存：725
    
    **修改thread-max值**
    
        echo 144400 > /proc/sys/kernel/threads-max
        
    java -Xss256k -Xmx256m -Xmx256m Test  --5--  递归数：10  线程数：32672  耗内存：1613
    
    **修改max_map_count值**
    
        echo 131060 > /proc/sys/vm/max_map_count
        
    java -Xss256k -Xmx256m -Xmx256m Test  --6--  递归数：10  线程数：48421  耗内存：2381
    
    **修改pid_max值**
     
        echo 13107 > /proc/sys/kernel/pid_max
        
    java -Xss256k -Xmx256m -Xmx256m Test --7-- 递归数：10  线程数：12736  耗内存：637
    
    **在同一参数下加大递归次数**
    
    java -Xss1024k -Xmx256m -Xmx256m Test
    
    递归数：10  线程数：12562  耗内存：687
    
    递归数：100  线程数：12562  耗内存：782
    
    递归数：1000  线程数：12562  耗内存：1415
    
    递归数：2000  线程数：12307  耗内存：2129
    
    递归数：2500  线程数：12307  耗内存：2491
    
    递归数：3000  线程数：12307  耗内存：2526
    
    递归数：3500  线程数：12052  耗内存：2526
    
    递归数：4000  线程数：12052  耗内存：2497
    
    递归数：5000  线程数：11797  耗内存：2543
    
    递归数：7000  线程数：11545  耗内存：2522（会出现栈溢出）
    
  - 总结：（前提是进程虚拟内存受限，如设置ulimit -v）
    
       1. 最大线程数=(进程最大虚拟内存 - 堆保留内存(-Xmx) - 堆外内存保留内存 )/ 线程栈大小(-Xss)
        所以减小堆保留内存或线程栈大小会获得更多的线程，但也会有上限，会受thread-max,max_map_count,pid_max这些值的限制。
        
       2. 通过加大递归次数（会加大线程的实际内存使用量），最大线程数量会有所降低
       
       3. 因为虚拟内存是可以大于实际物理内存的，所以当线程实际使用内存总量会大于物理内存会触发 linux OOM-killer机制杀死java进程，
       加大每个线程实际使用内存和加大虚拟内存（需要去除thread-max,max_map_count,pid_max等限制）会更容易触发这一操作；
        
        
