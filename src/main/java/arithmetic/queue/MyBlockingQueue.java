package arithmetic.queue;

import java.util.Random;

/**
 * 用堆实现的阻塞优先队列
 */
public class MyBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        new MyBlockingQueue().test();
    }

    public void test() throws InterruptedException {
        final MyBlockingQueue mq = new MyBlockingQueue(3);
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Random random = new Random();
                        int age = random.nextInt(20);
                        mq.offer(new Job(age, String.valueOf(Thread.currentThread().getId())));
                        for (int i = 0; i < mq.array.length; i++) {
                            if (mq.array[i] != null) {
                                System.out.print(mq.array[i]+"   ");
                            }
                        }
                        System.out.println();
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(2500);
                        System.out.println("remove=====" + mq.take());
                        ;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private Job[] array;
    private int size;
    private static final int DEFAULT_SIZE = 10;

    public MyBlockingQueue(int initCapacity) {
        array = initCapacity > 0 && initCapacity < Integer.MAX_VALUE ? new Job[initCapacity] : new Job[DEFAULT_SIZE];
        size = 0;
    }

    public MyBlockingQueue() {
        this(DEFAULT_SIZE);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getCapacity() {
        return array.length;
    }

    public Job take() throws InterruptedException {
        synchronized (array) {
            while (size <= 0) {
                array.wait();
            }
            Job root = array[0];
            array[0] = array[--size];
            trickleDown(0);
            array.notifyAll();
            return root;
        }
    }

    public boolean offer(Job e) throws InterruptedException {
        synchronized (array) {
            while (size >= array.length) {
                array.wait();
            }
            array[size] = e;
            trickleUp(size++);
            array.notifyAll();
            return true;
        }
    }

    //向上调整
    private void trickleUp(int index) {
        int parent = (index - 1) / 2; //父节点的索引
        Job bottom = array[index]; //将新加的尾节点存在bottom中
        while (index > 0 && array[parent].age < bottom.age) {
            array[index] = array[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        array[index] = bottom;
    }

    //向下调整
    public void trickleDown(int index) {
        Job top = array[index];
        int largeChildIndex;
        while (index < size / 2) { //下标超过元素个数的一半说明已经到树的最底层了
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            //找出最大子节点
            if (rightChildIndex < size &&  //如果右子节点的下标大于元素个数则说明右子节点不存在
                    array[leftChildIndex].age < array[rightChildIndex].age) {
                largeChildIndex = rightChildIndex;
            } else {
                largeChildIndex = leftChildIndex;
            }
            if (top.age >= array[largeChildIndex].age) {
                break;
            }
            array[index] = array[largeChildIndex];
            index = largeChildIndex;
        }
        array[index] = top;
    }

    public class Job implements Runnable{
        int age;
        String name;

        public Job(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "age=" + age +
                    '}';
        }

        @Override
        public void run() {
            //todo
        }
    }

}
