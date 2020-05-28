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

    @Override
    public void evaluate(EvalObject evalObject) {
    }

    public class EvalObject {
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

        public String getLocation() {
            return location;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public String getDayStart() {
            return dayStart;
        }

        public String getDayEnd() {
            return dayEnd;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public String getStartString() {
            return startString;
        }

        public String getEndString() {
            return endString;
        }

        public int getDur() {
            return dur;
        }

        public int getStartdow() {
            return startdow;
        }

        public int getEnddow() {
            return enddow;
        }

        public List<Integer> getRepetition() {
            return repetition;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public void setDayStart(String dayStart) {
            this.dayStart = dayStart;
        }

        public void setDayEnd(String dayEnd) {
            this.dayEnd = dayEnd;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public void setStartString(String startString) {
            this.startString = startString;
        }

        public void setEndString(String endString) {
            this.endString = endString;
        }

        public void setDur(int dur) {
            this.dur = dur;
        }

        public void setStartdow(int startdow) {
            this.startdow = startdow;
        }

        public void setEnddow(int enddow) {
            this.enddow = enddow;
        }

        public void setRepetition(List<Integer> repetition) {
            this.repetition = repetition;
        }

    }

    public Scheduler evaluate() {
        EventCreator ec = new EventCreator();
        Scheduler scheduler = new Scheduler();
        for (Event e:calendar.events
             ) {
            EvalObject evalObject = new EvalObject();
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
                                if (evalObject.getStart() == 0 || tempStart < evalObject.getStart()) {
                                    evalObject.setStartdow(event.getDayOfWeek());
                                    evalObject.setStart(tempStart);
                                }
                                if (evalObject.getEnd() == 0 || tempEnd > evalObject.getEnd()) {
                                    evalObject.setEnd(tempEnd);
                                }
                            }
                        }
                    }
                    evalObject.setDur(evalObject.getEnd()-evalObject.getStart());
                    if (found) {
                        break;
                    }
                }
            }
            else {
                e.evaluate(evalObject);
            }
            try {
                if (evalObject.getDayStart() != null) {
                    evalObject.setStartdow(parseDayOfWeek(evalObject.getDayStart()));
                }
                if (evalObject.getDayEnd() != null) {
                    evalObject.setEnddow(parseDayOfWeek(evalObject.getDayEnd()));
                }
            } catch (ParseException parseException) {
                System.out.println("invalid day");
            }
            if (evalObject.getStart() != 0) {
                evalObject.setStartString(convertTime(evalObject.getStart()));
            }
            if (evalObject.getEnd() != 0) {
                evalObject.setEndString(convertTime(evalObject.getEnd()));
            }
            try {
                scheduler.addEvent(ec.createEvent(evalObject.getStartString(), evalObject.getEndString(), evalObject.getTitle(), evalObject.getLocation(), evalObject.getDesc(), evalObject.getDur(), evalObject.getStartdow(), evalObject.getRepetition()));

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
    public static int parseDayOfWeek(String day) throws ParseException {
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
