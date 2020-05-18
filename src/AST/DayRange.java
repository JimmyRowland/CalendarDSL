package AST;

import model.io.Tokenizer;

public class DayRange extends Occurrence implements ASTnode{
    Day from;
    Day to;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("from");
        from = new Day();
        from.parse();
        t.getAndCheckNext("to");
        to = new Day();
        to.parse();
    }

    @Override
    public void evaluate() {

    }
}
