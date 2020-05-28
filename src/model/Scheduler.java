package model;

import java.text.SimpleDateFormat;
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
        boolean allocated;
        for(Day day: days){
            day.allocateFlexibleEvents();
        }
        for(FlexibleEvent flexibleEvent: flexibleEventList){
            allocated = false;
            for(Day day: days){
                if(day.addEvent(flexibleEvent)){
                    allocated = true;
                    break;
                }
            }
            if (!allocated) {
                throw new RuntimeException("Not enough time for event "+ flexibleEvent.toString());
            }
        }
    }

    private enum DaysOfWeek {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY
    }



    public void print(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:");
        String indent = "   ";
        for(int i = 0; i < 7; i++){
            System.out.println(DaysOfWeek.values()[i] + "\n" + "---------------------------");
            List<Event> events = this.days.get(i).getEvents();
            for(Event e: events) {
                String end = "";
                if (e.getEnd() != null) {
                }
                    System.out.println(indent + "Event " + e.getName() + ": " + formatter.format(e.getStart().getTime()) + "-" + formatter.format(e.getEnd().getTime()));
                    System.out.println(indent + indent + "Location: " + e.getLocation());
                    System.out.println(indent + indent + "Description: " + e.getDescription());
            }
        }
    }
}