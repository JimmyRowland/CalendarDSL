package model;

import java.util.Calendar;
import java.util.List;

public class FlexibleEventMultiDay extends FlexibleEvent{
    Calendar start;
    Calendar end;
    List<Integer> daysOfWeek;

    public FlexibleEventMultiDay(int duration, String name, String location, String description, List<Integer> daysOfWeek) {
        super(duration,name,location,description);
        this.daysOfWeek = daysOfWeek;
        this.start = Util.getNewCalendar();
    }
    @Override
    public int getDayOfWeek() {
        return daysOfWeek.get(0);
    }

    @Override
    public void addToScheduler(Scheduler scheduler) {
        scheduler.addEvent( this);
    }

    public List<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }

    public FlexibleEventWithDayField getFlexibleEventWithDayField(int i){
        return new FlexibleEventWithDayField(duration,name,location,description,daysOfWeek.get(i));
    }
}
