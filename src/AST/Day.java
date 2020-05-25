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
