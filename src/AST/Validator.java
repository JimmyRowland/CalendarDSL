package AST;

import model.io.Tokenizer;

import java.util.Arrays;

public class Validator {

    public static String validateDay(String token) {
        String value;
        String[] days = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};
        for (String s : days) {
            if (token.equalsIgnoreCase(s)) {
                value = s;
                return value;
            }
        }
        // will only reach this line if token does not match a valid day string
        throw new RuntimeException("Invalid day");
    }

    public static int validateInt(String token) {
        int value;
        try {
            value = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid time");
        }
        return value;
    }

    public static String validateString(String token) {
        String[] reservedWords = { ";", "<", ">", ":" };
        for (String s: reservedWords) {
            if (token.contains(s)) {
                throw new RuntimeException("String contains reserved word");
            }
        }
        return token;
    }

    public static int validatePriority(String token) {
        int value;
        try {
            value = Integer.parseInt(token);
            if (value < 1 || value > 2) {
                throw new RuntimeException("Priority value out of range");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid priority value");
        }
        return value;
    }

    public static String validateRepetition(String token) {
        Tokenizer t = Tokenizer.getTokenizer();
        String value = null;
        String[] repeatable = {"daily","MWF","TTH"};
        if (token.equalsIgnoreCase("every")) {
            value = token + Validator.validateDay(t.getNext());
        }
        for (String s: repeatable) {
            if (token.equalsIgnoreCase(s)) {
                value = token;
                break;
            }
        }
        if (value == null) {
            throw new RuntimeException("Invalid repetition");
        }
        return value;
    }
}
