package model;

import com.sun.org.apache.xml.internal.dtm.ref.ExpandedNameTable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GroupEvent implements Event{
    Calendar start;
    Calendar end;

    List Events = new ArrayList<Event>();
    void addEvent(Event event){

    };

    boolean hasConflict(Event event) throws Exception {

    }


}
