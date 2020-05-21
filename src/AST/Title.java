package AST;

import model.io.Tokenizer;

public class Title implements ASTnode {
    String title;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        title = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {

    }

    public String getTitle() {
        return title;
    }
}
