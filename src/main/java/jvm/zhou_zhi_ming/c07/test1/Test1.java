package jvm.zhou_zhi_ming.c07.test1;

/**
 * 被动引用示例
 */
public class Test1 {
    public static void main(String[] args) {
        //只会触发父类SuperClass的初始化
        int i=SubClass.value;
    }
}
