package model;

import java.util.Calendar;

public class FlexibleEvent implements Event, Comparable<Event> {
    int duration;
    Calendar start;
    Calendar end;
    String name;
    String location;
    String description;

    public FlexibleEvent(int duration, String name, String location, String description) {
        this.duration = duration;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    @Override
    public Calendar getEnd() {
        return end;
    }

    void setEnd(Calendar end) {
        this.end = end;
    }

    void setStart(Calendar start) {
        this.start = start;
    }

    float getDuration() {
        return duration;
    }

    float getDurationInMS() {
        return duration * 3600000;
    }

    public Calendar getStart() {
        return this.start;
    }

    @Override
    public int getDayOfWeek() {
        return start.get(Calendar.DAY_OF_WEEK);
    }
    // TODO might need to switch to LocalDateTime
    @Override
    public boolean hasConflict(Calendar start, Calendar end) {
        long space = end.getTimeInMillis() - start.getTimeInMillis();
//        Util.printCalendar(start);
//        Util.printCalendar(end);
        if (this.getDurationInMS() < space) {
            this.setStart((Calendar) start.clone());
            Calendar newEnd = (Calendar) start.clone();
            newEnd.add(Calendar.MINUTE,this.duration*60);
            this.setEnd(newEnd);
            return false;
        }
        return true;
    }

    @Override
    public void addToScheduler(Scheduler scheduler) {
        scheduler.addEvent(this);
    }

    @Override
    public int compareTo(Event event) {
        return start.compareTo(event.getStart());
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