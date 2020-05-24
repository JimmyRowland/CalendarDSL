package AST;

import libs.Tokenizer;
import model.Event;
import model.Scheduler;
import model.EventCreator;

import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Program implements ASTnode {

    NewCalendar calendar;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        if(t.checkToken("new calendar")) {
            calendar = new NewCalendar();
            calendar.parse();
            // unique program terminator would allow for more than one cal
        } else {
            throw new RuntimeException("Invalid syntax, expected a calendar declaration");
        }
    }


    public Scheduler evaluate() {
        EventCreator ec = new EventCreator();
        Scheduler scheduler = new Scheduler();
        for (AST.Event e:calendar.events
             ) {
            String dayStart = null;
            String dayEnd = null;
            int start = 0;
            int end = 0;
            String startString = null;
            String endString = null;
            int dur = 2; //todo implement duration once AST is implemented
            int startdow = 0;
            int enddow = 0;
            List<Integer> repetition = null;
            if (e.occurrence.range.getClass().equals(AST.Day.class)) {
                dayStart = ((Day) e.occurrence.range).day;
            }
            else if (e.occurrence.range.getClass().equals(AST.DayRange.class)) {
                dayStart = ((DayRange) e.occurrence.range).from.day;
                dayEnd = ((DayRange) e.occurrence.range).to.day;
            }
            else if (e.occurrence.range.getClass().equals(AST.Time.class)) {
                start = ((Time) e.occurrence.range).time;
            }
            else if (e.occurrence.range.getClass().equals(AST.TimeRange.class)) {
                dayStart = ((TimeRange) e.occurrence.range).day.day;
                start = ((TimeRange) e.occurrence.range).start.time;
                end = ((TimeRange) e.occurrence.range).end.time;
            }
            try {
                if (dayStart != null) {
                    startdow = parseDayOfWeek(dayStart);
                }
                if (dayEnd != null) {
                    enddow = parseDayOfWeek(dayStart);
                }
            } catch (ParseException parseException) {
                System.out.println("invalid day");
            }
            if (e.repeat != null) {
                repetition = e.repeat.evaluate();
            }
            if (start != 0) {
                startString = convertTime(start);
            }
            if (end != 0) {
                endString = convertTime(end);
            }
            try {
                scheduler.addEvent(ec.createEvent(startString, endString, e.title.title, e.location.name, e.description.desc, dur, startdow, repetition));
            } catch (Exception exception) {
                System.out.println("Could not convert to event");
                exception.printStackTrace();
            }
        }
        scheduler.allocateFlexibleEvents();
        return scheduler;
    }

    // modified from https://stackoverflow.com/questions/18232340/convert-string-to-day-of-week-not-exact-date
    private static int parseDayOfWeek(String day) throws ParseException {
        SimpleDateFormat dayFormat = new SimpleDateFormat("E", Locale.US);
        Date date = dayFormat.parse(day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }



    public NewCalendar getCalendar() {
        return calendar;
    }

    public String convertTime(int time) {
        String ret = Integer.toString(time);
        if (time < 10){
            ret = "0" + ret + ":00";
        } else {
            ret = ret + ":00";
        }
        return ret;
    }

    public void setCalendar(NewCalendar calendar) {
        this.calendar = calendar;
    }
}
