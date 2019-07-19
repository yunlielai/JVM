package effective.c02_builder;

public class Student1 {
    /*必填*/
    private String name;
    private int age;
    /*选填*/
    private String sex;
    private String grade;
    public Student1(String name, String sex) {
        this(name, sex, 0);
    }
    public Student1(String name, String sex, int age) {
        this(name, sex, age, "");
    }
    public Student1(String name, String sex, int age, String grade) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.grade = grade;
    }
}
