package test.c04_object.c02;

public class Dog extends Animal{
    public Dog(){
        System.out.println("dog init");
    }
    @Override
    public void say() {
        System.out.println("dog");
    }
    {
        System.out.println("dog{}");
    }
    static {
        System.out.println("dog static");
    }


}
