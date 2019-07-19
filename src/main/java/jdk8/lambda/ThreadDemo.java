package jdk8.lambda;

public class ThreadDemo {
    private String name="luobiao";

    public void innerClass() {
        new Thread(new Runnable() {
            private String name="luobiao2";
            @Override
            public void run() {
                System.out.println("innerClass this :"+this);
                System.out.println("innerClass name="+this.name);
            }
        }).start();
    }


    public void lambda() {
        String name="luobiao3";
        new Thread(() -> {
            System.out.println("lambda this :"+this);
            System.out.println("lambda name="+this.name);
        }).start();
    }
}
