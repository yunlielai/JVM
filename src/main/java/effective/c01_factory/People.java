package effective.c01_factory;

public class People {
    private String name;
    static People getInstance(){
        return new People();
    }
}
