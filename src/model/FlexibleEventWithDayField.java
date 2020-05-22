package model;

import java.util.Calendar;

public class FlexibleEventWithDayField extends FlexibleEvent{
    int duration;
    Calendar start;
    Calendar end;
    String name;
    String location;
    String description;
    int dayOfWeek;

    public FlexibleEventWithDayField(int duration, String name, String location, String description, int dayOfWeek) {
        super(duration,name,location,description);
        this.dayOfWeek = dayOfWeek;
        this.start = Calendar.getInstance();
        start.set(Calendar.DAY_OF_WEEK, dayOfWeek);
    }
    @Override
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    @Override
    public void addToScheduler(Scheduler scheduler) {
        scheduler.addEvent( this);
    }
}
