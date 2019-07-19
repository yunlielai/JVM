package jdk8.stream;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 操作类型
 * 操作类型概念要理清楚。有几个维度。
 * 首先分为 中间操作 和 最终操作，在最终操作没有调用的情况下，所有的中级操作都不会执行。那么那些是中间操作那些是最终操作呢？ 简单来说，返回stream流的就是中间操作，可以继续链式调用下去，不是返回stream的就是最终操作。这点很好理解。
 * 最终操作里面分为短路操作和非短路操作，短路操作就是limit/findxxx/xxxMatch这种，就是找了符合条件的就终止，其他的就是非短路操作。在无限流里面需要调用短路操作，否则像炫迈口香糖一样根本停不下来！
 * 中间操作又分为 有状态操作 和 无状态操作，怎么样区分呢？ 一开始很多同学需要死记硬背，其实你主要掌握了状态这个关键字就不需要死记硬背。状态就是和其他数据有关系。我们可以看方法的参数，如果是一个参数的，就是无状态操作，因为只和自己有关，其他的就是有状态操作。
 * 如map/filter方法，只有一个参数就是自己，就是无状态操作；而distinct/sorted就是有状态操作，因为去重和排序都需要和其他数据比较，理解了这点，就不需要死记硬背了！
 * 为什么要知道有状态和无状态操作呢？在多个操作的时候，我们需要把无状态操作写在一起，有状态操作放到最后，这样效率会更加高。
 */
public class LazyDemo {

    public static void main(String[] args) {
        int[] nums={1,2,3};
        int sum2=IntStream.of(nums).map(LazyDemo::doubleNum).sum();
        System.out.println("sum2="+sum2);

        //惰性求值就是终止没有调用的情况下，中间操作不会执行
        System.out.println(IntStream.of(nums).map(LazyDemo::doubleNum));

        runStream();
    }

    public static int doubleNum(int i) {
        System.out.println("执行了乘2");
        return i*2;
    }


    public static void runStream(){
        Random random = new Random();
        // 随机产生数据
        Stream<Integer> stream = Stream.generate(() -> random.nextInt())
                // 产生500个 ( 无限流需要短路操作. )
                .limit(500)
                // 第1个无状态操作
                .peek(s -> print("peek: " + s))
                // 第2个无状态操作
                .filter(s -> {
                    print("filter: " + s);
                    return s > 1000000;
                })
                // 有状态操作
                .sorted((i1, i2) -> {
                    print("排序: " + i1 + ", " + i2);
                    return i1.compareTo(i2);
                })
                // 又一个无状态操作
                .peek(s -> {
                    print("peek2: " + s);
                }).parallel();

        // 终止操作
        stream.count();
    }

    public static void print(String str)   {
        System.out.println(Thread.currentThread().getName()+">"+str);
        try {
            TimeUnit.MILLISECONDS.sleep(5);
        } catch (InterruptedException e) {

        }
    }
}
