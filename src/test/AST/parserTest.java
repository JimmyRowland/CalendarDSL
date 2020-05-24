package test.AST;

import AST.Event;
import AST.NewCalendar;
import AST.Program;
import libs.Keyword;
import libs.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class parserTest {
    Program prog = new Program();
    Tokenizer tokenizer;
    Keyword initKeys = new Keyword();
    List<String> literals = Arrays.asList(";", "new calendar", "new event", "event end",
            "group:", "<", ">", "(", ",", ")", "|", "start", "finish", "location:", "repeat:",
            "daily", "every", "priority", "description:", "@", "from", "to");

    @BeforeEach
    void init() {
        prog = new Program();
        initKeys = new Keyword();
    }

    void getCalendarInfo(NewCalendar cal) {
        List<Event> events = cal.getEvents();
        Object calTitle = cal.getTitle().getTitle();
        System.out.println("calendar title: " + calTitle);
        for (Event event: events) {
            System.out.println("New event: " + event.hashCode());
            String eventTitle = event.getTitle();
            Object eventOccurrence = event.getOccurrence();
            String eventDescrip = event.getDescription();
            String eventLocation = event.getLocation();
            String eventRepeat = event.getRepeat();
            String eventGroupTitle = event.getGroupTitle();
            List<String> eventGroupEvents = event.getGroupEvents();
            System.out.println("title: " + eventTitle);
            System.out.println("occurence: " + eventOccurrence);
            System.out.println("description: " + eventDescrip);
            System.out.println("location: " + eventLocation);
            System.out.println("repeat: " + eventRepeat);
            System.out.println("group title: " + eventGroupTitle);
            System.out.println("group events: " + eventGroupEvents);
        }
    }

    @Test
    void simpleTest() {
        tokenizer = new Tokenizer("src/test/AST/test1", literals);
        tokenizer = Tokenizer.getTokenizer();
        prog.parse();
        NewCalendar cal = prog.getCalendar();
        getCalendarInfo(cal);
    }

    @Test
    void testLocationAndRepeatMWF() {
        tokenizer = new Tokenizer("src/test/AST/test2", literals);
        tokenizer = Tokenizer.getTokenizer();
        prog.parse();
        NewCalendar cal = prog.getCalendar();
        getCalendarInfo(cal);
    }

    @Test
    void testOccurenceTime() {
        tokenizer = new Tokenizer("src/test/AST/test3", literals);
        tokenizer = Tokenizer.getTokenizer();
            prog.parse();
            NewCalendar cal = prog.getCalendar();
            getCalendarInfo(cal);
    }

    @Test
    void testOccurenceTimeRangeWithDay() {
        tokenizer = new Tokenizer("src/test/AST/test4", literals);
        tokenizer = Tokenizer.getTokenizer();
        prog.parse();
        NewCalendar cal = prog.getCalendar();
        getCalendarInfo(cal);
    }

    @Test
    void testOccurenceTimeRangeWithoutDayRepeatDayList() {
        tokenizer = new Tokenizer("src/test/AST/test5", literals);
        tokenizer = Tokenizer.getTokenizer();
        prog.parse();
        NewCalendar cal = prog.getCalendar();
        getCalendarInfo(cal);
    }

    @Test
    void testDayRangeAllFields() {
        tokenizer = new Tokenizer("src/test/AST/test6", literals);
        tokenizer = Tokenizer.getTokenizer();
        prog.parse();
        NewCalendar cal = prog.getCalendar();
        getCalendarInfo(cal);
    }

    @Test
    void testMultipleEvents() {
        tokenizer = new Tokenizer("src/test/AST/test7", literals);
        tokenizer = Tokenizer.getTokenizer();
        prog.parse();
        NewCalendar cal = prog.getCalendar();
        getCalendarInfo(cal);
    }

    @Test
    void testKeywordMap() {
        String changedWord1 = Keyword.keywords.get("at");
        String changedWord2 = Keyword.keywords.get("done");
        System.out.println(changedWord1);
        System.out.println(changedWord2);
    }

    @Test
    void testGroupingOnTwoEvents() {
        tokenizer = new Tokenizer("src/test/AST/groupTest1", literals);
        tokenizer = Tokenizer.getTokenizer();
        prog.parse();
        NewCalendar cal = prog.getCalendar();
        getCalendarInfo(cal);
    }


}
