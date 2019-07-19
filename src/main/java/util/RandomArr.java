package util;

import java.util.Random;

/**
 * 生成随机数组的工具类
 */
public class RandomArr {
    public static int[] getArr(int size){
        int[] arr=new int[size];
        Random random = new Random();
        for (int i=0;i<arr.length;i++){
            arr[i]=random.nextInt(size)+1;
        }
        return arr;
    }
}
