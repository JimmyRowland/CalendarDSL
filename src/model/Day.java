package model;

import java.lang.reflect.Array;
import java.util.*;

public class Day implements FlexibleEventAllocatable {
    public ArrayList<Event> getEvents() {
        return events;
    }

    ArrayList<Event> events = new ArrayList<Event>();
    ArrayList<Calendar> timeStamps;
    ArrayList<FlexibleEvent> flexibleEvents;

    public Day(Calendar startOfTheDay) {
        flexibleEvents = new ArrayList<>();
        timeStamps = new ArrayList<>();
        timeStamps.add(startOfTheDay);
        Calendar endOfTheDay = ((Calendar) startOfTheDay.clone());
        Util.setTime(endOfTheDay,24);
        timeStamps.add(endOfTheDay);
    }

//    // modifies flexible event
//    public boolean hasTimeSlot(FlexibleEvent flexibleEvent){
//        for(int i = 0; i<this.events.size(); i++){
//            Calendar start = this.events.get(i).getEnd();
//            Calendar end;
//            if(i != this.events.size()-1){
//                end = this.events.get(i+1).getStart();
//            }else{
//                end = (Calendar) start.clone();
//                end.set(Calendar.HOUR,24);
//            }
//            int space = start.compareTo(end);
//            if(flexibleEvent.getDurationInMS()>space){
//                flexibleEvent.setStart(start);
//                flexibleEvent.setEnd(end);
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean hasTimeSlot(Event event) {
        if (events.size() == 0) {
            return true;
        }

        if (timeStamps.size() < events.size() * 2 + 2) {
            for (Event e : events) {
                timeStamps.add(e.getStart());
                timeStamps.add(e.getEnd());
            }
            Collections.sort(timeStamps);
        }

        for (int i = 0; i < this.timeStamps.size(); i += 2) {
            Calendar start = timeStamps.get(i);
            Calendar end = timeStamps.get(i + 1);
            if (!event.hasConflict(start, end)) {
                return true;
            }
        }
        return false;
    }

    // Inefficient but less code
    public void sortEvent() {
        Collections.sort(events);

    }

    public boolean addFlexibleEvent(FlexibleEvent flexibleEvent) {
        if (this.hasTimeSlot(flexibleEvent)) {
            events.add(flexibleEvent);
            sortEvent();
            return true;
        }
        return false;
    }

    public Event getEvent(int index) {
        return events.get(index);
    }

    public int size() {
        return events.size();
    }

    public void addEvent(Event event) {
        if (hasTimeSlot(event)) {
            events.add(event);
            sortEvent();
        }
    }

    // Skip events with conflict without throwing exception
    public void addEvent(ReoccuringEvent reoccuringEvent) {
        for (Event event : reoccuringEvent.getEvents()) {
            if (hasTimeSlot(event)) {
                events.add(event);
                sortEvent();
            }
        }
    }

    @Override
    public void allocateFlexibleEvents() {
        for (FlexibleEvent flexibleEvent : flexibleEvents) {
            this.addFlexibleEvent(flexibleEvent);
        }
    }
}
