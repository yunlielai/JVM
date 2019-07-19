package effective.c05_string;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Person {
    private final Date birthDate;
    private static final Date BOOM_START;
    private static final Date BOOM_END;
    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));    //新创建Calendar实例
        gmtCal.set(1949, Calendar.JANUARY, 1, 0, 0, 0);
        System.out.println("创建比较的开始日期");
        BOOM_START = gmtCal.getTime();  //新创建Date实例
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        System.out.println("创建比较的结束日期");
        BOOM_END = gmtCal.getTime();    //新创建Date实例
    }
    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
    }
}
