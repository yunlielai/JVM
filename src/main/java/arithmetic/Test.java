package arithmetic;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Integer[] arr = {};
        List list = new ArrayList();
        list.add("c01_factory");
        list.add("c02_builder");
        list.add("three");
        list.add("c02_builder");
        list.remove("c02_builder");
        for (Object s : list) {
            System.out.println(s);
        }
    }
}
