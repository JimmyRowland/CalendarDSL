package AST;

import libs.Keyword;
import libs.Tokenizer;
import model.EventCreator;
import model.Scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;


public class Program implements ASTnode {

    NewCalendar calendar;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        if(t.checkToken(Keyword.keywords.get("new calendar"))) {
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
        for (Event e:calendar.events
             ) {
            String location = "";
            String title = "";
            String desc = "";
            String dayStart = null;
            String dayEnd = null;
            int start = 0;
            int end = 0;
            String startString = null;
            String endString = null;
            int dur = 0;
            int startdow = 0;
            int enddow = 0;
            List<Integer> repetition = null;
            if (e.getClass().equals(Group.class)) {
                int tempStart;
                int tempEnd;
                boolean found = false;
                for (model.Day day: scheduler.getDays()) {
                    ArrayList<model.Event> events = day.getEvents();
                    for (model.Event event: events) {
                        for (AST.Event eventAST: ((Group) e).events) {
                            if (event.getName() == eventAST.title.title) {
                                found = true;
                                tempStart = event.getStart().get(Calendar.HOUR_OF_DAY);
                                tempEnd = event.getEnd().get(Calendar.HOUR_OF_DAY);
                                if (start == 0 || tempStart < start) {
                                    startdow = event.getDayOfWeek();
                                    start = tempStart;
                                }
                                if (end == 0 || tempEnd > end) {
                                    end = tempEnd;
                                }
                            }
                        }
                    }
                    dur = end-start;
                    if (found) {
                        break;
                    }
                }
            }
            else if (e.occurrence.getClass().equals(Duration.class)) {
                dur = ((Duration) e.occurrence).hours;
            }
            else if (e.occurrence.range.getClass().equals(Day.class)) {
                dayStart = ((Day) e.occurrence.range).day;
                if (((Day) e.occurrence.range).time == null && ((Day) e.occurrence.range).timeRange == null) {
                    start = 6;
                    end = 23;
                } else if (((Day) e.occurrence.range).time != null) {
                    start = ((Day) e.occurrence.range).getTime();
                } else {
                    start = ((Day) e.occurrence.range).timeRange.start.time;
                    end = ((Day) e.occurrence.range).timeRange.end.time;
                }
                dur = end-start;
            }
            else if (e.occurrence.range.getClass().equals(DayRange.class)) {
                dayStart = ((DayRange) e.occurrence.range).from.day;
                dayEnd = ((DayRange) e.occurrence.range).to.day;
            }
            else if (e.occurrence.range.getClass().equals(Time.class)) {
                start = ((Time) e.occurrence.range).time;
            }
            else if (e.occurrence.range.getClass().equals(TimeRange.class)) {
                if (((TimeRange) e.occurrence.range).day != null) {
                    dayStart = ((TimeRange) e.occurrence.range).day.day;
                }
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
                if (e.repeat != null && e.repeat.dayList.size() > 0) {
                    repetition = new ArrayList<Integer>();
                    for (String day: e.repeat.dayList) {
                        repetition.add(parseDayOfWeek(day));
                    };
                }
            } catch (ParseException parseException) {
                System.out.println("invalid day");
            }
            if (start != 0) {
                startString = convertTime(start);
            }
            if (end != 0) {
                endString = convertTime(end);
            }
            if (e.location != null) {
                location = e.getLocation();
            }
            if (e.title != null) {
                title = e.getTitle();
            }

            if (e.description != null) {
                desc = e.description.desc;
            }
            try {
                scheduler.addEvent(ec.createEvent(startString, endString, title, location, desc, dur, startdow, repetition));

            } catch (Exception exception) {
                System.out.println("Could not convert to event");
                exception.printStackTrace();
            }
        }
        scheduler.allocateFlexibleEvents();
        scheduler.print();
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
        if (ret.length() < 2) {
            ret = "0" + ret;
        }
        while (ret.length() < 4) {
            ret = ret + "0";
        }
        ret = ret.substring(0, 2) + ":" + ret.substring(2,4);
        return ret;
    }

    public void setCalendar(NewCalendar calendar) {
        this.calendar = calendar;
    }
}
