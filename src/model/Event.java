package model;

import java.util.Calendar;

public interface Event extends Comparable {
    public Calendar getStart();
    public int getDayOfWeek();
}
