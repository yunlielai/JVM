package jvm.zhou_zhi_ming.c07.test3.demo;

public class ApplicationLoad extends ClassLoad {
    public ApplicationLoad() {
        //设置应用加载器的父加载器为扩展类加载器
        super(new ExtensionLoad());
    }

    public Class findClass(String name){
        if (name.equals("application")) {
            System.out.println("ApplicationLoad 加载成功");
            //纯粹模拟调用defineClass
            return defineClass(name.getBytes());
        }
        System.out.println("ApplicationLoad 无法加载");
        return null;
    }
}
