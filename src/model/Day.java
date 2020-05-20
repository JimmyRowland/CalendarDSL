package model;

import java.lang.reflect.Array;
import java.util.*;

public class Day {
    public ArrayList<Event> getEvents() {
        return events;
    }

    ArrayList<Event> events = new ArrayList<Event>();
    public Day(){
    }

    // modifies flexible event
    public boolean hasTimeSlot(FlexibleEvent flexibleEvent){
        for(int i = 0; i<this.events.size(); i++){
            Calendar start = this.events.get(i).getEnd();
            Calendar end;
            if(i != this.events.size()-1){
                end = this.events.get(i+1).getStart();
            }else{
                end = (Calendar) start.clone();
                end.set(Calendar.HOUR,24);
            }

            int space = start.compareTo(end);
            if(flexibleEvent.getDurationInMS()>space){
                flexibleEvent.setStart(start);
                flexibleEvent.setEnd(end);
                return true;
            }
        }
        return false;
    }

    public boolean hasTimeSlot(Event event){
        if(events.size()==0){
            return true;
        }
        for(int i = 0; i<this.events.size(); i++){
            Calendar start = this.events.get(i).getEnd();
            Calendar end;
            if(i != this.events.size()-1){
                end = this.events.get(i+1).getStart();
            }else{
                end = (Calendar) start.clone();
                end.set(Calendar.HOUR,24);
            }
            if(event.getStart().after(start) && event.getEnd().before(end)){
                return true;
            }
        }
        return false;
    }

    // Inefficient but less code
    public void sortEvent(){
        Collections.sort(events);

    }

    public boolean addFlexibleEvent(FlexibleEvent flexibleEvent){
        events.add(flexibleEvent);
        sortEvent();
        return false;
    }

    public Event getEvent(int index){
        return events.get(index);
    }

    public int size(){
        return events.size();
    }

    public void addEvent(Event event){
        if(hasTimeSlot(event)){
            events.add(event);
            sortEvent();
        }
    }
    // Skip events with conflict without throwing exception
    public void addEvent(ReoccuringEvent reoccuringEvent){
        for(Event event: reoccuringEvent.getEvents()){
            if(hasTimeSlot(event)){
                events.add(event);
                sortEvent();
            }
        }
    }
}
