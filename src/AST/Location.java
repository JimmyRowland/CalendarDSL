package AST;

import model.io.Tokenizer;

public class Location implements Setting, ASTnode {
    String name;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("location:");
        name = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {

    }

    public String getLocation() {
        return name;
    }
}
