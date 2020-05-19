package AST;

import model.io.Tokenizer;

public class Time implements ASTnode{
    int time;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("at");
        String timeStr = t.getNext();
        timeStr = timeStr.replace(":", "");
        time = Validator.validateInt(timeStr);
    }

    @Override
    public void evaluate() {

    }
}
