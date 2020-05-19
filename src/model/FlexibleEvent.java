package model;

import java.util.Calendar;

public class FlexibleEvent implements Event {
    int duration;
    Calendar start;
    Calendar end;
    public FlexibleEvent(String name, String location, String description, int duration){

    }

    void setStart(){

    }
    void setEnd(){

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public Calendar getStart(){
        return this.start;
    }
    @Override
    public int getDayOfWeek() {
        return start.get(Calendar.DAY_OF_WEEK);
    }
}
