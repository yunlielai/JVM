package jvm.zhou_zhi_ming.c02.test1;

/**
 * 开启：mark + kclass + 实例数据(object) + padding = 8 + 4 + 4 + 0 = 16
 * 未开：mark + kclass + 实例数据(object) + padding = 8 + 8 + 8 + 0 = 24
 */
public class MyObject2 {
    private Object object=new Object();
}
