package effective.c02_builder;

public class Student3 {
    /*必填*/
    private String name;
    private int age;
    /*选填*/
    private String sex;
    private String grade;

    public static class Builder {
        private String name;
        private int age;
        private String sex = "";
        private String grade = "";

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }
        public Builder grade(String grade) {
            this.grade = grade;
            return this;
        }
        public Student3 build() {
            return new Student3(this);
        }
    }
    private Student3(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
        this.grade = builder.grade;
    }

}
