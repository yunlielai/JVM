public class Test {
    private static Object object = new Object();

    public static void main(String[] name) {
       Test.test();
    }

    public static void test(){
        int i=0;
        synchronized (object) {
           i++;
        }
    }


}
