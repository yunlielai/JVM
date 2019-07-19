package arithmetic.stack;

public class MyStack {
    private int top;
    private int size;
    private Object[] element;

    public MyStack(int size) {
        this.size=size;
        top=-1;
        element=new Object[size];
    }

    public boolean isEmpty() {
        if (top < 0) {
            return true;
        }else{
            return false;
        }
    }

    public boolean push(Object o) {
        if (size==element.length) {
            throw new StackOverflowError();
        }else{
            element[top++]=o;
            return true;
        }
    }

    public Object pop() {
        if (isEmpty()) {
            throw new NullPointerException("栈为空！");
        }else{
            Object ret=top;
            element[top--]=null;
            size--;
            return ret;
        }
    }

    public int getSize() {
        return size;
    }
}
