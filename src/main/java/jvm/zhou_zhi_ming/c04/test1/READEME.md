### jdk 命令工具
    
    如果使用的是openjdk，使用命令时如果出现异常，则需安装debuginfo，如安装1.8的debuginfo
    yum --enablerepo='*-debug*' install java-1.8.0-openjdk-debuginfo.x86_64

#### jps : jps [option]
   虚拟机进程状况工具
   
    -q    --只输出pid
    -m    --输出main方法的参数
    -l    --输出类的全名
    -v    --输出虚拟机启动时显示传入的参数
    
#### jstat : jstat[option pid [interval[s|ms][count]]]
   虚拟机统计信息监视工具，提供类装载、内存、垃圾收集、JIT编译等运行数据
   
    -class       --监视类装载，卸载数量，总空间及类装载所耗时间
    -gc          --监视java堆状况，包括Eden区，两个survivor区，老年代，永久代的容量，已用空间，GC时间合计等信息
    -gccapacity    --监控内容与-gc相同，但输出主要关注各区使用到的最大，最小空间
    -gcutil      --监控内容与-gc相同，但输出主要关注已使用空间栈总空间的百分百
    -gccause     --与-gcutil功能一样，但会额外输出导致上次GC产生的原因
    -gcnew       --监视新时代的GC情况
    -gcnewcapacity  --监视内容与-gcnew基本相同，输出主要关注使用的最大，最小空间
    -gcold          --监视老年代GC状况
    -gcoldcapacity  --监视内容与-gcold基本相同，输出主要关注使用的最大，最小空间
    -gcpermcapacity --输出永久代使用到的最大，最小空间
    -compiler    --输出JIT编译器编译过的方法，耗时等
    -printcompilation    --输出JIT编译过的方法，耗时等信息
    -gcmetacapacity    --查看元数据区使用情况
    
#### jinfo : jinfo [option] pid
   Java配置信息工具
    
    -flags    --查看虚拟机启动参数，包含默认选项
    -sysprops    --输出虚拟机进程的System.getProperties()的内容

#### jmap : jstack [option] pid
   Java堆栈跟踪工具
   
    -F    --当正常输出无响应时，用此参数强制输出
    -l     --除堆栈外，显示关于锁的附加信息
    -m    --如果调用的本地方法，可以显示C/C++的堆栈
    
#### jcmd : jcmd pid [option] 
   1.7中引入的命令工具，直接执行jcmd相当于jps功能，通过jcmd pid help 可查看可使用的参数
   
    VM.native_memory summary    --需要使用-XX:NativeMemoryTracking=summary开启summary追踪，能看到java进程各区域的保留内存和提交内存（包括本地区域）
    GC.heap_info    --查看新生代，老年代，永久代的使用情况(windows 版本的jdk没有此命令)
    
    Thread.print -l -- 打印线程栈
    VM.command_line    -- 打印启动命令及传入的参数
    GC.heap_dump /data/31275.dump -- 堆转存储 jcmd pid GC.heap_dump ./dump.log
    GC.class_histogram -- 查看类的统计信息
    VM.system_properties -- 查看系统属性内容，和jinfo -sysprops类似
    VM.uptime -- 查看虚拟机启动时间
    PerfCounter.print --查看性能统计

### javap : javap [option] xxx.class

    -c 输出类反汇编内容，不包括常量池等附加信息
    -v 输出类反汇编和附加信息
    -p 输出所有类和成员，包括私有类，方法等
    
#### 实际应用
    
   - tomcat设置jvm参数
   
    修改TOMCAT_HOME/bin/catalina.sh
    在位置cygwin=false前，加入jvm参数如：
    JAVA_OPTS="-server -Xms256m -Xmx512m -XX:PermSize=64M -XX:MaxPermSize=128m"
   
   - 查找cup或内存占用率过高的线程
   
     1. 通过jps 查看需要查找的java进程的pid 如：1000
     2. 通过top -H -p 1000 查看该进程下的所有线程，找出最耗资源的线程pid 如：1001
     3. 执行jstatck 1001 >dump.txt 获取线程快照
     4. 将1001转成十六进制在dump.txt查找对应的线程