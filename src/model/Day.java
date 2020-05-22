package model;

import javax.sound.midi.Soundbank;
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



    public boolean hasTimeSlot(Event event) {
        for (int i = 0; i < this.timeStamps.size(); i += 2) {
            Calendar start = timeStamps.get(i);
            Calendar end = timeStamps.get(i + 1);
            if (!event.hasConflict(start, end)) {
                timeStamps.add(event.getStart());
                timeStamps.add(event.getEnd());
                Collections.sort(timeStamps);
                return true;
            }
        }
        return false;
    }

    // Inefficient but less code
    public void sortEvent() {
        Collections.sort(events);

    }

    public boolean addEvent(FlexibleEvent flexibleEvent) {
        if (this.hasTimeSlot(flexibleEvent)) {
            events.add(flexibleEvent);
            sortEvent();
            return true;
        }
        return false;
    }

    public void addEvent(FlexibleEventWithDayField FlexibleEventWithDayField) {
        this.flexibleEvents.add(FlexibleEventWithDayField);
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

    public void addEvent(IndividualEvent event) {
        if (hasTimeSlot(event)) {
            events.add(event);
            sortEvent();
        }
    }

//    // Skip events with conflict without throwing exception
//    public void addEvent(RecurringEvent recurringEvent) {
//        for (Event event : recurringEvent.getEvents()) {
//            if (hasTimeSlot(event)) {
//                events.add(event);
//                sortEvent();
//            }
//        }
//    }

    @Override
    public void allocateFlexibleEvents() {
        for (FlexibleEvent flexibleEvent : flexibleEvents) {
            this.addEvent(flexibleEvent);
        }
    }
}