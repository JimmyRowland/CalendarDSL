package AST;


import libs.Keyword;
import libs.Tokenizer;

public class Title implements ASTnode {
    String title;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        title = Validator.validateString(t.getNext());
        t.getAndCheckNext(Keyword.keywords.get(";"));
    }

    @Override
    public void evaluate(Program.EvalObject evalObject) {
        evalObject.setTitle(title);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
