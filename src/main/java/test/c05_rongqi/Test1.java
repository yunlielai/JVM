package test.c05_rongqi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("threee");
        list.add("one");
        list.add("tow");
        //使用这种方式迭代list中的元素如果以list.remove删除元素且被删除的是最后一个元素会抛出ConcurrentModificationException
        //这种遍历方式需要使用iterator.remove的方式进行删除
        remove1(list);
        //  remove2(list);
    }


    public static void remove1(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.equals("tow")) {
                System.out.println(str);
                list.remove(str);
                //该中迭代方式正确的删除元素方式
                //iterator.remove();
            }
        }
    }

    public static void remove2(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (str.equals("tow")) {
                System.out.println(str);
                list.remove(str);
            }

        }
    }
}
