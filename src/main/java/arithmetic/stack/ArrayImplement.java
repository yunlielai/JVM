package arithmetic.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayImplement {
    Object[] elementData;
    int top = -1;
    int size;

    public ArrayImplement() {
        this.size = 15;
        this.elementData = new Object[size];
    }

    public ArrayImplement(int init) {
        if (init > Integer.MAX_VALUE) {
            size = Integer.MAX_VALUE;
        } else if (init < 0) {
            throw new IllegalArgumentException("栈初始容量不能小于0");
        } else {
            size = init;
        }
        this.elementData = new Object[size];
    }

    public boolean isEmpty() {
        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }

    public Object push(Object o) {
        isGrow(top+1);
        elementData[++top] = o;
        return o;
    }

    public Object pop() {
        Object o = peek();
        elementData[top--]=null;
        return o;
    }

    public Object peek() {
        if (top > -1) {
            return elementData[top];
        } else {
            throw new EmptyStackException();
        }
    }
    public boolean isGrow(int minCapacity){
        int oldCapacity = size;
        //如果当前元素压入栈之后总容量大于前面定义的容量，则需要扩容
        if(minCapacity >= oldCapacity){
            //定义扩大之后栈的总容量
            int newCapacity = 0;
            //栈容量扩大两倍(左移一位)看是否超过int类型所表示的最大范围
            if((oldCapacity<<1) - Integer.MAX_VALUE >0){
                newCapacity = Integer.MAX_VALUE;
            }else{
                newCapacity = (oldCapacity<<1);//左移一位，相当于*2
            }
            this.size = newCapacity;
            elementData = Arrays.copyOf(elementData, size);
            return true;
        }else{
            return false;
        }
    }
}
