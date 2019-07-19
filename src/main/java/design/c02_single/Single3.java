package design.c02_single;

import java.io.Serializable;

public class Single3 implements Serializable {
    static volatile Single3 instance;
    private Single3(){
        System.out.println("single3 构造函数初始化...");
    }
    static Single3 getInstance(){
        if (instance == null) {
            synchronized(Single3.class) {
                if (instance == null)
                    instance = new Single3();
            }
        }
        return instance;
    }


    public static void doSomeThing() {
        //todo
    }
}
