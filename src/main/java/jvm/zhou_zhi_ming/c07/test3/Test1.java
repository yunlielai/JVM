package jvm.zhou_zhi_ming.c07.test3;

public class Test1 {

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class myclass=myClassLoader.loadClass("jvm.zhou_zhi_ming.c07.test3.MyClass");
        //注意：此处不能直接将myClass1强转为MyClass，因为myClassObj1的类加载器为MyClassLoader，而MyClass类的类加载器为AppClassLoader
        Object myClassObj1 = myclass.newInstance();
        MyClass myClassObj2=new MyClass();
        System.out.println("myClass1类加载器："+myClassObj1.getClass().getClassLoader());
        System.out.println("myClass2类加载器："+myClassObj2.getClass().getClassLoader());
        System.out.println(myClassObj1 instanceof MyClass);

    }
}
