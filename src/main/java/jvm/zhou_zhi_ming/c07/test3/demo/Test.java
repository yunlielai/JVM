package jvm.zhou_zhi_ming.c07.test3.demo;

/**
 * 模拟类双亲委派实现机制
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoad classLoadr=new MyDefindLoad();
        Class cls=classLoadr.loadClass("extension");
        System.out.println("cls 为 "+cls.getDesc());
    }
}
