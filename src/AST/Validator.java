package AST;

import libs.Keyword;
import libs.Tokenizer;

import javax.management.relation.RoleUnresolved;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Validator {
    final static String[] days = Keyword.days;
    // final static String[] days = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};
    // final static String[] settingkeys = {"location:", "repeat:", "description:"};
    final static String[] settingkeys = Keyword.settingkeys;
    final static HashMap<String, String> keys = Keyword.keywords;

    public static String validateDay(String token) {
        String value;

        for (String s : days) {
            if (token.equalsIgnoreCase(s)) {
                value = s;
                return value;
            }
        }
        // will only reach this line if token does not match a valid day string
        throw new RuntimeException("Invalid day: " + token);
    }

    // Checks token for numeric integer value and within given range (inclusive)
    public static int validateTime(String token, int start, int end) {
        try {
            int value = Integer.parseInt(token);
            if(value > end || value < start){
                throw new RuntimeException("Value out of range:" + token);
            }
            return value;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid time: " + token);
        }
    }

    public static String validateString(String token) {
        String[] reservedWords = Keyword.reservedWords;
        for (String s: reservedWords) {
            if (token.contains(s)) {
                throw new RuntimeException("String contains reserved word: " + token);
            }
        }
        return token;
    }

    public static List<String> validateRepetition(String token) {
        Tokenizer t = Tokenizer.getTokenizer();
        List<String> dayList = new ArrayList<String>();
        String[] repeatable = Keyword.repeatable;
        String days = Arrays.toString(Keyword.days);

        if (days.contains(token)) {
            dayList.add(token);
            while (!t.checkNext().equals(";")) {
                t.getAndCheckNext(",");
                dayList.add(t.getNext());
            }
            return dayList;
        }

        for (String s: repeatable) {
            if (token.equalsIgnoreCase(s)) {
                dayList.add(token);;
                return dayList;
            }
        }
        if (dayList.size() == 0) {
            throw new RuntimeException("Not a valid repetition: " + token);
        }
        return dayList;
    }


    public static ASTnode validateOccurrence(String token) {
        Tokenizer t = Tokenizer.getTokenizer();
        try {
            int val = Integer.parseInt(token);
            return new Duration();
        } catch (NumberFormatException e) {
            if (Arrays.asList(days).contains(token)) {
                return new Day();
            } else if (token.equals(keys.get("at"))) {
                return new Time();
            } else if (token.equals(keys.get("from"))) {
                return new DayRange();
            } else if (token.equals(keys.get("on"))) {
                return new TimeRange();
            } else if (token.equals(keys.get("start"))) {
                return new TimeRange();
            } else {
                throw new RuntimeException("Invalid Occurrence type: " + token);
            }
        }
    }

    public static Event validateExistingEvent(String next) {
        for (Event e:NewCalendar.getSelf().events.toArray(new Event[]{})) {
            if(e.title.title.equals(next)) {
                return e;
            }
        }
        // todo check for events already in model so grouping knows if exists and add
        throw new RuntimeException("no valid event to group");
    }

    public static boolean getValidSettingKeyword(String token) {
        return (Arrays.asList(settingkeys).contains(token));
    }

    // REQUIRES: Validated token by getValidSettingKeyword()
    public static Setting getAndSettingType(String token, Event e) {
        if (token.equals(keys.get("location:"))) {
            e.location = new Location();
            return e.location;
        }
        if (token.equals(keys.get("repeat:"))) {
            e.repeat = new Repetition();
            return e.repeat;
        }
        if (token.equals(keys.get("description:"))) {
            e.description= new Description();
            return e.description;
        } else {
            throw new RuntimeException("Not a valid setting type: " + token);
        }
    }
}
