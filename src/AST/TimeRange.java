package AST;


import libs.Keyword;
import libs.Tokenizer;

import java.util.HashMap;


public class TimeRange extends Occurrence implements ASTnode{
    Day day;
    Time start;
    Time end;
    HashMap<String, String> keys = Keyword.keywords;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        if (t.checkNext().equals("start")) {
            t.getAndCheckNext(keys.get("start"));
            start = new Time();
            start.parse();
            t.getAndCheckNext(keys.get("finish"));
            end = new Time();
            end.parse();
        }
    }


    public String getTimeRange() {
        String dayStr = "";
        if (day != null) {
            dayStr = day.getDay() + " ";
        }
        String startStr = String.valueOf(start.getTime());
        String endStr = String.valueOf(end.getTime());
        return dayStr + startStr + "-" + endStr;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    @Override
    public void evaluate(Program.EvalObject evalObject) {
        if (day != null) {
            evalObject.setDayStart(this.day.getDay());
        }
        evalObject.setStart(this.start.getTime());
        evalObject.setEnd(this.end.getTime());
    }
}
