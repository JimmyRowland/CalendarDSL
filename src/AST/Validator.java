package AST;

import libs.Keyword;
import libs.Tokenizer;

import java.util.Arrays;
import java.util.HashMap;

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
        throw new RuntimeException("Invalid day");
    }

    // Checks token for numeric integer value and within given range (inclusive)
    public static int validateTime(String token, int start, int end) {
        try {
            int value = Integer.parseInt(token);
            if(value > end || value < start){
                throw new RuntimeException("Value out of range");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid time");
        }
    }

    public static String validateString(String token) {
        String[] reservedWords = Keyword.reservedWords;
        for (String s: reservedWords) {
            if (token.contains(s)) {
                throw new RuntimeException("String contains reserved word");
            }
        }
        return token;
    }

    public static String validateRepetition(String token) {
        Tokenizer t = Tokenizer.getTokenizer();
        String value = null;
        String[] repeatable = Keyword.repeatable;

        String str = token;
        if (str.contains("every")) {
            // validate the string starting from after the word every
            value = "every " + Validator.validateDay(token.substring(6));
            return value;
        }
        for (String s: repeatable) {
            if (token.equalsIgnoreCase(s)) {
                value = token;
                return value;
            }
        }
        if (value == null) {
            throw new RuntimeException("Invalid repetition");
        }
        return value;
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

    public static String validateExistingEvent(String next) {
        // todo check for events already in model so grouping knows if exists and add
        return next;
    }

    public static boolean getValidSettingKeyword(String token) {
        return (Arrays.asList(settingkeys).contains(token));
    }

    // REQUIRES: Validated token by getValidSettingKeyword()
    public static Setting getAndSettingType(String token, Event e) {
        // todo refactor this to something smarter
//        switch (token) {
//            case "location:":
//                e.location = new Location();
//                return e.location;
//            case "repeat:":
//                e.repeat = new Repetition();
//                return e.repeat;
//            case "description:":
//                e.description= new Description();
//                return e.description;
//            default:
//                // throw new RuntimeException("Not a valid setting type");
//        }
//
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
