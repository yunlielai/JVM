package jvm.zhou_zhi_ming.c07.test1;

/**
 * 被动引用示例
 */
public class Test2 {
    public static void main(String[] args) {
        //不会触发SuperClass的初始化
        SuperClass[] subClasses = new SuperClass[10];
    }
}
