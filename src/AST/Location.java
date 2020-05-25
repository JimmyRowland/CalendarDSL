package AST;

import model.Scheduler;
import model.io.Tokenizer;

public class Location implements Setting, ASTnode {
    String name;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.checkToken("location:");
        name = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }

    @Override
    public Scheduler evaluate() {

    }

    public String getLocation() {
        return name;
    }
}
