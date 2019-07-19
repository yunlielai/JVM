package jvm.zhou_zhi_ming.c02.test2.heap_oom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-Xms10g -Xmx10g
 */
public class Test2 {
    private static List list = new ArrayList();
    public static void main(String[] args) throws InterruptedException, IOException {
        Thread.sleep(1000);
        System.out.println("开始添加");
        byte[] byteSize = new byte[50];
        System.out.print("输入大小:");
        int temp = System.in.read(byteSize);
        String sizeStr = new String(byteSize, 0, temp);
        int size = Integer.parseInt(sizeStr.trim());
        while (size-- > 0) {
            System.out.println("size="+size);
            list.add(new byte[1024*1024*1024]);
        }
        System.out.println("结束添加");
    }
}
