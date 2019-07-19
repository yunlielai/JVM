package test.c03_mianshi;

/**
 * 　　Object src : 原数组
 *     int srcPos : 从原数据的srcPos位置开始填充
 * 　　Object dest : 目标数组
 * 　　int destPos : 从目标数组的destPos位置开始复制
 * 　　int length  : 从原数组复制length个元素到目标数组
 */
public class Test4 {
    static char c;
    public static void main(String[] args) {
        char[] srcArr = new char[10];
        srcArr[0]='a';
        srcArr[1]='b';
        srcArr[2]='c';
        srcArr[3]='d';
        srcArr[4]='e';
        char[] destArr = new char[15];
        System.arraycopy(srcArr,1,destArr,2,3);

        for (int i = 0; i < destArr.length; i++) {
            System.out.println("destArr["+i+"]="+destArr[i]);
        }

    }
}
