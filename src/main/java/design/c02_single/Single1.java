package design.c02_single;

import java.io.Serializable;

public class Single1 implements Serializable {
    private static Single1 single = new Single1();

    private Single1() {
        System.out.println("single1 构造函数初始化...");
    }

    static Single1 getInstance() {
        return single;
    }

    public static void doSomeThing(){
        //todo
    }

}
