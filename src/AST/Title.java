package AST;

import model.io.Tokenizer;

public class Title implements ASTnode {
    String title;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        t.getAndCheckNext("called:");
        title = t.getNext();
        t.getAndCheckNext(";");
    }

    @Override
    public void evaluate() {

    }
}
