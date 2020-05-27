package test.AST;

import libs.Const;
import model.Scheduler;
import model.io.Parser;
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

        parse = new Parser("src/test/AST/2flexEventsWithEmptyFields");
        try{
            scheduler = parse.calendar();
            assertEquals(scheduler.getFlexibleEventList().size(),1);
            assertEquals(scheduler.getDays().get(1).getFlexibleEvents().size(),5);
            assertEquals(scheduler.getDays().get(0).getFlexibleEvents().size(),4);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

    }

    @Test
    void invalidDaysOfWeek() {
        parse = new Parser("src/test/AST/InvalidDaysOfweek");
        assertThrows(Exception.class, ()-> parse.calendar());
    }

    @Test
    void flexDayRangeAndDayList() {

        parse = new Parser("src/test/AST/2flexEventsDayRangeAndDayList");
        try{
            scheduler = parse.calendar();
            assertEquals(scheduler.getFlexibleEventList().size(),1);
            assertEquals(scheduler.getDays().get(4).getFlexibleEvents().size(),4);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

    }



}
