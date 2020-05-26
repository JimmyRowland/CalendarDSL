package libs;

import AST.Event;
import AST.NewCalendar;
import AST.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenizerTest {
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
        for (Event event : events) {
            System.out.println("New event: " + event.hashCode());
            String eventTitle = event.getTitle();
            Object eventOccurrence = event.getOccurrence();
            String eventDescrip = event.getDescription();
            String eventLocation = event.getLocation();
            List<String> eventRepeat = event.getRepeat();
            String eventGroupTitle = event.getGroupTitle();
            List<Event> eventGroupEvents = event.getGroupEvents();
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
        String[] expected = {"new calendar", "my calendar!!", ";", "new event", "big day", ";", "<", "monday", ">",
                "description:", "blah", ";", "event end", ";"};
        String[] tokenizerArray = tokenizer.getTokenArray();
        assertTrue(Arrays.equals(tokenizerArray, expected));
    }

    @Test
    void testLocationAndRepeatMWF() {
        tokenizer = new Tokenizer("src/test/AST/test2", literals);
        tokenizer = Tokenizer.getTokenizer();
        String[] expected = {"new calendar", "my calendar!!", ";", "new event", "big day", ";", "<", "monday", "@",
                "12:30", ">", "description:", "blah", ";", "location:", "wherever", ";", "repeat:", "MWF", ";",
                "event end", ";"};
        String[] tokenizerArray = tokenizer.getTokenArray();
        assertTrue(Arrays.equals(tokenizerArray, expected));

    }

    @Test
    void testOccurenceTime() {
        tokenizer = new Tokenizer("src/test/AST/test3", literals);
        tokenizer = Tokenizer.getTokenizer();
        String[] expected = {"new calendar", "my calendar!!", ";", "new event", "big day", ";", "<", "@", "12:30", ">",
                "description:", "blah", ";", "location:", "wherever", ";", "repeat:", "daily", ";", "event end", ";"};
        String[] tokenizerArray = tokenizer.getTokenArray();
        assertTrue(Arrays.equals(tokenizerArray, expected));
    }

    @Test
    void testOccurenceTimeRangeWithDay() {
        tokenizer = new Tokenizer("src/test/AST/test4", literals);
        tokenizer = Tokenizer.getTokenizer();
        String[] expected = {"new calendar", "my calendar!!", ";", "new event", "big day", ";", "<", "monday", "start",
                "12:30", "finish", "14:30", ">", "description:", "blah", ";", "location:", "wherever", ";", "repeat:",
                "TTH", ";", "event end", ";"};
        String[] tokenizerArray = tokenizer.getTokenArray();
        assertTrue(Arrays.equals(tokenizerArray, expected));
    }

    @Test
    void testOccurenceTimeRangeWithoutDayRepeatDayList() {
        tokenizer = new Tokenizer("src/test/AST/test5", literals);
        tokenizer = Tokenizer.getTokenizer();
        String[] expected = {"new calendar", "my calendar!!", ";", "new event", "big day", ";", "<", "start", "12:30",
                "finish", "14:30", ">", "description:", "blah", ";", "location:", "wherever", ";", "repeat:", "monday",
                ",", "tuesday", ",", "friday", ";", "event end", ";"};
        String[] tokenizerArray = tokenizer.getTokenArray();
        assertTrue(Arrays.equals(tokenizerArray, expected));
    }

    @Test
    void testDayRangeAllFields() {
        tokenizer = new Tokenizer("src/test/AST/test6", literals);
        tokenizer = Tokenizer.getTokenizer();
        String[] expected = {"new calendar", "my calendar!!", ";", "new event", "big day", ";", "<", "from", "monday",
                "to", "wednesday", ">", "description:", "blah", ";", "location:", "whatever", ";", "repeat:", "sunday", ";",
                "event end", ";"};
        String[] tokenizerArray = tokenizer.getTokenArray();
        assertTrue(Arrays.equals(tokenizerArray, expected));
    }

    @Test
    void testMultipleEvents() {
        tokenizer = new Tokenizer("src/test/AST/test7", literals);
        tokenizer = Tokenizer.getTokenizer();
        String[] expected = {"new calendar", "my calendar!!", ";", "new event", "long event", ";", "<", "from",
                "monday", "to", "wednesday", ">", "description:", "blah", ";", "location:", "whatever", ";",
                "event end", "new event", "another day", ";", "<", "tuesday", "start", "14:00", "finish", "17:00", ">",
                "description:", "blahblah", ";", "location:", "wherever", ";", "repeat:", "MWF", ";", "event end", ";"};

        String[] tokenizerArray = tokenizer.getTokenArray();
        assertTrue(Arrays.equals(tokenizerArray, expected));
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
        String[] expected = {"new calendar", "lots of stuff", ";", "new event", "big day", ";", "<", "monday", ">",
                "description:", "blah", ";", "event end", "new event", "stuff", ";", "<", "wednesday", ">", "location:",
                "UBC", ";", "event end", "group:", "cool", ";", ">", "(", "big day", ",", "stuff", ")", ";"};
        String[] tokenizerArray = tokenizer.getTokenArray();
        assertTrue(Arrays.equals(tokenizerArray, expected));
    }
}

