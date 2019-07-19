package jvm.zhou_zhi_ming.c07.test3.demo;

public class ExtensionLoad extends ClassLoad {
    public ExtensionLoad() {
        super(null);
    }
    public Class findClass(String name){
        if (name.equals("extension")) {
            System.out.println("ExtensionLoad 加载成功");
            //纯粹模拟调用defineClass
            return defineClass(name.getBytes());
        }
        System.out.println("ExtensionLoad 无法加载");
        return null;
    }
}
