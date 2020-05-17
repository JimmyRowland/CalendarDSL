package model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    List<Day> schedules;
    public Scheduler(){
        schedules = new ArrayList<>();
        schedules.add(new MWFSchedule());
        schedules.add(new TTHSchedule());
        schedules.add(new MWFSchedule());
        schedules.add(new TTHSchedule());
        schedules.add(new MWFSchedule());
        schedules.add(new SSSchedule());
        schedules.add(new SSSchedule());
    }




}
