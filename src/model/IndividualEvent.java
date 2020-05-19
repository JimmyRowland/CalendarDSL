package model;

import java.util.Calendar;


public class IndividualEvent implements Event, Comparable<Event>{
    Calendar start;
    Calendar end;
    public IndividualEvent(Calendar start, Calendar end, String name, int priority, String location, String description){
//     int timeRange    repetition

    }

    @Override
    public int compareTo(Event event) {
        return start.compareTo(event.getStart());
    }

    @Override
    public Calendar getStart() {
        return start;
    }

    @Override
    public int getDayOfWeek() {
        return start.get(Calendar.DAY_OF_WEEK);
    }
}
