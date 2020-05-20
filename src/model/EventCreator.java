package model;

import java.util.Calendar;

public class EventCreator {
    public static IndividualEvent createEvent(Calendar start, Calendar end, String name, String location, String description) {
        return new IndividualEvent(start, end, name, location, description);
    }

    public static FlexibleEvent createEvent(int duration, String name, String location, String description) {
        return new FlexibleEvent(duration, name, location, description);
    }

    public static GroupEvent createEvent(String name, String location, String description) {
        return new GroupEvent(name, location, description);
    }
}
