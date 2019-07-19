package test.c04_object.c02;

public class Animal {
    public Animal(){
        System.out.println("Animal init");
    }
    {
        System.out.println("Animal{}");
    }
    static {
        System.out.println("Animal static");
    }
    public void say() {
        System.out.println("animal");
    }
}
