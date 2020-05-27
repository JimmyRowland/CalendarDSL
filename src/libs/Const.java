package libs;

import java.util.HashMap;
import java.util.Map;

public class Const {
    public static String NEWCALENDAR = "newCalendar:";
    public static String NEWEVENT = "newEvent:";
    public static String ENDOFEVENT = "endOfEvent";
    public static String OCCURRENCELESSTHAN = "<";
    public static String OCCURRENCEGREATERTHAN = ">";
    public static String DURATION = "duration:";
    public static String FROM = "from:";
    public static String TO = "to:";
    public static String LEFTBRACKETREG = "\\[";
    public static String RIGHTBRACKETREG = "\\]";
    public static String LEFTBRACKET = "[";
    public static String RIGHTBRACKET = "]";
    public static String IREG = "\\|";
    public static String I = "|";
    public static String LOCATION = "location:";
    public static String DESCRIPTION = "description:";
    public static String DAYSOFWEEK = "daysOfWeek:";
    public static String ENDOFCALENDAR = "endOfCalendar";
    public static Map<String, Integer> dayOfWeek;
    static {
        dayOfWeek = new HashMap<>();
        dayOfWeek.put("Sunday", 1);
        dayOfWeek.put("Monday", 2);
        dayOfWeek.put("Tuesday", 3);
        dayOfWeek.put("Wednesday", 4);
        dayOfWeek.put("Thursday", 5);
        dayOfWeek.put("Friday", 6);
        dayOfWeek.put("Saturday", 7);
        dayOfWeek.put("S", 1);
        dayOfWeek.put("M", 2);
        dayOfWeek.put("T", 3);
        dayOfWeek.put("W", 4);
        dayOfWeek.put("Th", 5);
        dayOfWeek.put("F", 6);
        dayOfWeek.put("s", 7);
    }

}
