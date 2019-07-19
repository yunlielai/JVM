package jvm.zhou_zhi_ming.c02.test2.direct_oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM Args:-Xmx20M -XX:MaxDirectMemorySize=10M
 * 异常：
 * Exception in thread "main" java.lang.OutOfMemoryError
 */
public class DirectMemoryOOM {
    private static final int mb=1024*1024;
    private  long memoryId;
    Unsafe unsafe;

    public static void main(String[]args)throws Exception{
        while(true){
            DirectMemoryOOM directMemoryOOM=new DirectMemoryOOM();
        }
    }

    public DirectMemoryOOM() throws IllegalAccessException {
       Field unsafeField=Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        unsafe=(Unsafe)unsafeField.get(null);
        memoryId=unsafe.allocateMemory(mb);
    }

/*
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("free id="+memoryId);
        //unsafe.freeMemory(memoryId);
    }*/
}
