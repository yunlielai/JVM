package jvm.zhou_zhi_ming.c07.test2;

public class Test1 {
    static {
       i=1;
       //不能访问
       //System.out.println(i);
    }

    static int i;

    public static void main(String[] args) {
        System.out.println(i);
    }
}
