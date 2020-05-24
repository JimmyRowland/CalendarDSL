package AST;


import model.Scheduler;
import libs.Tokenizer;


public class Time implements ASTnode{
    int time;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("at");
        String timeStr = t.getNext();
        time = Validator.validateTime(timeStr, 0,23) * 100;
        t.getAndCheckNext(":");
        timeStr = t.getNext();
        time += Validator.validateTime(timeStr, 0, 59);
    }



    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
