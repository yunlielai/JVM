package jvm.zhou_zhi_ming.c02.test1;

/**
 * 会从父类中继承一个long实例变量，占8B
 * 开启：mark + kclass + 实例数据(继承父类long) + padding = 8 + 4 + 8 + 4 = 24
 * 未开：mark + kclass + 实例数据(继承父类long) + padding = 8 + 8 + 8 + 0 = 24
 */
public class MyObject3 extends MyObject{

}
