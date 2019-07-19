package design.c02_single;

import java.io.Serializable;

public class Single5 implements Serializable {
    private static Single5 single=new Single5();
    private Single5(){
    }
    static Single5 getInstance(){
        return single;
    }

    private Object readResolve() {
        return single;
    }

    public static void  doSomeThing (){
        //todo
    }
}
