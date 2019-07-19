package effective.c02_builder;

/**
 * 遇到多个构造器参数时要考虑用构建器
 */
public class Test {
    public static void main(String[] args) {
        //旧的实现1,不能灵活的自主组合传参，必须要有对应的构造方法，而且参数一多每个参数都不知道什么意思
        Student1 oldStudent1=new Student1("Kevin", "男", 0, "1年级");
        //旧的实现2,这导致了在构造过程中JavaBean可能处于不一致的状态，也就是说实例化对象本该是一气呵成，但现在却分割成了两大步，这可能会导致它线程不安全
        Student2 oldStudent2=new Student2("luobiao",27);
        oldStudent2.setSex("man");
        //推荐使用，可以自主选择需要设置哪些参数，而且在实例化student之前，所有的属性都已设置完毕
        Student3 student=new Student3.Builder("luobiao",27).grade("man").build();
    }
}
