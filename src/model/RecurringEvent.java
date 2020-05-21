package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CancellationException;

public class RecurringEvent implements Event, Comparable<Event>{
    Calendar start;
    Calendar end;
    String name;
    String location;
    String description;

    List<Event> events;
    List<Integer> daysOfWeek;

    public RecurringEvent(String name, String location, String description, Calendar start, Calendar end, int repeatNum ) {
//        this.name = name;
//        this.location = location;
//        this.description = description;
//        events = new ArrayList<Event>();
//        // Should not support repeating group event
//        for(int i = 0; i < repeatNum && start.get(Calendar.DAY_OF_WEEK) <= 7; i++){
//            events.add(new IndividualEvent(start,end,name,location,description));
//            start.add(Calendar.DATE,1);
//            end.add(Calendar.DATE,1);
//        }
    }
    public RecurringEvent(String name, String location, String description, Calendar start, Calendar end, List<Integer> daysOfWeek ) {
        this.name = name;
        this.location = location;
        this.description = description;
        events = new ArrayList<>();
        this.daysOfWeek = daysOfWeek;
        // Should not support repeating group event
        for(int i = 0; i < daysOfWeek.size(); i++){
            Calendar newStart = (Calendar) start.clone();
            Calendar newEnd = (Calendar) end.clone();
            newStart.set(Calendar.DAY_OF_WEEK,daysOfWeek.get(i));
            newEnd.set(Calendar.DAY_OF_WEEK,daysOfWeek.get(i));
            events.add(new IndividualEvent(newStart,newEnd,name,location,description));
        }
    }

    @Override
    public void addToScheduler(Scheduler scheduler) {
        scheduler.addEvent( this);
    }

    @Override
    public Calendar getStart() {
        return start;
    }

    @Override
    public int getDayOfWeek() {
        return start.get(Calendar.DAY_OF_WEEK);
    }
    //TODO
    @Override
    public boolean hasConflict(Calendar start, Calendar end) {
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

    public List<Event> getEvents() {
        return events;
    }

    public List<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
