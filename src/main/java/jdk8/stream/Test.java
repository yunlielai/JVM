package jdk8.stream;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        String name="luobiao";
        String name2=new String("luobiao").intern();
        String name3="luo"+"biao";
        String name4=new String("luo")+new String("biao");
        System.out.println(name==name2);
        System.out.println(name2==name3);
        System.out.println(name2==name4);
        System.out.println(name4);

    }
}
