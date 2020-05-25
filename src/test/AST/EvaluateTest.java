package test.AST;
import AST.Day;
import AST.Event;
import model.*;
import AST.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvaluateTest {
    Program program;
    NewCalendar calendar;
    List<AST.Event> events;
    Scheduler scheduler;

    Title t1;
    Title t2;
    Title t3;

    Event e1;
    Event e2;
    Event e3;

    Occurrence day;
    Occurrence dayRange;
    Occurrence time;
    Occurrence timeRange;

    Day dayNode;
    DayRange dayRangeNode;
    Time timeNode;
    TimeRange timeRangeNode;

    Location l1;
    Location l2;
    Location l3;

    Description d1;
    Description d2;
    Description d3;

    @BeforeEach
    void init(){
        program = new Program();
        calendar = new NewCalendar();
        events  = new ArrayList<AST.Event>();

        t1 = new Title();
        t1.setTitle("t1");
        t2 = new Title();
        t2.setTitle("t2");
        t3 = new Title();
        t3.setTitle("t3");

        day = new Occurrence();
        dayRange = new Occurrence();
        time = new Occurrence();
        timeRange = new Occurrence();

        dayNode = new Day();
        dayRangeNode = new DayRange();
        timeNode = new Time();
        timeRangeNode = new TimeRange();

        day.setRange(dayNode);
        dayRange.setRange(dayRangeNode);
        time.setRange(timeNode);
        timeRange.setRange(timeRangeNode);

        l1 = new Location();
        l1.setName("l1");
        l2 = new Location();
        l2.setName("l2");
        l3 = new Location();
        l3.setName("l3");

        d1 = new Description();
        d1.setDesc("d1");
        d2 = new Description();
        d2.setDesc("d2");
        d3 = new Description();
        d3.setDesc("d3");

        e1 = new Event();
        e2 = new Event();
        e3 = new Event();

        e1.setTitle(t1);
        e2.setTitle(t2);
        e3.setTitle(t3);

        e1.setDescription(d1);
        e2.setDescription(d2);
        e3.setDescription(d3);

        e1.setLocation(l1);
        e2.setLocation(l2);
        e3.setLocation(l3);
    }
    @Test
    void basicOneFlexEvent(){
        dayNode.setDay("Monday");
        e1.setOccurrence(day);

        events.add(e1);
        calendar.setEvents(events);
        program.setCalendar(calendar);

        scheduler = program.evaluate();
        assertEquals(1, scheduler.getDays().get(1).getEvents().size());
        System.out.println(scheduler.getDays().get(1).getEvents());

    }

    @Test
    void OneFlexOneIndEventDifferentDays(){
        (dayNode).setDay("Monday");
        e1.setOccurrence(day);

        events.add(e1);
        calendar.setEvents(events);
        program.setCalendar(calendar);

        Time tstart = new Time();
        Time tend = new Time();

        Day day2 = new Day();
        day2.setDay("Wednesday");
        tstart.setTime(9);
        tend.setTime(23);

        timeRangeNode.setDay(day2);
        timeRangeNode.setStart(tstart);
        timeRangeNode.setEnd(tend);
        timeRange.setRange(timeRangeNode);
        e2.setOccurrence(timeRange);

        events.add(e2);
        calendar.setEvents(events);
        program.setCalendar(calendar);

        scheduler = program.evaluate();
        assertEquals(1, scheduler.getDays().get(1).getEvents().size());
        assertEquals(1, scheduler.getDays().get(3).getEvents().size());

    }

    @Test
    void OneFlexOneIndEventSameDay(){
        (dayNode).setDay("Saturday");
        e1.setOccurrence(day);

        events.add(e1);

        Time tStart = new Time();
        Time tEnd = new Time();
        tStart.setTime(9);
        tEnd.setTime(23);

        timeRangeNode.setDay(dayNode);
        timeRangeNode.setStart(tStart);
        timeRangeNode.setEnd(tEnd);
        timeRange.setRange(timeRangeNode);
        e2.setOccurrence(timeRange);

        events.add(e2);
        calendar.setEvents(events);
        program.setCalendar(calendar);

        scheduler = program.evaluate();
        assertEquals(2, scheduler.getDays().get(6).getEvents().size());
    }

    @Test
    void RandomMultipleEvents(){
        dayNode.setDay("Tuesday");
        e1.setOccurrence(day);
        e3.setOccurrence(day);

        events.add(e1);
        events.add(e3);

        Time tStart = new Time();
        Time tEnd = new Time();
        Day day2 = new Day();

        day2.setDay("Friday");
        tStart.setTime(15);
        tEnd.setTime(17);

        timeRangeNode.setDay(day2);
        timeRangeNode.setStart(tStart);
        timeRangeNode.setEnd(tEnd);
        timeRange.setRange(timeRangeNode);
        e2.setOccurrence(timeRange);

        events.add(e2);
        calendar.setEvents(events);
        program.setCalendar(calendar);

        scheduler = program.evaluate();
        assertEquals(2, scheduler.getDays().get(2).getEvents().size());
        assertEquals(1, scheduler.getDays().get(5).getEvents().size());
    }

}
