package test.model;

import model.Day;
import model.EventCreator;
import model.IndividualEvent;
import model.Scheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

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
    void allocateFlexibleEvents() {

    }
}