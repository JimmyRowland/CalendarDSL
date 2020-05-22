package AST;

<<<<<<< HEAD
import model.Scheduler;
import model.io.Tokenizer;

=======
>>>>>>> origin/dev
public class Title implements ASTnode {
    String title;

    @Override
    public void parse() {
        Tokenizer t = Tokenizer.getTokenizer();
        title = Validator.validateString(t.getNext());
        t.getAndCheckNext(";");
    }

    @Override
    public Scheduler evaluate() {

    }

    public String getTitle() {
        return title;
    }
}
