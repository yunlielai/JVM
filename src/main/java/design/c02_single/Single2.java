package design.c02_single;

import java.io.Serializable;

public class Single2 implements Serializable {
    public static class SingletonHolder {
        private static final Single2 INSTANCE = new Single2();
    }

    private Single2() {
        System.out.println("single2 构造函数初始化...");
    }

    public static final Single2 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void  doSomeThing (){
        //todo
    }
}
