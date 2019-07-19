package effective.c10_clone;

/**
 * 谨慎地覆盖clone
 * 能不重写clone就不要去重写，因为它带来的问题太多了
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student1 s11=new Student1("luobiao",27);
        Student1 s12=s11;
        s11.setAge(28);
        //修改s1的值age后，s2的age也发生了变化
        System.out.println(s12.getAge());
        System.out.println("s11 hashCode="+s11.hashCode()+"    "+"s12 hashCode="+s12.hashCode());

        Student2 s21=new Student2("luobiao",27);
        Student2 s22=s21.clone();
        s21.setAge(28);
        //修改s21的age后，s22的age并不会变化
        System.out.println(s22.getAge());
        System.out.println("s11 hashCode="+s11.hashCode()+"    "+"s12 hashCode="+s12.hashCode());
    }
}
