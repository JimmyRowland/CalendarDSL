package AST;


import libs.Keyword;
import libs.Tokenizer;


public class Occurrence implements ASTnode {

    ASTnode range; // todo range types will need to be parsed and get value into model

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext(Keyword.keywords.get("<"));
        String token = t.checkNext();
        range = Validator.validateOccurrence(token);
        range.parse();
        t.getAndCheckNext(Keyword.keywords.get(">"));
    }

    public Object getRange() {
        if (range.getClass().equals(AST.Day.class)) {
            Day day = (Day) range;
            if (day.timeRange != null) {
                return day.getDay() + day.getTimeRange();
            }
            if (day.time != null) {
                return day.getDay() + day.getTime();
            }
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

    public void setRange(ASTnode range) {
        this.range = range;
    }

    public void evaluate(Program.EvalObject evalObject) {
        range.evaluate(evalObject);
    }
}
