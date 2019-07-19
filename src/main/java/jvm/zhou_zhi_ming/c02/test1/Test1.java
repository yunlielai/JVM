package jvm.zhou_zhi_ming.c02.test1;

import util.SystemUtil;

/**
 * 在64位的机器上，默认是开启指针压缩的，对象的指针占用32位即4字节，如果没开启压缩为64位即8字节
 * 压缩：大概内存 2140m (512*4)
 * 未压缩：大概内存 4202m ( 512*8 未压缩测试需添加 -Xms8G -XX:-UseCompressedOops 虚拟机参数)
 */
public class Test1 {
    //1024*1024字节等于1m
    private static int m=1024*1024;
    //将内存占用放大factor倍，提高准确性（因为程序启动本身会消耗少量内存）
    private static int factor=100;

    public static void main(String[] args){
        long use=SystemUtil.getUseMemory(()->{
            Object[] array = new Object[m*factor];
        });
        System.out.println("消耗内存："+use);
        //注意：如果使用dump查看内存占用时指针是未压缩的，所以计算出的数组元素为8字节
        System.out.println("占用字节："+use/factor);
    }
}
