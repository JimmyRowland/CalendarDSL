package model;


import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Util {

    public static Calendar getNewCalendar(String dayOfTheWeek, String time){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK,dayOfTheWeekMap(dayOfTheWeek));
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
        int diff = dayOfWeek - date.get(Calendar.DAY_OF_WEEK);
        if (diff <= 0) {
            diff += 7;
        }
        date.add(Calendar.DAY_OF_MONTH, diff);
        date.set(Calendar.HOUR,6);
        return date;
    }

}
