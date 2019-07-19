package arithmetic.heap;

import util.Display;
import util.RandomArr;

/**
 * 大根堆（大顶堆）
 */
public class MyHeap {

    private int[] heapArray;
    private int maxSize;
    private int currentSize;

    public static void main(String[] args) {
        int[] arr=RandomArr.getArr(20);
        MyHeap myHeap = new MyHeap(arr);
        for (int i = 0; i < arr.length; i++) {
            myHeap.trickleUp(arr[i]);
        }
        Display.display("建堆后的的数组为：",arr);
    }
    public MyHeap(int mx) {
        maxSize = mx;
        currentSize = 0;
        heapArray = new int[maxSize];
    }

    public MyHeap(int[] arr) {
        maxSize=arr.length;
        currentSize=arr.length;
        heapArray=arr;
    }

    public boolean isEmpty() {
        return (currentSize == 0)? true : false;
    }

    public boolean isFull() {
        return (currentSize == maxSize)? true : false;
    }

    public boolean insert(int key) {
        if(isFull()) {
            return false;
        }
        heapArray[currentSize] = key;
        trickleUp(currentSize++);
        return true;
    }

    //向上调整
    public void trickleUp(int index) {
        int parent = (index - 1) / 2; //父节点的索引
        int bottom = heapArray[index]; //将新加的尾节点存在bottom中
        while(index > 0 && heapArray[parent] < bottom) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    public int remove() {
        int root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    //向下调整
    public void trickleDown(int index) {
        int top = heapArray[index];
        int largeChildIndex;
        while(index < currentSize/2) { //下标超过元素个数的一半说明已经到树的最底层了
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            //找出最大子节点
            if(rightChildIndex < currentSize &&  //如果右子节点的下标大于元素个数则说明右子节点不存在
                    heapArray[leftChildIndex] < heapArray[rightChildIndex]) {
                largeChildIndex = rightChildIndex;
            }
            else {
                largeChildIndex = leftChildIndex;
            }
            if(top >= heapArray[largeChildIndex]) {
                break;
            }
            heapArray[index] = heapArray[largeChildIndex];
            index = largeChildIndex;
        }
        heapArray[index] = top;
    }

    //根据索引改变堆中某个数据
    public boolean change(int index, int newValue) {
        if(index < 0 || index >= currentSize) {
            return false;
        }
        int oldValue = heapArray[index];
        heapArray[index]=newValue;
        if(oldValue < newValue) {
            trickleUp(index);
        }
        else {
            trickleDown(index);
        }
        return true;
    }

}

