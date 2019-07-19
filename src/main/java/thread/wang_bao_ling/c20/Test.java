package thread.wang_bao_ling.c20;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList迭代时删除元素示例代码
 */
public class Test {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        Iterator<String> iterator=list.iterator();
        while (iterator.hasNext()) {
            String str=iterator.next();
            if (str.equals("three")) {
                System.out.println(str);
                //会抛出UnsupportedOperationException异常
                iterator.remove();
            }
        }

    }
}
