package test.c03_mianshi;

public class Test2 {
    public static void main(String[] args) {
        test();
    }

    /**
     * 进入常量的途径：
     * 1.字面量，如test中的one,two,three,four,five,six(注意：如String s="one"+"two"这种只有"onetwo"会进入常量池，因为在编译的时候就加一起了)
     * 2.final类型的String对象引用相加，如s6（注意：final类型的String对象需都保证已在常量池中，如s9就不满足）
     * 3.调用String对象的intern方法（注意：如果对象内容已存在于常量池中会直接返回常量池中的引用，如果没有则会将对象的引用添加到常量池中并返回）
     */
    public static void test(){
        String s1 = "one";
        final String s2 = "two";
        final String s3 = "three";
        String s4 =  new String("four"); //通过堆中s4的value(char value[])属性引用常量池中four字符串对象的value属性
        String s5 ="five"+s1; //有引用类型相加会调用stringBuilder.append()进行追加，除非引用都是final类型，所以s5(fiveone)不会进入常量池
        String s6=s1+s2; // s1为非final类型的引用，所以还是会调用stringBuilder.append()进行追加得到s6(onetwo)不会进入常量池
        String s7=s2+s3; // 两个引用类型都为final，所以s7(twothree)会进入常量池
        final String s8="six"+s4;  //同s5同理，s8(sixfour)不会进入到常量池，虽然s8为final类型
        String s9=s2+s8; // s8不在常量池中，所以s9(twosixfour)不会经入常量池，虽然是两个final类型的引用相加

        System.out.println(s5=="fiveone");//false
        System.out.println(s6=="onetwo");//false
        System.out.println(s7=="twothree");//true
        System.out.println(s9=="twosixfour");//false
    }
}
