package jvm.zhou_zhi_ming.c07.test1;
/**
 * 被动引用示例
 */
public class Test3 {
    public static void main(String[] args) {
        //虽然访问了类的静态属性，但并不会触发类的初始化，因为被修饰为static final的常量会在编译时就被存储到了Test3的常量池中
        //通过javap -verbose Test3.class 可以看到常量池中有"HELLO"字符串
        System.out.println(ConstantClass.str);
    }
}
