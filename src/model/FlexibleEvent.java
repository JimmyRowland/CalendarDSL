package model;

import java.util.Calendar;

public class FlexibleEvent implements Event, Comparable<Event> {
    int duration;
    Calendar start;
    Calendar end;
    public FlexibleEvent(String name, String location, String description, int duration){

    }

    void setStart(){

    }
    void setEnd(){

    }

    public Calendar getStart(){
        return this.start;
    }
    @Override
    public int getDayOfWeek() {
        return start.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public int compareTo(Event event) {
        return 0;
    }
}
