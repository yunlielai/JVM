package test.c02_fanxing;

import java.util.ArrayList;
import java.util.List;

public class Company<T> {
    public <V extends Number> List<V> employeeList(V v){
        List<V> list = new ArrayList<>();
        list.add(v);
        return list;
     }
}
