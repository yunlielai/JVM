package jvm.zhou_zhi_ming.c02.test2.meta_oom;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-Xms20m -Xmx20m
 * 调小堆内存，会发现抛出了的异常信息为
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 而非
 * Exception in thread "main" java.lang.OutOfMemoryError: PermGen space
 */
public class Test1 {
    public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();
        // 10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
