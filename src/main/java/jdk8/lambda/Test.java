package jdk8.lambda;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 函数式接口是只包含一个抽象方法声明的接口,java.lang.Runnable 就是一种函数式接口,每个 Lambda 表达式都能隐式地赋值给函数式接口
 * 如：Runnable r = () -> System.out.println("hello world");
 */
public class Test {
    public static void main(String[] args) {
        //自定义函数接口示例
        functionDemo();

        //lambda表达式list遍历
        //listDemo();

        //匿名内部类和lambda表达式this引用区别
        //thisDemo();

        //方法引用示例
        //refrenceDemo();

        //函数式接口Predicate示例
       // predicateDemo();

        //Map和Reduce示例
        //mapReduceDemo();

    }

    public static void functionDemo() {
        MyFunction my = (name) -> {
            System.out.println("hello: " + name);
        };
        my.run("luobiao");
    }


    public static void thisDemo() {
        ThreadDemo threadDemo = new ThreadDemo();
        //内部类方式创建线程，内部类中this指向匿名类
        threadDemo.innerClass();
        //lambda表达式的方式创建线程，lambda表达式中指向当前类
        threadDemo.lambda();
    }

    public static void refrenceDemo() {
        //静态方法和实例方法的引用
        MethodRefrenceDemo rDemo = new MethodRefrenceDemo();
        IntUnaryOperator i1 = MethodRefrenceDemo::staticMethod;
        IntUnaryOperator i2 = rDemo::normalMethod;
        System.out.println(i1.applyAsInt(1));
        System.out.println(i2.applyAsInt(1));

        BiFunction<MethodRefrenceDemo, Integer, Integer> i3 = MethodRefrenceDemo::normalMethod;
        System.out.println(i3.apply(rDemo, 3));
    }

    public static void listDemo() {
        //Old way:
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        for (Integer n : list) {
            System.out.println(n);
        }

        //New way:
        list.forEach(n -> System.out.println(n));

        //or we can use :: double colon operator in Java 8
        list.forEach(System.out::println);
    }

    public static void predicateDemo() {
        List<String> nameList = Arrays.asList("zlisi", "zhangsan", "wanwu");
        //以z开头
        Predicate<String> starWith=str -> str.startsWith("z");
        //长度等于5
        Predicate<String> minLen=str -> str.length()==5;
        predicateFilter(nameList,starWith,minLen);
    }

    public static void predicateFilter(List<String> nameList, Predicate<String> starWith,Predicate<String> minLen) {
        //同时满足starWith和minLen
        nameList.stream().filter(starWith.and(minLen)).forEach(name -> System.out.println(name));
    }

    public static void mapReduceDemo(){
       List<Integer> list=Arrays.asList(100,200,300,400);
       //对各个值进行加10%，然后计算平均值
       Double sum=list.stream().map(
               m -> m+m*0.1
       ).reduce(
               //s为上一次的返回值，i为当前值
               (s,i) -> s+i
       ).get();
        System.out.println(sum);

        //演示通过map处理后的数据再通过collect方法进行收集
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);

        //通过summaryStatistics（摘要统计计算）获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

}
