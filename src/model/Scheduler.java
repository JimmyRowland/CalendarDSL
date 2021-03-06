package model;

import model.io.CVS;

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
//            Util.printCalendar(startOfTheDay);
            days.add(new Day(startOfTheDay));
        }
        flexibleEventList = new ArrayList<>();
    }

    public void addEvent(IndividualEvent event){
        int dayOfWeek = event.getDayOfWeek();
//        System.out.println("Individual");
//        Util.printCalendar(event.getStart());
//        Util.printCalendar(event.getEnd());
        days.get(dayOfWeek-1).addEvent(event);
    }

    public void addEvent(Event event){
        event.addToScheduler(this);
    }

    public void addEvent(RecurringEvent recurringEvent){
//        List<Integer> days = recurringEvent.getDaysOfWeek();
        List<Event> recurringEventList = recurringEvent.getEvents();
        for(int i = 0; i<recurringEventList.size(); i++){
            addEvent(recurringEventList.get(i));
        }
    }

    public void addEvent(FlexibleEventMultiDay flexibleEventMultiDay){
//        List<Integer> days = recurringEvent.getDaysOfWeek();
        List<Integer> daysOfWeek = flexibleEventMultiDay.getDaysOfWeek();
        for(int i = 0; i<daysOfWeek.size(); i++){
            FlexibleEventWithDayField flexibleEventWithDayField = flexibleEventMultiDay.getFlexibleEventWithDayField(i);
            addEvent(flexibleEventWithDayField);
        }
    }

    public void addEvent(FlexibleEvent flexibleEvent){
        flexibleEventList.add(flexibleEvent);
    }

    public void addEvent(FlexibleEventWithDayField flexibleEventWithDayField){
        int dayOfWeek = flexibleEventWithDayField.getDayOfWeek();
        days.get(dayOfWeek-1).addEvent(flexibleEventWithDayField);
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
//            throw new RuntimeException("Not enough time for event "+ flexibleEvent.toString());
        }
        flexibleEventList.clear();
    }

    public void scheduleEvent(FlexibleEvent flexibleEvent){
        boolean scheduled = false;
        for(Day day: days){
            if(day.addEvent(flexibleEvent)){
                scheduled = true;
                break;
            }
        }
        if(!scheduled){
            flexibleEvent.throwNotEnoughTimeException();
        }
    }
}