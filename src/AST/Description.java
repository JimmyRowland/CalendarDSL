package AST;

import model.io.Tokenizer;

public class Description implements Setting, ASTnode {
    String desc;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("description:");
        desc = t.getNext();
        Validator.validateString(desc);
        t.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {

    }
}
