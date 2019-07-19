package arithmetic;

import arithmetic.heap.MyHeap;
import util.CglibProxy;
import util.RandomArr;

import java.util.ArrayList;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        int[] arr=RandomArr.getArr(1000000);
        Sort sort=CglibProxy.getProxyObj(Sort.class);
        //sort.chaiRu(arr);
        //sort.xuanZe(arr);
        sort.maoPao(arr);
        //sort.guiBin(arr);
        //sort.kuaiSu(arr);
        //sort.jiShu(arr);
       // sort.dui(arr);
    }

    //冒泡排序
    public  void maoPao(int[] arr){
        //display("初始值",arr);
        for (int i =1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            //System.out.println();
            //display("第 "+i+"轮",arr);
        }
    }

    //选择排序
    public  void xuanZe(int[] array) {
        //display("初始值",array);
        for (int i = 0; i < array.length - 1; i++) {
            int min=i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min=j;
                }
            }
            if (min != i) {
                int temp=array[min];
                array[min]=array[i];
                array[i]=temp;
            }
            //System.out.println();
            //display("第 "+i+"轮",array);
        }
    }

    //插入排序
    public  void chaiRu(int[] arr) {
        //display("初始值",arr);
        for (int i = 1; i < arr.length; i++) {
            int j=i;
            int temp=arr[i];
            while (j > 0 && temp < arr[j-1]) {
                arr[j]=arr[j-1];
                j--;
            }
            arr[j]=temp;
            //System.out.println();
            //display("第 "+i+"轮",arr);
        }
    }

    //基数排序
    public  void jiShu(int[] array){
        int max = array[0];
        for(int i=0;i<array.length;i++){  //找到数组中的最大值
            if(array[i]>max){
                max = array[i];
            }
        }
        int keysNum = 0;  //关键字的个数，我们使用个位、十位、百位...当做关键字，所以关键字的个数就是最大值的位数
        while(max>0){
            max /= 10;
            keysNum++;
        }
        List<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<10;i++){  //每位可能的数字为0~9，所以设置10个桶
            buckets.add(new ArrayList<Integer>());  //桶由ArrayList<Integer>构成
        }
        for(int i=0;i<keysNum;i++){  //由最次关键字开始，依次按照关键字进行分配
            for(int j=0;j<array.length;j++){  //扫描所有数组元素，将元素分配到对应的桶中
                //取出该元素对应第i+1位上的数字，比如258，现在要取出十位上的数字，258%100=58,58/10=5
                int key =array[j]%(int)Math.pow(10, i+1)/(int)Math.pow(10, i);
                buckets.get(key).add(array[j]);  //将该元素放入关键字为key的桶中
            }
            //分配完之后，将桶中的元素依次复制回数组
            int counter = 0;  //元素计数器
            for(int j=0;j<10;j++){
                ArrayList<Integer> bucket =buckets.get(j);  //关键字为j的桶
                while(bucket.size()>0){
                    array[counter++] = bucket.remove(0);  //将桶中的第一个元素复制到数组，并移除
                }
            }
        }
    }

    //堆排序
    public void dui(int[] arr){
        MyHeap myHeap=new MyHeap(arr);
        for (int i = 1; i < arr.length; i++) {
            myHeap.trickleUp(i);
        }
        int len=arr.length-1;
        while (!myHeap.isEmpty()) {
            int i=myHeap.remove();
            arr[len--]=i;
        }
    }


    /**
     * 归并排序
     * 时间复杂度为O(N* log2N)
     * 递归分解数组时栈不容易溢出
     */
    public  void guiBin(int[] arr) {
        GuiBin guiBin = new GuiBin(arr);
        guiBin.mergeSort();
    }

    /**
     * 快速排序
     * 时间复杂度为O(N*log2N)
     * 当数据是有序的时候时间复杂度为O(N),此时容易递归分解时容易栈溢出,会使得虚拟机栈中一次性入栈N个方法
     */
    public void kuaiSu(int[] arr) {
        KuaiSu kuaiSu=new KuaiSu(arr);
        kuaiSu.quikSort();
    }

    public class GuiBin {
        private int [] array;  //待排序的数组

        public GuiBin(int [] array){
            this.array= array;
        }
        //归并排序
        public void mergeSort(){
            int[] workSpace = new int [array.length]; //用于辅助排序的数组
            recursiveMergeSort(workSpace,0,workSpace.length-1);
        }

        /**
         * 递归的归并排序
         * @param workSpace  辅助排序的数组
         * @param lowerBound 欲归并数组段的最小下标
         * @param upperBound 欲归并数组段的最大下标
         */
        private void recursiveMergeSort(int [] workSpace,int lowerBound,int upperBound){
            if(lowerBound== upperBound){  //该段只有一个元素，不用排序
                return;
            }else{
                int mid = (lowerBound+upperBound)/2;
                recursiveMergeSort(workSpace,lowerBound,mid);    //对低位段归并排序
                recursiveMergeSort(workSpace,mid+1,upperBound);  //对高位段归并排序
                merge(workSpace,lowerBound,mid,upperBound);
            }
        }

        /**
         * 对数组array中的两段进行合并，lowerBound~mid为低位段，mid+1~upperBound为高位段
         * @param workSpace 辅助归并的数组，容纳归并后的元素
         * @param lowerBound 合并段的起始下标
         * @param mid 合并段的中点下标
         * @param upperBound 合并段的结束下标
         */
        private void merge(int [] workSpace,int lowerBound,int mid,int upperBound){
            int lowBegin = lowerBound;  //低位段的起始下标
            int lowEnd = mid;           //低位段的结束下标
            int highBegin = mid+1;  //高位段的起始下标
            int highEnd = upperBound;  //高位段的结束下标
            int j = 0; //workSpace的下标指针
            int n = upperBound-lowerBound+1;  //归并的元素总数

            while(lowBegin<=lowEnd && highBegin<=highEnd){
                if(array[lowBegin]<array[highBegin]){//将两者较小的那个放到workSpace中
                    workSpace[j++]= array[lowBegin++];
                }else{
                    workSpace[j++]= array[highBegin++];
                }
            }
            while(lowBegin<=lowEnd){
                workSpace[j++]= array[lowBegin++];
            }
            while(highBegin<=highEnd){
                workSpace[j++]= array[highBegin++];
            }
            for(j=0;j<n;j++){  //将归并好的元素复制到array中
                array[lowerBound++]= workSpace[j];
            }

        }


    }

    public class KuaiSu{
        private int[] array;
        public KuaiSu(int[] array) {
            this.array=array;
        }

        public void quikSort(){
            recursiveQuikSort(0,array.length-1);
        }

        /**
         * 递归的快速排序
         *@param low  数组的最小下标
         *@param high  数组的最大下标
         */
        private void recursiveQuikSort(int low,int high){
            if(low>=high){
                return;
            }else{
                int pivot = array[low];  //以第一个元素为基准
                int partition =partition(low,high,pivot);  //对数组进行划分，比pivot小的元素在低位段，比pivot大的元素在高位段
                recursiveQuikSort(low,partition-1);  //对划分后的低位段进行快速排序
                recursiveQuikSort(partition+1,high);  //对划分后的高位段进行快速排序
            }
        }

        /**
         * 以pivot为基准对下标low到high的数组进行划分
         *@param low 数组段的最小下标
         *@param high 数组段的最大下标
         *@param pivot 划分的基准元素
         *@return 划分完成后基准元素所在位置的下标
         */
        private int partition(int low,int high,int pivot){
            while(low<high){
                while(low<high &&array[high]>=pivot){  //从右端开始扫描，定位到第一个比pivot小的元素
                    high--;
                }
                swap(low,high);
                while(low<high &&array[low]<=pivot){  //从左端开始扫描，定位到第一个比pivot大的元素
                    low++;
                }
                swap(low,high);
            }
            return low;

        }
        /**
         * 交换数组中两个元素的数据
         *@param low 欲交换元素的低位下标
         *@param high 欲交换元素的高位下标
         */
        private void swap(int low,int high){
            int temp = array[high];
            array[high] = array[low];
            array[low] = temp;
        }

    }
}
