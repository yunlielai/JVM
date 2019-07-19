package thread;

/**
 * ThreadLocal演示
 */
public class DemoThreadLocal {
    public  static ThreadLocal<String> threadLocal=new ThreadLocal<String>(){
/*
        //通过重写initialValue初始化值，如果有通过此方法赋值，则remove被调用后会返回改初始值
        @Override
        protected String initialValue() {
            return "initValue";
        }*/
    };
    public static void main(String[] args) {
        System.out.println(threadLocal.get());
        threadLocal.set("setValue");
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());
    }
}
