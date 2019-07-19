### java visualVM远程监控服务器java进程

#### JMX方式（jconsole也可通过此方式进行连接）

   jmx方式能监控到CPU信息，但无法使用visualVM的visualVM GC插件
   
   - jmx无密码方式
    
     监控普通的java进程
       
         1. 设置hostname
             在/etc/hosts中添加hostname解析，解析地址可以不是外网ip（可通过hostname命令查看hostname是多少）
         2. 启动时添加参数
             如：
                 java -Djava.rmi.server.hostname=xxx.xxx.xxx -Dcom.sun.management.jmxremote.port=22334 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false Hello
                 其中xxx.xxx.xxx为本机外网ip,22334为提供jmx访问的端口，可自己指定
                 如果没有配置hostname解析可能会出现如下异常：
                     Error: Exception thrown by the agent : java.net.MalformedURLException: Local host name unknown: java.net.UnknownHostException:
         3. 在防火墙中开放端口
             使用命令：
                     lsof -i | grep java | grep “启动的java进程pid”(可通过jps查看)
                 将查看上一步查看到的tcp监听端口全部在防火墙中进行开放，注意关闭该java进程后重新启动需要重新配置开发的端口，因为监听的端口会发生变化
         4. 打开visualVm或jconsole输入ip（2中配置的xxx.xxx.xxx）和端口（22334）进行连接即可查看监控，无需输入用户名和密码

      监控普通的tomcat
   
         1. 下载catalina-jmx-remote.jar，放在tomcat/lib目录下面
             地址示例：http://mirror.bit.edu.cn/apache/tomcat/tomcat-8/v8.5.35/bin/extras/
           请根据自己的tomcat版本选择对应的包
         2. 配置conf/server.xml
             在server.xml中添加如下监听配置
             <Listener className="org.apache.catalina.mbeans.JmxRemoteLifecycleListener"  rmiRegistryPortPlatform="22334" rmiServerPortPlatform="22334" />
             有了该可使得每次重启tomat不需要重新在防火墙中开放端口，因为会使用配置的22334端口
         3. 修改bin/catalina.sh
             添加配置：
             CATALINA_OPTS="${CATALINA_OPTS} -Djava.rmi.server.hostname=服务器的外网ip地址"                                                                                                                                                                                                      
             CATALINA_OPTS="${CATALINA_OPTS} -Dcom.sun.management.jmxremote=true"
             CATALINA_OPTS="${CATALINA_OPTS} -Dcom.sun.management.jmxremote.ssl=false"
             CATALINA_OPTS="${CATALINA_OPTS} -Dcom.sun.management.jmxremote.authenticate=false"
         3. 在防火墙中开放端口
             在防火墙中开发22334端口
         4. 打开visualVm或jconsole输入ip（3中配置的服务器的外网ip地址）和端口（22334）进行连接即可查看监控，无需输入用户名和密码
   
   - 有密码方式
   
         1. 添加账号
             进入/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.91.x86_64/jre/lib/management/目录（可通过jinfo  pid|grep java.home的查看自己的java.home）
             执行命令：
                 mv jmxremote.password.template jmxremote.password
                 chmod 600 jmxremote.password
                 vim jmxremote.password
                 将被注释的monitorRole，和controlRole放开，并修改密码123456
                     monitorRole  123456
                     controlRole  123456
         2.修改之前的启动配置
             修改配置：
                     -Dcom.sun.management.jmxremote.authenticate=true
                 添加配置：
                     -Dcom.sun.management.jmxremote.password.file=/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.91.x86_64/jre/lib/management/jmxremote.password
                     -Dcom.sun.management.jmxremote.access.file=/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.91.x86_64/jre/lib/management/jmxremote.access
         3. 启动
             打开visualVm或jconsole输入ip+端口，然后输入之前设置的账号controlRole 和密码 123456即可连上
    
    
#### jstatd方式    

jstatd方式能使用visualVM的visualVM GC插件，但无法监控到CPU信息

    1、开启权限
        在/usr/lib/jvm/java-1.7.0-openjdk-1.7.0.91.x86_64/jre/lib/security/java.policy中添加
            permission java.security.AllPermission;
    2、启动
        rmiregistry 2020&jstatd -J-Djava.security.policy=all.policy -J-Djava.rmi.server.logCalls=true -J-Djava.rmi.server.hostname=服务器外网ip -p 1099
    3、开放端口
        通过netstat -ntlp|grep jstatd查看监听的端口（注意有两个监听端口），在防火墙中进行开放这些端口
        如果出现：
            java.rmi.NotBoundException: JStatRemoteHost
        一般等一段时间就恢复正常了
    4、打开visualVm添加远程主机会自动加载远程java进程