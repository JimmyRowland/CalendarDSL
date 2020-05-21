package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Scheduler implements FlexibleEventAllocatable {
    public List<Day> getDays() {
        return days;
    }

    public List<FlexibleEvent> getFlexibleEventList() {
        return flexibleEventList;
    }

    List<Day> days;
    List<FlexibleEvent> flexibleEventList;
    public Scheduler(){
        days = new ArrayList<>();
        for(int i = 1;  i < 8; i++){
            Calendar startOfTheDay = Util.nextDayOfWeek(i);
            Util.setTime(startOfTheDay,6);
            days.add(new Day(startOfTheDay));
        }
        flexibleEventList = new ArrayList<>();
    }

    public void addEvent(IndividualEvent event){
        int dayOfWeek = event.getDayOfWeek();
        days.get(dayOfWeek).addEvent(event);
    }

    public void addEvent(Event event){
        event.addToScheduler(this);
    }

    public void addEvent(RecurringEvent recurringEvent){
        List<Integer> days = recurringEvent.getDaysOfWeek();
        for(int d: days){
            addEvent(recurringEvent.getEvents().get(d));
        }
    }

    public void addEvent(FlexibleEvent flexibleEvent){
        flexibleEventList.add(flexibleEvent);
    }

    public void addEvent(FlexibleEventWithDayField flexibleEventWithDayField){
        int dayOfWeek = flexibleEventWithDayField.getDayOfWeek();
        days.get(dayOfWeek).addEvent(flexibleEventWithDayField);
    }


    // Not specified in grammar
//    public Scheduler getScheduler(int priority){
//
//    }
//
//    public Scheduler getScheduler(String day){
//
//    }
//
//    public Scheduler getScheduler(int startDate, int endDate){
//
//    }

    // Call this at the end of the driver
    public void allocateFlexibleEvents(){
        for(Day day: days){
            day.allocateFlexibleEvents();
        }
        for(FlexibleEvent flexibleEvent: flexibleEventList){
            for(Day day: days){
                if(day.addEvent(flexibleEvent)){
                    break;
                }
            }
            throw new RuntimeException("Not enough time for event "+ flexibleEvent.toString());
        }
    }
}
