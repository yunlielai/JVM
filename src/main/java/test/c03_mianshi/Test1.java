package test.c03_mianshi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询字符串中出现频率最高的字符
 */
public class Test1 {
    public static void main(String[] args) {
        Test1 test1 =new Test1();
        test1.findMaxKeyDemo();
    }

    /**
     * 查询字符串中出现频率最高的字符
     */
    public  void findMaxKeyDemo(){
        String str="luo biao c01_factory c02_builder demo luo biao word biao c02_builder c01_factory c01_factory c01_factory";
        String[] arr=str.split(" ");
        Map<String,Counter> map=new HashMap<String, Counter>();
        for (String s : arr) {
            int count=1;
            if (map.containsKey(s)) {
                count=map.get(s).count+1;
            }
            Counter counter=new Counter(s,count);
            map.put(s,counter);
        }
        Counter[] counters=map.values().toArray(new Counter[0]);
        Arrays.sort(counters);
        System.out.println(counters[0]);
    }

    class Counter implements Comparable<Counter>{
        private String word;
        private int count;

        public Counter(String word, int count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(Counter o) {
            return o.count-count;
        }

        @Override
        public String toString() {
            return "Counter{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }

}
