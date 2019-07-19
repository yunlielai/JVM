package test.c04_object.c02;

/**
 输出：
 Animal static
 dog static
 Animal{}
 Animal init
 dog{}
 dog init
 因为类的构造方法中会先调用父类构造，然后执行实例代码块中收集的代码，最后执行构造方法中写的代码
 */
public class Test1 {
    public static void main(String[] args) {
        new Dog();
    }
}
