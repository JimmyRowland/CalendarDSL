package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Day {
    ArrayList<Event> events = new ArrayList<Event>();
    public Day(){
    }

    // modifies flexible event
    public boolean hasTimeSlot(FlexibleEvent flexibleEvent){
        return false;
    }

    public boolean hasTimeSlot(Event event){
        return false;
    }

    // Inefficient but less code
    public void sortEvent(){

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
}
