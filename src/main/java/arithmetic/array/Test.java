package arithmetic.array;

import util.Display;
import util.RandomArr;

public class Test {
    public static void main(String[] args) {
        int[] arr=RandomArr.getArr(10);
        Test test=new Test();
        test.buildHeap(arr);
    }

    private int parent(int i) {
        return (i-1)/2;
    }

    private int left(int i) {
        return 2*i+1;
    }

    private int right(int i) {
        return 2*i+2;
    }

    public void buildHeap(int[] arr) {
        Display.display(arr);
        for (int i = 1; i < arr.length; i++) {
            int t=i;
            while (t != 0 && arr[parent(t)] >= arr[t]) {
                int temp=arr[t];
                arr[t]=arr[parent(t)];
                arr[parent(t)]=temp;
                t=parent(t);
            }
        }
        System.out.println();
        Display.display(arr);
    }

}
