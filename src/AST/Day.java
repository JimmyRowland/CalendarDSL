package AST;


import libs.Keyword;
import libs.Tokenizer;


public class Day implements ASTnode {
    String day;
    Time time;
    TimeRange timeRange;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        String token = t.getNext();
        day = Validator.validateDay(token);
        if (t.checkNext().equals(Keyword.keywords.get("at"))) {
            t.getNext();
            time = new Time();
            time.parse();
        }
        if (t.checkNext().equals(Keyword.keywords.get("start"))) {
            timeRange = new TimeRange();
            timeRange.parse();
        }
    }

    @Override
    public void evaluate(Program.EvalObject evalObject) {
        evalObject.setDayStart(day);
        if (time == null && timeRange == null) {
            evalObject.setStart(6);
            evalObject.setEnd(23);
        } else if (time != null) {
            time.evaluate(evalObject);
        } else {
            timeRange.evaluate(evalObject);
        }
        evalObject.setDur(evalObject.getEnd()-evalObject.getStart());
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTime() {
        return time.getTime();
    }

    public String getTimeRange() {
        return timeRange.getTimeRange();
    }
}
