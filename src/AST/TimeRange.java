package AST;


import model.Scheduler;
import libs.Tokenizer;



public class TimeRange extends Occurrence implements ASTnode{
    Day day;
    Time start;
    Time end;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        if (t.checkToken("on")) {
            t.getAndCheckNext("on");
            day = new Day();
            day.parse();
        }
            t.getAndCheckNext("start");
            start = new Time();
            start.parse();
            t.getAndCheckNext("finish");
            end = new Time();
            end.parse();
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
}
