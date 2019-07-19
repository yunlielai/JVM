### 元数据区OOM

    jdk 1.8 中移除了方法区，将类元数据存储在了虚拟机外的元空间中，将常量池等信息移动到了堆中存储


- 验证常量池移动到了堆中 

   > 演示类：Test1
   
   > 关于字符串常量的东西，可查看 [javaString对象](https://www.processon.com/view/link/5cf06c4fe4b06e3f4fa63be8)
     
- 验证类元数据移动到了元空间中

   > 演示类：Test2

- 关于元空间

      1. 如果开启了-XX:+UseCompressedOops及-XX:+UseCompressedClassesPointers(默认都是开启，关闭其中一个则不存在CompressedClassSpace)
      那么元空间Metaspace会包含类指针压缩空间CompressedClassSpace，可用通过CompressedClassSpaceSize设置类指针空间大小，默认是1G，设置后不可动态扩展
      
      2. 如果 CompressedClassSpace实际使用量+Metaspace其他区域的实际使用量大于MaxMetaspaceSize的值，则会抛出"java.lang.OutOfMemoryError: Metaspace"
      
      3. 如果CompressedClassSpace的值设置大于MaxMetaspaceSize则为CompressedClassSpace的值不会超过MaxMetaspaceSize（linux 中是这样，windows好像不会）
      
     [元空间详细资料](http://ju.outofmemory.cn/entry/74869) 