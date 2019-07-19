package effective.c01_factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 考虑用静态工厂方法代替构造器
 *
 * 缺点：
 * 1.一是公有的静态方法所返回的非公有类不能被实例化，也就是说Collections.synchronizedList返回的SynchronizedList类不能被实例化
 * 2.二是查找API比较麻烦，它们不像普通的类有构造器在API中标识出来，而是和其他普通静态方法一样
 */
public class Test {
    public static void main(String[] args) {
        //1.可以随意修改方法名,当不止一个构造方法时，可以通过方法名描述每种实例化方式的特点
        People people=People.getInstance();
        //2.不用重复创建一个对象,即单例模式的应用
        Single single=Single.getInstance();
        //3.可以返回原返回类型的任何子类型的
        List list1 = Collections.synchronizedList(new ArrayList());
        List list2=Collections.synchronizedList(new LinkedList());
    }
}
