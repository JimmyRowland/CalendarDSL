package AST;

<<<<<<< HEAD
import model.Scheduler;
import model.io.Tokenizer;

=======
>>>>>>> origin/dev

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

    @Override
<<<<<<< HEAD
    public Scheduler evaluate() {
=======
    public void evaluate() {
>>>>>>> origin/dev
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
}
