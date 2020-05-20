package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Day {
    List Events = new ArrayList<Event>();
    public Day(){

    }
    public boolean hasTimeSlot(int duration) {
        return false;
    }

    public boolean addFlexibleEvent(FlexibleEvent flexibleEvent){
        return false;
    }
}
