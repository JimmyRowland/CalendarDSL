package AST;

import model.io.Tokenizer;


public class TimeRange extends Occurrence implements ASTnode{
    Time start;
    Time end;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("at");
        start = new Time();
        start.parse();
        t.getAndCheckNext("to");
        end = new Time();
        end.parse();
    }

    @Override
    public void evaluate() {
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }
}
