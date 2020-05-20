package model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    List<Day> schedules;
    public Scheduler(){
        schedules = new ArrayList<>();
        for(int i = 0;  i < 7; i++){
            schedules.add(new Day());
        }
    }
    public void addFlexibleEvent(FlexibleEvent flexibleEvent){

    }
    public Scheduler getScheduler(int priority) {

        return null;
    }

    public Scheduler getScheduler(String day){

        return null;
    }

    public Scheduler getScheduler(int startDate, int endDate){

        return null;
    }

}
