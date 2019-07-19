package arithmetic.array;

public class DiGui {
    public static void main(String[] args){
        System.out.println(test1(5));
        int[] arr={1,3,5,8,9,10,11};
        System.out.println(test2(arr,10,0,arr.length-1));
    }

    //非递归的二分查找（数组是有序的）
    public static int findTwoPoint(int[] array,int key){
        int start = 0;
        int last = array.length-1;
        while(start <= last){
            int mid = (last-start)/2+start;//防止直接相加造成int范围溢出
            if(key == array[mid]){//查找值等于当前值，返回数组下标
                return mid;
            }
            if(key > array[mid]){//查找值比当前值大
                start = mid+1;
            }
            if(key < array[mid]){//查找值比当前值小
                last = mid-1;
            }
        }
        return -1;
    }

    //用递归的二分查找（数组是有序的）
    public static int test2(int[] arr,int key,int low,int height) {
        int mid=(height-low)/2+low;
        if (arr[mid] == key) {
            return mid;
        }
        if (low > height) {
            return -1;
        }
        if (key > arr[mid]) {
            return test2(arr,key,mid,height);
        }else{
            return test2(arr,key,low,mid);
        }
    }

    //使用递归解决汉诺塔问题
    public static void move(int dish,String from,String temp,String to){
        if(dish == 1){
            System.out.println("将盘子"+dish+"从塔座"+from+"移动到目标塔座"+to);
        }else{
            move(dish-1,from,to,temp);//A为初始塔座，B为目标塔座，C为中介塔座
            System.out.println("将盘子"+dish+"从塔座"+from+"移动到目标塔座"+to);
            move(dish-1,temp,from,to);//B为初始塔座，C为目标塔座，A为中介塔座
        }
    }
    //用递归计算阶乘
    public static int test1(int i) {
        if (i == 0) {
            return 1;
        }
        return test1(i-1)*i;
    }
}
