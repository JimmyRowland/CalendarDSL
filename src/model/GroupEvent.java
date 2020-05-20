package model;

import com.sun.org.apache.xml.internal.dtm.ref.ExpandedNameTable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GroupEvent implements Event, Comparable<Event>{
    Calendar start;
    Calendar end;
    String name;
    String location;
    String description;

    List<Event> events;

    public GroupEvent(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
        events = new ArrayList<Event>();
    }

    void addEvent(Event event){
        events.add(event);
    };

    void setStartAndEndDate(){

    }


    @Override
    public Calendar getStart() {
        return null;
    }

    @Override
    public int getDayOfWeek() {
        return start.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public boolean hasConflict(Calendar start, Calendar end) {
        if(this.start.after(start)&& this.end.before(end)){
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Event event) {
        return start.compareTo(event.getStart());
    }

    @Override
    public Calendar getEnd() {
        return end;
    }
}
