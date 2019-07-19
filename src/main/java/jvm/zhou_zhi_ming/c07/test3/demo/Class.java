package jvm.zhou_zhi_ming.c07.test3.demo;

public class Class {
    private String desc;


    public Class(byte[] bytes) {
        this.desc=new String(bytes)+"Load类加载器加载";
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String name) {
        this.desc = desc;
    }
}
