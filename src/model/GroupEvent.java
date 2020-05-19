package model;

import com.sun.org.apache.xml.internal.dtm.ref.ExpandedNameTable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GroupEvent implements Event, Comparable<Event>{
    Calendar start;
    Calendar end;

    List Events = new ArrayList<Event>();
    void addEvent(Event event){

    };
//
//    boolean hasConflict(Event event) throws Exception {
//
//    }


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
        return 0;
    }

}
