package AST;

import model.Scheduler;
import model.io.Tokenizer;

public class Day implements ASTnode {
    String day;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        String token = t.getNext();
        day = Validator.validateDay(token);
    }

    @Override
    public Scheduler evaluate() {

    }

    public String getDay() {
        return day;
    }
}
