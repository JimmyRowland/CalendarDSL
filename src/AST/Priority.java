package AST;

import model.io.Tokenizer;

public class Priority implements Setting, ASTnode {
    int value;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("priority:");
        String token = t.getNext();
//        Validator.validateInt(token);
        value = Validator.validatePriority(token);
        t.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {

    }
}
