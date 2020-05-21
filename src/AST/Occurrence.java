package AST;

import model.io.Tokenizer;


public class Occurrence implements ASTnode {

    ASTnode range; // todo range types will need to be parsed and get value into model

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("<");
        String token = t.checkNext();
        range = Validator.validateOccurrence(token);
        range.parse();
        t.getAndCheckNext(">");
    }

    @Override
    public void evaluate() {

    }

    public Object getRange() {
        if (range.getClass().equals(AST.Day.class)) {
            Day day = (Day) range;
            return day.getDay();
        }
        if (range.getClass().equals(AST.DayRange.class)) {
            DayRange dayRange = (DayRange) range;
            return dayRange.getDayRange();
        }
        if (range.getClass().equals(AST.Time.class)) {
            Time time = (Time) range;
            return time.getTime();
        }
        if (range.getClass().equals(AST.TimeRange.class)) {
            TimeRange timeRange = (TimeRange) range;
            return timeRange.getTimeRange();
        }
        return range;
    }
}
