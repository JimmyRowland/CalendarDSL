package AST;


import model.Scheduler;
import libs.Tokenizer;

public class Title implements ASTnode {
    String title;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        title = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }



    public String getTitle() {
        return title;
    }
}
