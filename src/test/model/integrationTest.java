package test.model;

import libs.Const;
import model.Scheduler;
import model.io.Parser;
import model.io.Writer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class integrationTest {
    Parser parse;
    Scheduler scheduler;
    @BeforeEach
    void init() {
    }


    @Test
    void simpleTestDaysOfWeek() {

        parse = new Parser("src/test/model/data/2flexEventsWithEmptyFields");
        try{
            scheduler = parse.calendar();
            assertEquals(scheduler.getFlexibleEventList().size(),1);
            assertEquals(scheduler.getDays().get(1).getFlexibleEvents().size(),5);
            assertEquals(scheduler.getDays().get(0).getFlexibleEvents().size(),4);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        scheduler.allocateFlexibleEvents();
        Writer.write("2flexEventsWithEmptyFields.cvs",scheduler);

    }

    @Test
    void invalidDaysOfWeek() {
        parse = new Parser("src/test/model/data/InvalidDaysOfweek");
        assertThrows(Exception.class, ()-> parse.calendar());
    }

    @Test
    void flexDayRangeAndDayList() {

        parse = new Parser("src/test/model/data/2flexEventsDayRangeAndDayList");
        try{
            scheduler = parse.calendar();
            assertEquals(scheduler.getFlexibleEventList().size(),1);
            assertEquals(scheduler.getDays().get(4).getFlexibleEvents().size(),4);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        scheduler.allocateFlexibleEvents();
        Writer.write("2flexEventsDayRangeAndDayList.cvs",scheduler);

    }

    @Test
    void recurringEvent() {

        parse = new Parser("src/test/model/data/recurringEvents");
        try{
            scheduler = parse.calendar();
            assertEquals(scheduler.getFlexibleEventList().size(),2);
            assertEquals(scheduler.getDays().get(4).getFlexibleEvents().size(),8);
            assertEquals(scheduler.getDays().get(1).getEvents().size(),1);
            assertEquals(scheduler.getDays().get(2).getEvents().size(),2);
            assertEquals(scheduler.getDays().get(3).getEvents().size(),0);
            assertEquals(scheduler.getDays().get(4).getEvents().size(),1);
            assertEquals(scheduler.getDays().get(5).getEvents().size(),0);
            assertEquals(scheduler.getDays().get(6).getEvents().size(),2);
            assertEquals(scheduler.getDays().get(0).getEvents().size(),1);

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        scheduler.allocateFlexibleEvents();
        Writer.write("recurringEvents.cvs",scheduler);

    }

    @Test
    void scheduleEvents() {

        parse = new Parser("src/test/model/data/scheduleEvents");
        try{
            scheduler = parse.calendar();
            assertEquals(scheduler.getFlexibleEventList().size(),1);
            assertEquals(scheduler.getDays().get(4).getFlexibleEvents().size(),8);
            assertEquals(scheduler.getDays().get(1).getEvents().size(),1);
            assertEquals(scheduler.getDays().get(2).getEvents().size(),4);
            assertEquals(scheduler.getDays().get(3).getEvents().size(),0);
            assertEquals(scheduler.getDays().get(4).getEvents().size(),1);
            assertEquals(scheduler.getDays().get(5).getEvents().size(),3);
            assertEquals(scheduler.getDays().get(6).getEvents().size(),2);
            assertEquals(scheduler.getDays().get(0).getEvents().size(),1);

        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        scheduler.allocateFlexibleEvents();
        Writer.write("scheduleEvents.cvs",scheduler);

    }

    @Test
    void scheduleEventsAlreadyScheduled() {

        parse = new Parser("src/test/model/data/scheduleEventsAlreadyScheduled");
        assertThrows(Exception.class, ()-> parse.calendar());

    }



}
