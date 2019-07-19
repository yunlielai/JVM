package test.c01_enums;

import java.util.EnumSet;

public class Test {
    public static void main(String[] args) {
        //简单的使用
        Test.switchWeek();
        //带属性的enum
        System.out.println(Msg.S000.getCode());
        System.out.println(Msg.S000.getDes());
        //EnumSet工具类，输出mon到wed之间的枚举
        for(WeekDayEnum day : EnumSet.range(WeekDayEnum.Mon, WeekDayEnum.Wed)) {
            System.out.println(day);
        }
    }

    public static void switchWeek(){
        WeekDayEnum weekDayEnum=readToday();
        switch (weekDayEnum) {
            case Mon:
                System.out.println("is Mon");
                break;
            case Fri:
                System.out.println("is Fir");
                break;
            case Sun:
                System.out.println("is Sun");
                break;
        }
    }

    public static WeekDayEnum readToday(){
        return WeekDayEnum.Fri;
    }
}
