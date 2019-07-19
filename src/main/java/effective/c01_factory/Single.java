package effective.c01_factory;

public class Single {
    private static Single single=new Single();
    private Single(){};
    static Single getInstance(){
        return single;
    }
}
