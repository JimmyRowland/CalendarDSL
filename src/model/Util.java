package model;


import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Util {

    public static Calendar getNewNextWeekCalendar(String dayOfTheWeek, String time){
        int dow = dayOfTheWeekMap(dayOfTheWeek);
        return Util.getNewNextWeekCalendar(dow,time);
    }

    public static Calendar getNewNextWeekCalendar(int dayOfTheWeek, String time){
        Calendar calendar = Util.nextDayOfWeek(dayOfTheWeek);
        String[] timeList =  time.split(":");
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(timeList[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(timeList[1]));
//        System.out.println("---");
//        System.out.println(time);
//        System.out.println(calendar.getTime().toString());
//        System.out.println("---");
        return calendar;
    }

    public static int dayOfTheWeekMap(String dayOfTheWeek){
        if(dayOfTheWeek.equals("Sunday")) return 1;
        if(dayOfTheWeek.equals("Monday")) return 2;
        if(dayOfTheWeek.equals("Tuesday")) return 3;
        if(dayOfTheWeek.equals("Wednesday")) return 4;
        if(dayOfTheWeek.equals("Thursday")) return 5;
        if(dayOfTheWeek.equals("Friday")) return 6;
        if(dayOfTheWeek.equals("Saturday")) return 7;
        return 0;
    }

    //https://stackoverflow.com/questions/3463756/is-there-a-good-way-to-get-the-date-of-the-coming-wednesday
    public static Calendar nextDayOfWeek(int dayOfWeek) {
        Calendar date = Calendar.getInstance();
        if(date.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
            date.set(Calendar.DAY_OF_WEEK,dayOfWeek);
            date.add(Calendar.DAY_OF_MONTH, 7);
        }
        return date;
    }

    public static void setTime(Calendar calendar, int hour){
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
    }

    public static void printCalendar(Calendar calendar){
        System.out.println(calendar.getTime().toString());
        System.out.println(calendar.get(Calendar.MILLISECOND));
    }

}
