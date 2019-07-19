package jvm.zhou_zhi_ming.c02.test2.statck_sof;

/**
 * VM Args：-Xss128k
 * 在栈帧大小固定的情况下（方法中的局部变量等数据），虚拟机栈空间设置得越大，栈深度也越大
 *
 * 异常：
 * java.lang.StackOverflowError
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Error e) {
            System.out.println("栈深度------------"+oom.stackLength);
            e.printStackTrace();
        }
    }
}


