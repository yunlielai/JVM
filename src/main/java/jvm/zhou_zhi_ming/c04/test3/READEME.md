### jvm常用参数

#### 日志

    -XX:+PrintGC    --输出GC的简要信息
    -XX:+PrintGCDetails    --打印每个内存区的GC情况
    -XX:+PrintHeapAtGC    --打印更全面的堆GC情况
    -XX:+HeapDumpOnOutOfMemoryError    --在发生内存溢出时进行dump堆快照
    -verbose:gc    --和PrintGCDetails 一样
    
    
#### 堆

    -Xms    --设置堆最小值如：-Xms20m
    -Xmx    --设置堆最大值如： -Xmx20m
    -Xmn    --设置新生区大小如： -Xmn10m（等同：-XX:NewSize=10m -XX:MaxNewSize=10m）
    -XX:NewRatio    --设置新生区和老年区的比例：-XX:NewRatio=2 则老年区是新生区的2倍，即新生区占据内存的1/3
    -XX:SurvivorRatio    --设置Eden区和Survivor区的比例如：-XX:SurvivorRatio=8,如果新生区为10m, 则Eden区=(10/(8+2))*8,新生区中实际能使用的大小为9m(Eden+Survivor)
    -XX:MaxTenuringThreshold    --设置进入老年区的GC次数如：-XX:MaxTenuringThreshold=1


#### 永久代

    -XX:PermSize    --设置方法区初始大小，1.8已不存在
    -XX:MaxPermSize    --设置方法区最大限制，1.8已不存在
    
    ##### jdk1.8后移除了方法区，使用了 Metaspace， Metaspace使用的是本机内存
    -XX:MetaspaceSize    --设置Metaspace最小值
    -XX:MaxMetaspaceSize    --设置Metaspace最大值
    -XX:-UseCompressedOops    --关闭对象指针压缩（默认开启，当-Xmx设置大于32G会自动关闭）：
    -XX:-UseCompressedClassPointers    --关闭类指针压缩（默认开启，当-Xmx设置大于32G会自动关闭）：
    -XX:CompressedClassSpaceSize    --设置CompressedClassSpace大小
    注意：XX:CompressedClassSpaceSize 大小设置后就固定了，不能动态扩展，如果关闭UseCompressedOops或UseCompressedClassPointers 则不存在CompressedClassSpace


#### 栈

    -Xss    --设置栈区大小如：-Xss2M（Hotspot主机不区分虚拟机栈和本地方法栈，栈容量只由-Xss控制）

#### 垃圾回收器 

    -XX:+UseSerialGC              -- Serial+ Serial Old 收集器组合      （经测试为 linux上jdk1.8默认）
    -XX:+UseParNewGC              -- ParNew+ Serilal Old收集器组合                               
    -XX:+UseParallelGC            -- Parallel scavenge + Serial Old收集器组合    （经测试为 window上jdk1.8默认）          
    -XX:+UseParallelOldGC         -- Parallel scavenge + Parallel Old收集器组合                          
    -XX:+UseConcMarkSweepGC       -- ParNew+CMS+Serial Old(备用)收集器组合                
    (-XX:+UseConcMarkSweepGC
    -XX:-UseParNewGC)             -- Serial+CMS+Serial Old(备用)收集器组合                     
    -XX:+UseG1GC                  -- G1收集器并发     
              
    ##### 每种垃圾回收器在gc日志中对应的名称   
    Serial -- DefNew
    ParNew -- ParNew
    ParallelScavenge -- PSYoungGen
    SerialOld  -- Tenured
    ParallelOld -- ParOldGen
    CMS -- CMS

#### 即时编译 
    
    -XX:+PrintCompilation   -- 打印被编译的代码，带%号的表示被编译的方法
    -XX:+UnlockDiagnosticVMOptions -- 使用-XX:+PrintOptoAssembly 参数需要先开启此设置
    -XX:+PrintOptoAssembly  -- 输出方法内联情况
    
    -XX:CompileThreshold   -- 触发方法编译的阈值，client默认1500，server模式10000
    -XX:-UseCounterDecay    -- 关闭半衰周期即不管过多久方法计数器都不减半
    -XX:CounterHalfLifeTime -- 设置半衰时间，单位是ms
    
    -XX:OnStackReplacePercentage    -- 用于计算回边计数器阈值，默认值933，
    client计算公式：方法调用计数器阈值（CompileThreshold）×OSR比率（OnStackReplacePercentage）/100
    server计算公式：方法调用计数器阈值（CompileThreshold）×（OSR比率（OnStackReplacePercentage）-解释器监控比率（InterpreterProfilePercentage）/100
    InterpreterProfilePercentage默认值为140
    
    -XX:+DoEscapeAnalysis    -- 开启方法逃逸分析，jdk1.7默认是开启的
    -XX:+EliminateAllocations   -- 开启标量替换

#### 其他   


    -XX:NativeMemoryTracking=summary    --开启原生内存跟踪，默认是off，可以设置为summary，detail
    -XX:+PrintFlagsFinal    --可用通过 java -XX:+PrintFlagsFinal -version | grep -i 'stack' 查看栈初始大小（ThreadStackSize）