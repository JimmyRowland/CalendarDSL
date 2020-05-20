package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CancellationException;

public class ReoccuringEvent implements Event, Comparable<Event>{
    Calendar start;
    Calendar end;
    String name;
    String location;
    String description;

    List<Event> events;

    public ReoccuringEvent(String name, String location, String description, Calendar start, Calendar end, int repeatNum ) {
        this.name = name;
        this.location = location;
        this.description = description;
        events = new ArrayList<Event>();
        // Should not support repeating group event
        for(int i = 0; i < repeatNum && start.get(Calendar.DAY_OF_WEEK) <= 7; i++){
            events.add(new IndividualEvent(start,end,name,location,description));
            start.add(Calendar.DATE,1);
            end.add(Calendar.DATE,1);
        }
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
    public int compareTo(Event event) {
        return start.compareTo(event.getStart());
    }

    @Override
    public Calendar getEnd() {
        return end;
    }

    public List<Event> getEvents() {
        return events;
    }
}
