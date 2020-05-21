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
        calendar.set(Calendar.HOUR,Integer.parseInt(timeList[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(timeList[1]));
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
        calendar.set(Calendar.HOUR,hour-1);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
    }

}
