package arithmetic.stack;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        //testStringReversal();
        checkFormat();
    }
    //通过栈进行字符串反转
    public static void testStringReversal(){
        ArrayImplement stack = new ArrayImplement();
        String str = "how are you";
        char[] cha = str.toCharArray();
        for(char c : cha){
            stack.push(c);
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }

    //检测字符串中是否有括号没有关闭
    public static void checkFormat() throws Exception {
        String str="{df(df[dfd])d}";
        Map<String,String> map1=new HashMap();
        map1.put("{","}");
        map1.put("(",")");
        map1.put("[","]");
        Map<String,String> map2=new HashMap<String, String>();
        map2.put(")","(");
        map2.put("}","{");
        map2.put("]","[");
        char[] cs=str.toCharArray();
        ArrayImplement statck=new ArrayImplement();
        for (char c : cs) {
            String key = String.valueOf(c);
            if (map1.containsKey(key)) {
                statck.push(key);
            }
            if (map2.containsKey(key)) {
                String pKey = statck.pop().toString();
                if (!key.equals(map1.get(pKey))) {
                    throw new Exception("格式有误！");
                }
            }
        }
    }
}
