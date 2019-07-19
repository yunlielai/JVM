package arithmetic.queue;

/**
 * 数组实现的循环队列
 */
public class MyQueueByArray {
    private Object[] queArray;
    //队列总大小
    private int maxSize;
    //前端
    private int front;
    //后端
    private int rear;
    //队列中元素的实际数目
    private int nItems;

    public MyQueueByArray(int s){
        maxSize = s;
        queArray = new Object[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    //队列中新增数据
    public void insert(int value){
        if(isFull()){
            System.out.println("队列已满！！！");
        }else{
            //如果队列尾部指向顶了，那么循环回来，执行队列的第一个元素
            if(rear == maxSize -1){
                rear = -1;
            }
            //队尾指针加1，然后在队尾指针处插入新的数据
            queArray[++rear] = value;
            nItems++;
        }
    }

    //移除数据
    public Object remove(){
        Object removeValue = null ;
        if(!isEmpty()){
            removeValue = queArray[front];
            queArray[front] = null;
            front++;
            if(front == maxSize){
                front = 0;
            }
            nItems--;
            return removeValue;
        }
        return removeValue;
    }

    //查看对头数据
    public Object peekFront(){
        return queArray[front];
    }


    //判断队列是否满了
    public boolean isFull(){
        return (nItems == maxSize);
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return (nItems ==0);
    }

    //返回队列的大小
    public int getSize(){
        return nItems;
    }

}
