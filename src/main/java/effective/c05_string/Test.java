package effective.c05_string;

import java.util.Date;

/**
 * 避免创建不必要的对象
 */
public class Test {
    public static void main(String[] args) {
        String str1=new String("hello");
        String str2 = new String("hello");
        //输出false,会再堆中创建两个hello
        System.out.println(str1==str2);
        String str3 = "hello";
        String str4 = "hello";
        //输出true，只会创建一个hello
        System.out.println(str3==str4);

        Boolean flag1=new Boolean(true);
        Boolean flag2 = new Boolean(true);
        //输出false,会创建两个对象
        System.out.println(flag1==flag2);
        Boolean flag4=Boolean.valueOf(true);
        Boolean flag5=Boolean.valueOf(true);
        //输出true,只会创建一个对象
        System.out.println(flag4==flag5);

        //起止日期只会创建一次
        Person person=new Person(new Date());
        Person person2=new Person(new Date());

    }
}
