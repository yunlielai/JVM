package thread.wang_bao_ling.c02;

/**
 * 注意：此处关注的是jvm最终实现的内存模型规范，不关注底层实现（如：volatile强制所修饰的变量及它前边的变量刷新至内存，并且volatile禁止了指令的重排序）
 * 1.“x=42” Happens-Before 写变量 “v=true” ，这是规则1 的内容；
 * 2. 写变量“v=true” Happens-Before 读变量 “v==true”，这是规则2 的内容 。
 * 3. 根据规则 3 传递性规则，我们得到结果：“x=42” Happens-Before 读变量“v==true”
 * 这意味着如果b线程读到了v==true则x就肯定为42
 */
public class HappenBefore123 {
    int x = 0;
    volatile boolean v = false;
    public void writer() {
        x = 42;
        v = true;
    }
    public void reader() {
        if (v == true) {
            // 这里x会是多少呢？
            System.out.println(x);
        }
    }

    public void demo(){
        Thread a=new Thread(()->{
            writer();
        });
        Thread b=new Thread(()->{
            reader();
        });
       a.start();
       b.start();
    }
}
