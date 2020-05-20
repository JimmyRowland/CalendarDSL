package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Scheduler {
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
            days.add(new Day(startOfTheDay));
        }
        flexibleEventList = new ArrayList<>();
    }
    public void addFlexibleEvent(FlexibleEvent flexibleEvent){
        flexibleEventList.add(flexibleEvent);
    }

    public void addEvent(Event event){
        int dayOfWeek = event.getDayOfWeek();
        days.get(dayOfWeek).addEvent(event);
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
        for(FlexibleEvent flexibleEvent: flexibleEventList){
            for(Day day: days){
                if(day.addFlexibleEvent(flexibleEvent)){
                    break;
                }
            }
            throw new RuntimeException("Not enough time for event "+ flexibleEvent.toString());
        }
    }


}
