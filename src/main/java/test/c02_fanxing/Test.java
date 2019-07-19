package test.c02_fanxing;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add("three");
        for (String str : list) {
           /* if (str.equals("three")) {
                list.remove("three");
            }*/
            list.add("five");
        }

    }

}
