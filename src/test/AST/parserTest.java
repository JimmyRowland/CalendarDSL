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
            ASTnode eventDay = event.getOccurrence();
            String eventDescrip = event.getDescription();
            System.out.println("event title: " + eventTitle);
            System.out.println("event day: " + eventDay);
            System.out.println("event description: " + eventDescrip);
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
//        List<Event> events = cal.getEvents();
//        Event event = events.get(0);
//        Object calTitle = cal.getTitle().getTitle();
//        String eventTitle = event.getTitle();
//        ASTnode eventDay = event.getOccurrence();
//        String eventDescrip = event.getDescription();
//        System.out.println("calendar title: " + calTitle);
//        System.out.println("event title: " + eventTitle);
//        System.out.println("event day: " + eventDay);
//        System.out.println("event description: " + eventDescrip);
    }
}
