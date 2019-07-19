package jvm.zhou_zhi_ming.c06;

import java.io.IOException;
import java.io.InputStream;

public class PrintClassHex {
    public static void main(String[] args) throws IOException {
        new PrintClassHex().print();
    }
    public void print() throws IOException {
        InputStream in=this.getClass().getResourceAsStream("TestClass.class");
        int i;
        int count=1;
        try {
            while ((i = in.read()) > -1) {
                System.out.print(Integer.toHexString(i)+"\t");
                if (count % 16 == 0) {
                    System.out.println();
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            in.close();
        }
        System.out.println();
    }
}
