package test.AST;

import AST.*;
import model.io.Tokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

class parserTest {
    Program prog = new Program();
    Tokenizer tokenizer;

    @BeforeEach
    void init() {
        prog = new Program();
        tokenizer = new Tokenizer(new String[]{""});
    }

    void getCalendarInfo(NewCalendar cal) {
        List<Event> events = cal.getEvents();
        Object calTitle = cal.getTitle().getTitle();
        System.out.println("calendar title: " + calTitle);
        for (Event event: events) {
            String eventTitle = event.getTitle();
            Object eventOccurrence = event.getOccurrence();
            String eventDescrip = event.getDescription();
            String eventLocation = event.getLocation();
            String eventRepeat = event.getRepeat();
            String eventGroupTitle = event.getGroupTitle();
            List<String> eventGroupEvents = event.getGroupEvents();
            System.out.println("event title: " + eventTitle);
            System.out.println("event occurence: " + eventOccurrence);
            System.out.println("event description: " + eventDescrip);
            System.out.println("event location: " + eventLocation);
            System.out.println("event repeat: " + eventRepeat);
            System.out.println("event group title: " + eventGroupTitle);
            System.out.println("event group events: " + eventGroupEvents);
        }
    }

    @Test
    void simpleTest() {
        String[] tokens = {"new calendar", "my calendar!!", ";",
                "new event", "big day", ";",
                "<", "monday", ">",
                "description:", "blah", ";",
                "event end",
                "end"};
        tokenizer = new Tokenizer(tokens);
        prog.parse();
        NewCalendar cal = prog.getCalendar();
        getCalendarInfo(cal);
    }

    @Test
    void testLocation() {
        String[] tokens = {"new calendar", "my calendar!!", ";",
                "new event", "big day", ";",
                "<", "monday", ">",
                "description:", "blah", ";",
                "location:", "wherever", ";",
                "event end",
                "end"};
        tokenizer = new Tokenizer(tokens);
        prog.parse();
        NewCalendar cal = prog.getCalendar();
        getCalendarInfo(cal);
    }

    @Test
    void testRepeat() {
        String[] tokens = {"new calendar", "my calendar!!", ";",
                "new event", "big day", ";",
                "<", "monday", ">",
                "description:", "blah", ";",
                "location:", "wherever", ";",
                "repeat:", "MWF", ";",
                "event end",
                "end"};
        tokenizer = new Tokenizer(tokens);
        prog.parse();
        NewCalendar cal = prog.getCalendar();
        getCalendarInfo(cal);
    }
}
