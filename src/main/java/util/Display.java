package util;

/**
 * 数组打印工具
 */
public class Display {
    public static void display(String desc,int[] arr) {
        System.out.print(desc+" ");
        display(arr);
    }

    public static void display(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
