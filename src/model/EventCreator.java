package model;

import java.util.Calendar;
import java.util.Date;

public class EventCreator {
    public static IndividualEvent createEvent(Calendar start, Calendar end, String name, String location, String description) throws Exception {
        EventCreator.isEndAfterStart(start,end);
        EventCreator.isEventOnDifferentDay(start,end);
        return new IndividualEvent(start, end, name, location, description);
    }

    public static FlexibleEvent createEvent(int duration, String name, String location, String description) {
        return new FlexibleEvent(duration, name, location, description);
    }

    public static GroupEvent createEvent(String name, String location, String description) {
        return new GroupEvent(name, location, description);
    }
    public static boolean isEndAfterStart(Calendar start, Calendar end) throws Exception{
        if(start.before(end)){
            throw new Exception("Event ends before it starts");
        }
        return true;
    }
    public static boolean isEventOnDifferentDay(Calendar start, Calendar end) throws Exception{
        if(start.get(Calendar.DATE) == end.get(Calendar.DATE)){
            return false;
        }
        throw new Exception("Same event can't happen on different days");
    }
}
