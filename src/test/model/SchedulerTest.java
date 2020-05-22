package test.model;

import model.Day;
import model.EventCreator;
import model.IndividualEvent;
import model.Scheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
//dev
class SchedulerTest {
    Scheduler scheduler;
    @BeforeEach
    void init(){
        scheduler = new Scheduler();
    }
    @Test
    void addEvent() {
        Calendar start0 = Calendar.getInstance();
        System.out.println(start0.get(Calendar.DAY_OF_WEEK));
        Calendar end0 = Calendar.getInstance();
        end0.add(Calendar.HOUR,2);
        Calendar start1 = Calendar.getInstance();
        start1.add(Calendar.HOUR,1);
        Calendar end1 = Calendar.getInstance();
        end1.add(Calendar.HOUR,1);
        System.out.println(start0.getTime().toString());
        System.out.println(end0.getTime().toString());
        System.out.println(start1.getTime().toString());
        System.out.println(end1.getTime().toString());
        IndividualEvent event0 = new IndividualEvent(start0,end0,"event0","location","description");
        IndividualEvent event1 = new IndividualEvent(start0,end0,"event1","location","description");
        scheduler.addEvent(event0);
        scheduler.addEvent(event1);
        assertEquals(start0.get(Calendar.DAY_OF_WEEK),4);
        assertEquals(scheduler.getDays().get(start0.get(Calendar.DAY_OF_WEEK)).getEvents().size(), 1);
    }

    @Test
    void addFlexibleEvent(){
        try{
            scheduler.addEvent(EventCreator.createEvent(null,null,"flexName1","loc","des",2,0,null));
            scheduler.addEvent(EventCreator.createEvent(null,null,"flexName2","loc","des",2,0,null));
        }catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(2,scheduler.getFlexibleEventList().size());
    }

    @Test
    void addRecurringEvent(){
        try{
            scheduler.addEvent(EventCreator.createEvent("8:00","10:00","recurring1","loc","des",0,0, Arrays.asList(1,2,3,4)));
            scheduler.addEvent(EventCreator.createEvent("7:00","9:00","recurring2","loc","des",0,0,Arrays.asList(1,2,3,4)));
            scheduler.addEvent(EventCreator.createEvent("6:00","7:00","recurring2","loc","des",0,0,Arrays.asList(1,2,3,4)));
        }catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(2,scheduler.getDays().get(2).size());
    }

    @Test
    void allocateFlexibleEvents() {
        try{
            scheduler.addEvent(EventCreator.createEvent("6:00","10:00","recurring1","loc","des",0,0, Arrays.asList(1,2,3,4)));
            scheduler.addEvent(EventCreator.createEvent("10:01","23:59","recurring2","loc","des",0,0,Arrays.asList(3,4)));
            scheduler.addEvent(EventCreator.createEvent("12:01","23:59","recurring2","loc","des",0,0,Arrays.asList(1,2)));
            scheduler.addEvent(EventCreator.createEvent(2,"flexible1","location1","des",1));
            scheduler.addEvent(EventCreator.createEvent(2,"flexible2","location2","des",3));
            scheduler.allocateFlexibleEvents();
        }catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(3,scheduler.getDays().get(0).size());
        assertEquals(2,scheduler.getDays().get(2).size());
    }

    @Test
    void test(){
        Calendar test1 = Calendar.getInstance();
        test1.set(Calendar.SECOND,0);
        Calendar test2 = Calendar.getInstance();
        test2.set(Calendar.SECOND,59);
        System.out.print(test2.compareTo(test1) + "");
    }
}