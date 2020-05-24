package test.model;

import model.*;
import model.io.Writer;
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
        Calendar start0 = Util.nextDayOfWeek(Calendar.FRIDAY);
//        System.out.println(start0.get(Calendar.DAY_OF_WEEK));
        Calendar end0 = Util.nextDayOfWeek(Calendar.FRIDAY);
        start0.set(Calendar.HOUR_OF_DAY,6);
        end0.set(Calendar.HOUR_OF_DAY,8);
        Calendar start1 = Util.nextDayOfWeek(Calendar.FRIDAY);
        Calendar end1 = Util.nextDayOfWeek(Calendar.FRIDAY);
        start1.set(Calendar.HOUR_OF_DAY,7);
        end1.set(Calendar.HOUR_OF_DAY,9);
//        System.out.println(start0.getTime().toString());
//        System.out.println(end0.getTime().toString());
//        System.out.println(start1.getTime().toString());
//        System.out.println(end1.getTime().toString());
        IndividualEvent event0;
        IndividualEvent event1;
        try{
            event0 = EventCreator.createEvent(start0,end0,"event0","location","description");
            event1 = EventCreator.createEvent(start1,end1,"event1","location","description");
            scheduler.addEvent(event0);
            scheduler.addEvent(event1);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

        for(Day day: scheduler.getDays()){
            System.out.println(day.getEvents().size());
        }
        Writer.write("individual.cvs",scheduler);
        assertEquals(scheduler.getDays().get(start0.get(Calendar.DAY_OF_WEEK)-1).getEvents().size(), 1);

    }

    @Test
    void addFlexibleEvent(){
        try{
            scheduler.addEvent(EventCreator.createEvent(null,null,"flexName1","loc","des",2,0,null));
            scheduler.addEvent(EventCreator.createEvent(null,null,"flexName2","loc","des",2,0,null));
        }catch (Exception e){
            e.printStackTrace();
        }
        Writer.write("flexible.cvs",scheduler);
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
        Writer.write("recurring0.cvs",scheduler);
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
        Writer.write("recurring.cvs",scheduler);
        assertEquals(3,scheduler.getDays().get(0).size());
        assertEquals(2,scheduler.getDays().get(2).size());
    }

    @Test
    void allocateMultipleFlexibleEventsOnTheSameDay() {
        try{
            scheduler.addEvent(EventCreator.createEvent("6:00","10:00","recurring1","loc","des",0,0, Arrays.asList(1,2,3,4)));
            scheduler.addEvent(EventCreator.createEvent("10:01","23:59","recurring2","loc","des",0,0,Arrays.asList(1)));
            scheduler.addEvent(EventCreator.createEvent("12:01","23:59","recurring3","loc","des",0,0,Arrays.asList(2)));
            scheduler.addEvent(EventCreator.createEvent("14:01","23:59","recurring4","loc","des",0,0,Arrays.asList(3,4)));
            scheduler.addEvent(EventCreator.createEvent(1,"flexible1","location1","des",2));
            scheduler.addEvent(EventCreator.createEvent(1,"flexible0","location1","des"));
            scheduler.addEvent(EventCreator.createEvent(1,"flexible2","location2","des",3));
            scheduler.addEvent(EventCreator.createEvent(2,"flexible2","location2","des",3));
            scheduler.addEvent(EventCreator.createEvent(1,"flexible2","location2","des",3));
            scheduler.addEvent(EventCreator.createEvent(1,"flexible2","location2","des",3));
            scheduler.allocateFlexibleEvents();
        }catch (Exception e){
            e.printStackTrace();
        }
        Writer.write("recurring.cvs",scheduler);
        assertEquals(4,scheduler.getDays().get(1).size());
        assertEquals(5,scheduler.getDays().get(2).size());
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