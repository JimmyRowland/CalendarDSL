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
        try {
            time = Integer.parseInt(timeStr);
        } catch (Exception err) {
            throw new RuntimeException("Not a valid time");
        }
    }

    @Override
    public void evaluate() {

    }
}
