package jvm.zhou_zhi_ming.c02.test1;

/**
 * 父类
 * 有一个long型的属性占8B会被子类继承（开启指针压缩不会压缩基础类型）
 */
public class MyObject {
    private long l;
}
