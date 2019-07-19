package design.c02_single;

import java.io.Serializable;

public enum  Single4 implements Serializable,MySingle {
    Single{
        @Override
        public void print(){
            System.out.println("hello world");
        }
    };

    public static void doSomeThing() {
        //todo
    }

    {
        System.out.println("single4 构造函数初始化...");
    }

    public static MySingle getInstance() {
        return Single4.Single;
    }
}
