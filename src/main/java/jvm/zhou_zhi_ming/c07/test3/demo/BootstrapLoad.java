package jvm.zhou_zhi_ming.c07.test3.demo;

public class BootstrapLoad{
    public Class nativeFindClass(String name){
        if (name.equals("bootstrap")) {
            System.out.println("BootstrapLoad 加载成功");
            return new Class("bootstrap".getBytes());
        }
        System.out.println("BootstrapLoad 无法加载");
        return null;
    }
}
