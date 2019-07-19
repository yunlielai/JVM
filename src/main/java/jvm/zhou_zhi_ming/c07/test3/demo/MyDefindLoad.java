package jvm.zhou_zhi_ming.c07.test3.demo;

public class MyDefindLoad extends ClassLoad{

    public Class findClass(String name){
        if (name.equals("myDefind")) {
            System.out.println("ExtensionLoad 加载成功");
            //纯粹模拟调用defineClass
            return defineClass(name.getBytes());
        }
        System.out.println("ExtensionLoad 无法加载");
        return null;
    }
}
