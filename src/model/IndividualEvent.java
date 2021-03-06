package model;

import java.util.Calendar;


public class IndividualEvent implements Event, Comparable<Event>{
    Calendar start;
    Calendar end;
    String name;
    String location;
    String description;
    public IndividualEvent(Calendar start, Calendar end, String name, String location, String description) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    @Override
    public void addToScheduler(Scheduler scheduler) {
        scheduler.addEvent(this);
//        System.out.println("!!!!!!!!!!!!!");
//        Util.printCalendar(start);
//        Util.printCalendar(end);
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

    @Override
    public boolean hasConflict(Calendar start, Calendar end) {
//        Util.printCalendar(start);
//        Util.printCalendar(end);
        if(!this.start.before(start)&& !this.end.after(end)){
            return false;
        }
        return true;
    }

    @Override
    public Calendar getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}