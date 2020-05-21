package AST;

import model.io.Tokenizer;


public class Occurrence implements ASTnode {

    ASTnode range; // todo range types will need to be parsed and get value into model

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("<");
        String token = t.checkNext();
        range = Validator.validateOccurrence(token);
        range.parse();
        t.getAndCheckNext(">");
    }

    @Override
    public void evaluate() {

    }
}
